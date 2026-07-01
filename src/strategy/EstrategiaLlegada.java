package strategy;

import java.util.List;
import models.EstadoTurno;
import models.Turno;

public class EstrategiaLlegada implements TurnoStrategy {

    @Override
    public Turno seleccionarSiguiente(List<Turno> turnos) {
        for (Turno turno : turnos) {
            if (turno.getEstado() == EstadoTurno.PENDIENTE) {
                return turno;
            }
        }
        return null;
    }
}
