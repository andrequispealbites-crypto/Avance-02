package strategy;

import java.util.List;
import models.Turno;

public interface TurnoStrategy {

    Turno seleccionarSiguiente(List<Turno> turnos);
}
