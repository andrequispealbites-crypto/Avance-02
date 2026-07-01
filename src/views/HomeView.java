package views;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

import controllers.HomeController;
import core.Model;
import core.View;

@SuppressWarnings("serial")
public class HomeView extends JPanel implements View {

    private HomeController homeController;
    private JFrame mainFrame;

    private final static int MAIN_FRAME_WIDTH = 900;
    private final static int MAIN_FRAME_HEIGHT = 600;

    private final static int MAIN_FRAME_X = 100;
    private final static int MAIN_FRAME_Y = 100;

    public HomeView(
            HomeController homeController,
            JFrame mainFrame) {

        this.homeController = homeController;
        this.mainFrame = mainFrame;

        makeMainFrame();
        makeTabs();
    }

    @Override
    public void update(
            Model model,
            Object data) {}

    private void makeMainFrame() {

        mainFrame.setTitle(
                "Sistema de Gestión de Turnos Bancarios");

        mainFrame.setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE);

        mainFrame.setBounds(
                MAIN_FRAME_X,
                MAIN_FRAME_Y,
                MAIN_FRAME_WIDTH,
                MAIN_FRAME_HEIGHT);

        mainFrame.setMinimumSize(
                new Dimension(
                        MAIN_FRAME_WIDTH,
                        MAIN_FRAME_HEIGHT));

        setBorder(
                new EmptyBorder(
                        5,
                        5,
                        5,
                        5));

        setLayout(
                new BorderLayout());
    }

    private void makeTabs() {

        JTabbedPane tabbedPane =
                new JTabbedPane(
                        JTabbedPane.TOP);

        tabbedPane.addTab(
                "Clientes",
                homeController.getClienteView());

        tabbedPane.addTab(
                "Turnos",
                homeController.getTurnoView());

        tabbedPane.addTab(
                "Atención",
                homeController.getAtencionView());

        tabbedPane.addTab(
                "Empleados",
                homeController.getEmpleadoView());

        tabbedPane.addTab(
                "Historial",
                homeController.getHistorialView());

        tabbedPane.addTab(
                "Reportes",
                homeController.getReporteView());

        add(
                tabbedPane,
                BorderLayout.CENTER);
    }
}