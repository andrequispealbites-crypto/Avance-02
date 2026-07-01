package controllers;

import core.Controller;
import models.SistemaTurnosModel;
import views.ClienteView;

public class ClienteController extends Controller {

    private ClienteView view;
    private SistemaTurnosModel model;

    public ClienteController(SistemaTurnosModel model) {
        this.model = model;
    }

    @Override
    public void run() {
        view = new ClienteView(model);
        model.attach(view);
    }

    public ClienteView getView() { return view; }
}
