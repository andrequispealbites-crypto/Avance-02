package models;

import core.Model;
import core.View;
import strategy.EstrategiaPrioridad;
import strategy.TurnoStrategy;

import javax.swing.SwingUtilities;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SistemaTurnosModel implements Model {

    private final List<View> views;
    private final List<Cliente> clientes;
    private final List<Turno> turnos;
    private final List<Empleado> empleados;
    private final List<Atencion> atenciones;
    private final ExecutorService executor;

    private TurnoStrategy estrategia;
    private int contadorCliente;
    private int contadorTurno;
    private int contadorEmpleado;
    private String ultimoMensaje;

    public SistemaTurnosModel() {
        views = new CopyOnWriteArrayList<View>();
        clientes = Collections.synchronizedList(new ArrayList<Cliente>());
        turnos = Collections.synchronizedList(new ArrayList<Turno>());
        empleados = Collections.synchronizedList(new ArrayList<Empleado>());
        atenciones = Collections.synchronizedList(new ArrayList<Atencion>());
        executor = Executors.newFixedThreadPool(3);
        estrategia = new EstrategiaPrioridad();
        contadorCliente = 1;
        contadorTurno = 1;
        contadorEmpleado = 1;
        ultimoMensaje = "Sistema iniciado";

        registrarEmpleadoInicial("Cajero Principal");
    }

    @Override
    public void attach(View view) {
        if (view != null && !views.contains(view)) {
            views.add(view);
        }
    }

    @Override
    public void detach(View view) {
        views.remove(view);
    }

    @Override
    public void notifyViews() {
        final String mensaje = ultimoMensaje;
        for (final View view : views) {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    view.update(SistemaTurnosModel.this, mensaje);
                }
            });
        }
    }

    public void setEstrategia(TurnoStrategy estrategia) {
        if (estrategia != null) {
            this.estrategia = estrategia;
            ultimoMensaje = "Strategy cambiada: " + estrategia.getClass().getSimpleName();
            notifyViews();
        }
    }

    public Cliente registrarCliente(String nombre, String dni, boolean preferencial) {
        DocumentoIdentidad documento = new DocumentoIdentidad(dni, "DNI");
        Cliente cliente;

        if (preferencial) {
            cliente = new ClientePreferencial(contadorCliente++, nombre, documento, "Preferencial");
        } else {
            cliente = new Cliente(contadorCliente++, nombre, documento);
        }

        clientes.add(cliente);
        Auditoria.registrar("Cliente registrado: " + cliente.getNombre());
        ultimoMensaje = "Cliente registrado: " + cliente.getNombre();
        guardarDatos();
        notifyViews();
        return cliente;
    }

    public Empleado registrarEmpleado(String nombre, String cargo) {
        Empleado empleado;
        if (cargo != null && cargo.toLowerCase().contains("caj")) {
            empleado = new Cajero(contadorEmpleado++, nombre);
        } else {
            empleado = new Empleado(contadorEmpleado++, nombre + " (" + cargo + ")");
        }
        empleados.add(empleado);
        Auditoria.registrar("Empleado registrado: " + empleado.getNombre());
        ultimoMensaje = "Empleado registrado: " + empleado.getNombre();
        guardarDatos();
        notifyViews();
        return empleado;
    }

    private void registrarEmpleadoInicial(String nombre) {
        empleados.add(new Cajero(contadorEmpleado++, nombre));
    }

    public boolean eliminarUltimoEmpleado() {
        synchronized (empleados) {
            if (empleados.isEmpty()) {
                return false;
            }
            Empleado eliminado = empleados.remove(empleados.size() - 1);
            ultimoMensaje = "Empleado eliminado: " + eliminado.getNombre();
            guardarDatos();
            notifyViews();
            return true;
        }
    }

    public Turno generarTurno(TipoTurno tipo, Prioridad prioridad) {
        Turno turno = new Turno(contadorTurno++, tipo, prioridad);
        turnos.add(turno);
        Auditoria.registrar("Turno generado: " + turno);
        ultimoMensaje = "Turno generado: " + turno;
        guardarDatos();
        notifyViews();
        return turno;
    }

    public void cancelarUltimoTurno() {
        synchronized (turnos) {
            if (!turnos.isEmpty()) {
                Turno turno = turnos.get(turnos.size() - 1);
                turno.setEstado(EstadoTurno.CANCELADO);
                ultimoMensaje = "Turno cancelado: " + turno;
                guardarDatos();
                notifyViews();
            }
        }
    }

    public void atenderSiguienteAsync() {
        executor.submit(new Runnable() {
            @Override
            public void run() {
                Turno turno;
                synchronized (turnos) {
                    turno = estrategia.seleccionarSiguiente(turnos);
                    if (turno != null) {
                        turno.setEstado(EstadoTurno.EN_ATENCION);
                    }
                }

                if (turno == null) {
                    ultimoMensaje = "No hay turnos pendientes";
                    notifyViews();
                    return;
                }

                ultimoMensaje = "Atendiendo turno: " + turno + " (concurrencia activa)";
                notifyViews();

                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }

                Cajero cajero = obtenerCajeroDisponible();
                Atencion atencion = new Atencion(turno, cajero);
                atencion.iniciar();
                atencion.finalizar();
                atenciones.add(atencion);

                Auditoria.registrar("Turno finalizado: " + turno);
                ultimoMensaje = "Turno finalizado: " + turno + " por " + cajero.getNombre();
                guardarDatos();
                notifyViews();
            }
        });
    }

    private Cajero obtenerCajeroDisponible() {
        synchronized (empleados) {
            for (Empleado empleado : empleados) {
                if (empleado instanceof Cajero) {
                    return (Cajero) empleado;
                }
            }
        }
        return new Cajero(0, "Cajero temporal");
    }

    public List<Cliente> getClientes() {
        synchronized (clientes) {
            return new ArrayList<Cliente>(clientes);
        }
    }

    public List<Turno> getTurnos() {
        synchronized (turnos) {
            return new ArrayList<Turno>(turnos);
        }
    }

    public List<Empleado> getEmpleados() {
        synchronized (empleados) {
            return new ArrayList<Empleado>(empleados);
        }
    }

    public List<Atencion> getAtenciones() {
        synchronized (atenciones) {
            return new ArrayList<Atencion>(atenciones);
        }
    }

    public String getUltimoMensaje() {
        return ultimoMensaje;
    }

    public String generarReporte() {
        StringBuilder sb = new StringBuilder();
        sb.append("REPORTE GENERAL DEL SISTEMA\n");
        sb.append("===========================\n");
        sb.append("Clientes registrados: ").append(clientes.size()).append("\n");
        sb.append("Empleados registrados: ").append(empleados.size()).append("\n");
        sb.append("Turnos generados: ").append(turnos.size()).append("\n");
        sb.append("Atenciones finalizadas: ").append(atenciones.size()).append("\n\n");
        sb.append("Módulos activos:\n");
        sb.append("- Gestión de clientes\n");
        sb.append("- Gestión de turnos con Strategy\n");
        sb.append("- Atención con concurrencia\n");
        sb.append("- Gestión de empleados\n");
        sb.append("- Historial y seguimiento\n");
        sb.append("- Persistencia TXT\n");
        sb.append("- Interfaz de usuario MVC\n");
        sb.append("- Control y coordinación mediante controladores\n\n");
        sb.append("Turnos:\n");
        for (Turno turno : getTurnos()) {
            sb.append(turno).append("\n");
        }
        return sb.toString();
    }

    private void guardarDatos() {
        File carpeta = new File("data");
        if (!carpeta.exists()) {
            carpeta.mkdirs();
        }
        escribirArchivo(new File(carpeta, "clientes.txt"), getClientes());
        escribirArchivo(new File(carpeta, "empleados.txt"), getEmpleados());
        escribirArchivo(new File(carpeta, "turnos.txt"), getTurnos());
        escribirArchivo(new File(carpeta, "atenciones.txt"), getAtenciones());
    }

    private void escribirArchivo(File archivo, List<?> datos) {
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(archivo));
            for (Object dato : datos) {
                bw.write(String.valueOf(dato));
                bw.newLine();
            }
        } catch (IOException e) {
            ultimoMensaje = "No se pudo guardar TXT: " + e.getMessage();
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException ignored) { }
            }
        }
    }
}
