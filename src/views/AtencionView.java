package views;

import javax.swing.*;
import java.awt.*;

import core.Model;
import core.View;

@SuppressWarnings("serial")
public class AtencionView extends JPanel implements View {

    private JButton btnAtender;

    private JTextArea areaAtencion;

    public AtencionView() {

        setLayout(
                new BorderLayout());

        btnAtender =
                new JButton(
                        "Atender Siguiente");

        add(
                btnAtender,
                BorderLayout.NORTH);

        areaAtencion =
                new JTextArea();

        add(
                new JScrollPane(
                        areaAtencion),
                BorderLayout.CENTER);
    }

    @Override
    public void update(
            Model model,
            Object data) {}
}