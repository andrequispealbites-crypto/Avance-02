package controllers;

import core.Controller;
import views.AtencionView;

public class AtencionController extends Controller {

    private AtencionView view;

    @Override
    public void run() {

        view = new AtencionView();
    }

    public AtencionView getView() {

        return view;
    }
}