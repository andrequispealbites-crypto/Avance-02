package models;

public class GestorTurnos {

    private ColaTurnos cola;
    private int contador;

    public GestorTurnos() {

        cola = new ColaTurnos();
        contador = 1;
    }

    public Turno generarTurno(
            TipoTurno tipo,
            Prioridad prioridad) {

        Turno turno =
                new Turno(
                        contador++,
                        tipo,
                        prioridad);

        cola.agregarTurno(turno);

        return turno;
    }

    public Turno llamarTurno() {

        Turno turno =
                cola.obtenerSiguiente();

        if(turno != null) {

            turno.setEstado(
                    EstadoTurno.EN_ATENCION);
        }

        return turno;
    }
}