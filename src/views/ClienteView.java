package views;

import javax.swing.*;
import java.awt.*;

import core.Model;
import core.View;

@SuppressWarnings("serial")
public class ClienteView extends JPanel implements View {

    private JTextField txtCodigo;
    private JTextField txtNombre;
    private JTextField txtDni;

    private JComboBox<String> cboTipo;

    private JButton btnRegistrar;

    public ClienteView() {

        setLayout(
                new GridLayout(
                        5,
                        2,
                        10,
                        10));

        add(new JLabel("Código"));
        txtCodigo = new JTextField();
        add(txtCodigo);

        add(new JLabel("Nombre"));
        txtNombre = new JTextField();
        add(txtNombre);

        add(new JLabel("DNI"));
        txtDni = new JTextField();
        add(txtDni);

        add(new JLabel("Tipo Cliente"));

        cboTipo =
                new JComboBox<>();

        cboTipo.addItem("Normal");
        cboTipo.addItem("Preferencial");

        add(cboTipo);

        btnRegistrar =
                new JButton(
                        "Registrar Cliente");

        add(btnRegistrar);
    }

    @Override
    public void update(
            Model model,
            Object data) {}
}