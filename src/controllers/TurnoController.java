package controllers;

import core.Controller;
import models.SistemaTurnosModel;
import views.TurnoView;

public class TurnoController extends Controller {

    private TurnoView view;
    private SistemaTurnosModel model;

    public TurnoController(SistemaTurnosModel model) {
        this.model = model;
    }

    @Override
    public void run() {
        view = new TurnoView(model);
        model.attach(view);
    }

    public TurnoView getView() { return view; }
}
