package controllers;

import core.Controller;
import models.SistemaTurnosModel;
import views.ReporteView;

public class ReporteController extends Controller {

    private ReporteView view;
    private SistemaTurnosModel model;

    public ReporteController(SistemaTurnosModel model) {
        this.model = model;
    }

    @Override
    public void run() {
        view = new ReporteView(model);
        model.attach(view);
    }

    public ReporteView getView() { return view; }
}
