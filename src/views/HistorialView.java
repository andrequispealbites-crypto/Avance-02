package views;

import core.Model;
import core.View;
import models.Atencion;
import models.SistemaTurnosModel;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class HistorialView extends JPanel implements View {

    private SistemaTurnosModel model;
    private JTextArea areaHistorial;
    private JButton btnActualizar;

    public HistorialView(SistemaTurnosModel model) {
        this.model = model;
        setLayout(new BorderLayout(10, 10));

        JPanel superior = new JPanel(new FlowLayout(FlowLayout.LEFT));
        btnActualizar = new JButton("Actualizar Historial");
        superior.add(new JLabel("Historial y seguimiento de atenciones"));
        superior.add(btnActualizar);
        add(superior, BorderLayout.NORTH);

        areaHistorial = new JTextArea();
        areaHistorial.setEditable(false);
        add(new JScrollPane(areaHistorial), BorderLayout.CENTER);

        btnActualizar.addActionListener(e -> refrescar(model.getUltimoMensaje()));
    }

    private void refrescar(Object data) {
        StringBuilder sb = new StringBuilder();
        sb.append("Último evento: ").append(data).append("\n\n");
        sb.append("HISTORIAL DE ATENCIONES\n");
        sb.append("-----------------------\n");
        if (model.getAtenciones().isEmpty()) {
            sb.append("Aún no hay atenciones finalizadas.\n");
        } else {
            for (Atencion atencion : model.getAtenciones()) {
                sb.append(atencion).append("\n");
            }
        }
        areaHistorial.setText(sb.toString());
    }

    @Override
    public void update(Model model, Object data) {
        if (model instanceof SistemaTurnosModel) {
            refrescar(data);
        }
    }
}
