package views;

import javax.swing.*;
import java.awt.*;

import core.Model;
import core.View;
import models.SistemaTurnosModel;

@SuppressWarnings("serial")
public class ReporteView extends JPanel implements View {

    private JTextArea txtReporte;
    private SistemaTurnosModel model;

    public ReporteView(SistemaTurnosModel model) {
        this.model = model;
        setLayout(new BorderLayout());
        txtReporte = new JTextArea();
        txtReporte.setEditable(false);
        add(new JScrollPane(txtReporte), BorderLayout.CENTER);
    }

    @Override
    public void update(Model model, Object data) {
        if (model instanceof SistemaTurnosModel) {
            txtReporte.setText(((SistemaTurnosModel) model).generarReporte());
        }
    }
}
