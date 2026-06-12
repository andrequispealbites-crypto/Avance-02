package controllers;

import core.Controller;
import views.*;

public class HomeController extends Controller {

    private HomeView homeView;

    private ClienteController clienteController =
            new ClienteController();

    private TurnoController turnoController =
            new TurnoController();

    private AtencionController atencionController =
            new AtencionController();

    private ReporteController reporteController =
            new ReporteController();

    @Override
    public void run() {

        clienteController.run();

        turnoController.run();

        atencionController.run();

        reporteController.run();

        homeView =
                new HomeView(
                        this,
                        mainFrame);

        addView(
                "HomeView",
                homeView);

        loadView(
                "HomeView");

        mainFrame.setVisible(true);
    }

    public ClienteView getClienteView() {

        return clienteController.getView();
    }

    public TurnoView getTurnoView() {

        return turnoController.getView();
    }

    public AtencionView getAtencionView() {

        return atencionController.getView();
    }

    public ReporteView getReporteView() {

        return reporteController.getView();
    }
}