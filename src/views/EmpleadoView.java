package views;

import core.Model;
import core.View;
import models.Empleado;
import models.SistemaTurnosModel;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class EmpleadoView extends JPanel implements View {

    private SistemaTurnosModel model;
    private JTextField txtNombre;
    private JTextField txtCargo;
    private JButton btnRegistrar;
    private JButton btnEliminar;
    private JTextArea areaEmpleados;

    public EmpleadoView(SistemaTurnosModel model) {
        this.model = model;
        setLayout(new BorderLayout(10, 10));

        JPanel formulario = new JPanel(new GridLayout(4, 2, 10, 10));
        formulario.add(new JLabel("Nombre"));
        txtNombre = new JTextField();
        formulario.add(txtNombre);

        formulario.add(new JLabel("Cargo"));
        txtCargo = new JTextField("Cajero");
        formulario.add(txtCargo);

        btnRegistrar = new JButton("Registrar Empleado");
        btnEliminar = new JButton("Eliminar último empleado");
        formulario.add(btnRegistrar);
        formulario.add(btnEliminar);

        formulario.add(new JLabel("Función"));
        formulario.add(new JLabel("Administra empleados para la atención"));
        add(formulario, BorderLayout.NORTH);

        areaEmpleados = new JTextArea();
        areaEmpleados.setEditable(false);
        add(new JScrollPane(areaEmpleados), BorderLayout.CENTER);

        btnRegistrar.addActionListener(e -> registrarEmpleado());
        btnEliminar.addActionListener(e -> eliminarUltimo());
    }

    private void registrarEmpleado() {
        String nombre = txtNombre.getText().trim();
        String cargo = txtCargo.getText().trim();
        if (nombre.isEmpty() || cargo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Complete nombre y cargo del empleado");
            return;
        }
        model.registrarEmpleado(nombre, cargo);
        txtNombre.setText("");
        txtCargo.setText("Cajero");
    }

    private void eliminarUltimo() {
        if (!model.eliminarUltimoEmpleado()) {
            JOptionPane.showMessageDialog(this, "No hay empleados para eliminar");
        }
    }

    @Override
    public void update(Model model, Object data) {
        if (model instanceof SistemaTurnosModel) {
            SistemaTurnosModel sistema = (SistemaTurnosModel) model;
            StringBuilder sb = new StringBuilder();
            sb.append("Último evento: ").append(data).append("\n\n");
            sb.append("EMPLEADOS REGISTRADOS\n");
            sb.append("--------------------\n");
            for (Empleado empleado : sistema.getEmpleados()) {
                sb.append("Código: ").append(empleado.getCodigo())
                  .append(" | Nombre: ").append(empleado.getNombre())
                  .append("\n");
            }
            areaEmpleados.setText(sb.toString());
        }
    }
}
