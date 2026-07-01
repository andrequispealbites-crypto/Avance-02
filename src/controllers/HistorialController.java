package controllers;

import core.Controller;
import models.SistemaTurnosModel;
import views.HistorialView;

public class HistorialController extends Controller {

    private HistorialView view;
    private SistemaTurnosModel model;

    public HistorialController(SistemaTurnosModel model) {
        this.model = model;
    }

    @Override
    public void run() {
        view = new HistorialView(model);
        model.attach(view);
    }

    public HistorialView getView() {
        return view;
    }
}
