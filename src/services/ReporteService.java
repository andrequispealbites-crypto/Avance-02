package services;

import models.Reporte;
import models.SistemaTurnosModel;

public class ReporteService {

    private SistemaTurnosModel model;

    public ReporteService(SistemaTurnosModel model) {
        this.model = model;
    }

    public Reporte crearReporteGeneral() {
        return new Reporte("Reporte general de turnos", model.generarReporte());
    }
}
