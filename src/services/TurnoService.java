package services;

import models.Prioridad;
import models.SistemaTurnosModel;
import models.Ticket;
import models.TipoTurno;
import models.Turno;
import validators.ValidadorTurno;

public class TurnoService {

    private SistemaTurnosModel model;
    private ValidadorTurno validadorTurno;

    public TurnoService(SistemaTurnosModel model) {
        this.model = model;
        this.validadorTurno = new ValidadorTurno();
    }

    public Ticket generarTicket(TipoTurno tipo, Prioridad prioridad) {
        if (!validadorTurno.esValido(tipo)) {
            throw new IllegalArgumentException(validadorTurno.getMensajeError());
        }
        Turno turno = model.generarTurno(tipo, prioridad);
        return new Ticket(turno);
    }
}
