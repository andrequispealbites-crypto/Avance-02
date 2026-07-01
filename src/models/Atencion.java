package models;

import java.time.LocalDateTime;

public class Atencion {

    private Turno turno;
    private Cajero cajero;

    private LocalDateTime inicio;
    private LocalDateTime fin;

    public Atencion(
            Turno turno,
            Cajero cajero) {

        this.turno = turno;
        this.cajero = cajero;
    }

    public void iniciar() {

        inicio =
                LocalDateTime.now();
    }

    public void finalizar() {

        fin =
                LocalDateTime.now();

        turno.setEstado(
                EstadoTurno.FINALIZADO);
    }

    public Turno getTurno() {
        return turno;
    }

    public Cajero getCajero() {
        return cajero;
    }

    public LocalDateTime getInicio() {
        return inicio;
    }

    public LocalDateTime getFin() {
        return fin;
    }

    @Override
    public String toString() {
        return "Atencion{" +
                "turno=" + turno +
                ", cajero=" + cajero.getNombre() +
                ", inicio=" + inicio +
                ", fin=" + fin +
                '}';
    }
}