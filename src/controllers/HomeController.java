package controllers;

import core.Controller;
import models.SistemaTurnosModel;
import views.*;

public class HomeController extends Controller {

    private HomeView homeView;
    private SistemaTurnosModel model;

    private ClienteController clienteController;
    private TurnoController turnoController;
    private AtencionController atencionController;
    private ReporteController reporteController;
    private EmpleadoController empleadoController;
    private HistorialController historialController;

    @Override
    public void run() {
        model = new SistemaTurnosModel();

        clienteController = new ClienteController(model);
        turnoController = new TurnoController(model);
        atencionController = new AtencionController(model);
        reporteController = new ReporteController(model);
        empleadoController = new EmpleadoController(model);
        historialController = new HistorialController(model);

        clienteController.run();
        turnoController.run();
        atencionController.run();
        reporteController.run();
        empleadoController.run();
        historialController.run();

        homeView = new HomeView(this, mainFrame);
        model.attach(homeView);

        addView("HomeView", homeView);
        loadView("HomeView");

        mainFrame.setVisible(true);
    }

    public ClienteView getClienteView() { return clienteController.getView(); }
    public TurnoView getTurnoView() { return turnoController.getView(); }
    public AtencionView getAtencionView() { return atencionController.getView(); }
    public ReporteView getReporteView() { return reporteController.getView(); }
    public EmpleadoView getEmpleadoView() { return empleadoController.getView(); }
    public HistorialView getHistorialView() { return historialController.getView(); }
}
