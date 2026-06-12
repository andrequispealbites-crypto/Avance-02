package controllers;

import core.Controller;
import views.ReporteView;

public class ReporteController extends Controller {

    private ReporteView view;

    @Override
    public void run() {

        view = new ReporteView();
    }

    public ReporteView getView() {

        return view;
    }
}