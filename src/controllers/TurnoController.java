package controllers;

import core.Controller;
import views.TurnoView;

public class TurnoController extends Controller {

    private TurnoView view;

    @Override
    public void run() {

        view = new TurnoView();
    }

    public TurnoView getView() {

        return view;
    }
}