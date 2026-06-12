package views;

import javax.swing.*;
import java.awt.*;

import core.Model;
import core.View;

@SuppressWarnings("serial")
public class TurnoView extends JPanel implements View {

    private JButton btnGenerarTurno;

    private JTextArea areaTurnos;

    public TurnoView() {

        setLayout(
                new BorderLayout());

        btnGenerarTurno =
                new JButton(
                        "Generar Turno");

        add(
                btnGenerarTurno,
                BorderLayout.NORTH);

        areaTurnos =
                new JTextArea();

        add(
                new JScrollPane(
                        areaTurnos),
                BorderLayout.CENTER);
    }

    @Override
    public void update(
            Model model,
            Object data) {}
}