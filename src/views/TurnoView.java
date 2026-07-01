package views;

import javax.swing.*;
import java.awt.*;

import core.Model;
import core.View;
import models.Prioridad;
import models.SistemaTurnosModel;
import models.TipoTurno;
import models.Turno;

@SuppressWarnings("serial")
public class TurnoView extends JPanel implements View {

    private JButton btnGenerarTurno;
    private JButton btnCancelarTurno;
    private JComboBox<TipoTurno> cboTipo;
    private JComboBox<Prioridad> cboPrioridad;
    private JTextArea areaTurnos;
    private SistemaTurnosModel model;

    public TurnoView(SistemaTurnosModel model) {
        this.model = model;
        setLayout(new BorderLayout(10, 10));

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        cboTipo = new JComboBox<TipoTurno>(TipoTurno.values());
        cboPrioridad = new JComboBox<Prioridad>(Prioridad.values());
        btnGenerarTurno = new JButton("Generar Turno");
        btnCancelarTurno = new JButton("Cancelar último turno");

        panel.add(new JLabel("Tipo:"));
        panel.add(cboTipo);
        panel.add(new JLabel("Prioridad:"));
        panel.add(cboPrioridad);
        panel.add(btnGenerarTurno);
        panel.add(btnCancelarTurno);
        add(panel, BorderLayout.NORTH);

        areaTurnos = new JTextArea();
        areaTurnos.setEditable(false);
        add(new JScrollPane(areaTurnos), BorderLayout.CENTER);

        btnGenerarTurno.addActionListener(e -> model.generarTurno(
                (TipoTurno) cboTipo.getSelectedItem(),
                (Prioridad) cboPrioridad.getSelectedItem()));
        btnCancelarTurno.addActionListener(e -> model.cancelarUltimoTurno());
    }

    @Override
    public void update(Model model, Object data) {
        if (model instanceof SistemaTurnosModel) {
            SistemaTurnosModel sistema = (SistemaTurnosModel) model;
            StringBuilder sb = new StringBuilder();
            sb.append("Último evento: ").append(data).append("\n\n");
            for (Turno turno : sistema.getTurnos()) {
                sb.append(turno).append("\n");
            }
            areaTurnos.setText(sb.toString());
        }
    }
}
