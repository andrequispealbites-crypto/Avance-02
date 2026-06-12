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
}