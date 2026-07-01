package views;

import javax.swing.*;
import java.awt.*;

import core.Model;
import core.View;
import models.SistemaTurnosModel;
import models.Turno;
import strategy.EstrategiaLlegada;
import strategy.EstrategiaPrioridad;

@SuppressWarnings("serial")
public class AtencionView extends JPanel implements View {

    private JButton btnAtender;
    private JComboBox<String> cboEstrategia;
    private JTextArea areaAtencion;
    private SistemaTurnosModel model;

    public AtencionView(SistemaTurnosModel model) {
        this.model = model;
        setLayout(new BorderLayout(10, 10));

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        btnAtender = new JButton("Atender Siguiente");
        cboEstrategia = new JComboBox<String>();
        cboEstrategia.addItem("Prioridad preferencial");
        cboEstrategia.addItem("Orden de llegada");

        panel.add(new JLabel("Strategy:"));
        panel.add(cboEstrategia);
        panel.add(btnAtender);
        add(panel, BorderLayout.NORTH);

        areaAtencion = new JTextArea();
        areaAtencion.setEditable(false);
        add(new JScrollPane(areaAtencion), BorderLayout.CENTER);

        cboEstrategia.addActionListener(e -> cambiarEstrategia());
        btnAtender.addActionListener(e -> model.atenderSiguienteAsync());
    }

    private void cambiarEstrategia() {
        if (cboEstrategia.getSelectedIndex() == 0) {
            model.setEstrategia(new EstrategiaPrioridad());
        } else {
            model.setEstrategia(new EstrategiaLlegada());
        }
    }

    @Override
    public void update(Model model, Object data) {
        if (model instanceof SistemaTurnosModel) {
            SistemaTurnosModel sistema = (SistemaTurnosModel) model;
            StringBuilder sb = new StringBuilder();
            sb.append("Último evento: ").append(data).append("\n\n");
            sb.append("Turnos:\n");
            for (Turno turno : sistema.getTurnos()) {
                sb.append(turno).append("\n");
            }
            areaAtencion.setText(sb.toString());
        }
    }
}
