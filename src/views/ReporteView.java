package views;

import javax.swing.*;
import java.awt.*;

import core.Model;
import core.View;

@SuppressWarnings("serial")
public class ReporteView extends JPanel implements View {

    private JTextArea txtReporte;

    public ReporteView() {

        setLayout(
                new BorderLayout());

        txtReporte =
                new JTextArea();

        add(
                new JScrollPane(
                        txtReporte),
                BorderLayout.CENTER);
    }

    @Override
    public void update(
            Model model,
            Object data) {}
}