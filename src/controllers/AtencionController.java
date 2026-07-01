package controllers;

import core.Controller;
import models.SistemaTurnosModel;
import views.AtencionView;

public class AtencionController extends Controller {

    private AtencionView view;
    private SistemaTurnosModel model;

    public AtencionController(SistemaTurnosModel model) {
        this.model = model;
    }

    @Override
    public void run() {
        view = new AtencionView(model);
        model.attach(view);
    }

    public AtencionView getView() { return view; }
}
