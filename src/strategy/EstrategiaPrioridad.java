package strategy;

import java.util.List;
import models.EstadoTurno;
import models.Prioridad;
import models.Turno;

public class EstrategiaPrioridad implements TurnoStrategy {

    @Override
    public Turno seleccionarSiguiente(List<Turno> turnos) {
        Turno normal = null;

        for (Turno turno : turnos) {
            if (turno.getEstado() == EstadoTurno.PENDIENTE) {
                if (turno.getPrioridad() == Prioridad.PREFERENCIAL) {
                    return turno;
                }
                if (normal == null) {
                    normal = turno;
                }
            }
        }
        return normal;
    }
}
