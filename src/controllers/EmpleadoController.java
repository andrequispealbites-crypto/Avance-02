package controllers;

import core.Controller;
import models.SistemaTurnosModel;
import views.EmpleadoView;

public class EmpleadoController extends Controller {

    private EmpleadoView view;
    private SistemaTurnosModel model;

    public EmpleadoController(SistemaTurnosModel model) {
        this.model = model;
    }

    @Override
    public void run() {
        view = new EmpleadoView(model);
        model.attach(view);
    }

    public EmpleadoView getView() {
        return view;
    }
}
