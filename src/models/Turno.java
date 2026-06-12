package models;

import java.time.LocalDateTime;

public class Turno {

    private int idTurno;
    private TipoTurno tipo;
    private EstadoTurno estado;
    private Prioridad prioridad;
    private LocalDateTime fecha;

    public Turno(
            int idTurno,
            TipoTurno tipo,
            Prioridad prioridad) {

        this.idTurno = idTurno;
        this.tipo = tipo;
        this.prioridad = prioridad;
        this.estado = EstadoTurno.PENDIENTE;
        this.fecha = LocalDateTime.now();
    }

    public int getIdTurno() {
        return idTurno;
    }

    public TipoTurno getTipo() {
        return tipo;
    }

    public EstadoTurno getEstado() {
        return estado;
    }

    public Prioridad getPrioridad() {
        return prioridad;
    }

    public void setEstado(
            EstadoTurno estado) {

        this.estado = estado;
    }
}