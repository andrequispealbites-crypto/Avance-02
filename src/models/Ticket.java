package models;

import java.time.LocalDateTime;

public class Ticket {

    private Turno turno;
    private LocalDateTime fechaEmision;

    public Ticket(Turno turno) {
        this.turno = turno;
        this.fechaEmision = LocalDateTime.now();
    }

    public Turno getTurno() {
        return turno;
    }

    public LocalDateTime getFechaEmision() {
        return fechaEmision;
    }

    public String imprimir() {
        return "TICKET\nTurno: " + turno + "\nEmitido: " + fechaEmision;
    }
}
