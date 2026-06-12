package controllers;

import core.Controller;
import views.ClienteView;

public class ClienteController extends Controller {

    private ClienteView view;

    @Override
    public void run() {

        view = new ClienteView();
    }

    public ClienteView getView() {

        return view;
    }
}