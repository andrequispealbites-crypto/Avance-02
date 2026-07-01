package models;

import java.util.ArrayList;
import java.util.List;

public class HistorialAtencion {

    private List<Atencion> historial;

    public HistorialAtencion() {

        historial =
                new ArrayList<>();
    }

    public void agregar(
            Atencion atencion) {

        historial.add(atencion);
    }

    public List<Atencion> getHistorial() {

        return historial;
    }
}