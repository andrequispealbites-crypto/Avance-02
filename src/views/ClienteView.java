package views;

import javax.swing.*;
import java.awt.*;

import core.Model;
import core.View;
import models.SistemaTurnosModel;

@SuppressWarnings("serial")
public class ClienteView extends JPanel implements View {

    private JTextField txtNombre;
    private JTextField txtDni;
    private JComboBox<String> cboTipo;
    private JButton btnRegistrar;
    private JTextArea areaClientes;
    private SistemaTurnosModel model;

    public ClienteView(SistemaTurnosModel model) {
        this.model = model;
        setLayout(new BorderLayout(10, 10));

        JPanel formulario = new JPanel(new GridLayout(4, 2, 10, 10));
        formulario.add(new JLabel("Nombre"));
        txtNombre = new JTextField();
        formulario.add(txtNombre);

        formulario.add(new JLabel("DNI"));
        txtDni = new JTextField();
        formulario.add(txtDni);

        formulario.add(new JLabel("Tipo Cliente"));
        cboTipo = new JComboBox<String>();
        cboTipo.addItem("Normal");
        cboTipo.addItem("Preferencial");
        formulario.add(cboTipo);

        btnRegistrar = new JButton("Registrar Cliente");
        formulario.add(new JLabel(""));
        formulario.add(btnRegistrar);
        add(formulario, BorderLayout.NORTH);

        areaClientes = new JTextArea();
        areaClientes.setEditable(false);
        add(new JScrollPane(areaClientes), BorderLayout.CENTER);

        btnRegistrar.addActionListener(e -> registrarCliente());
    }

    private void registrarCliente() {
        String nombre = txtNombre.getText().trim();
        String dni = txtDni.getText().trim();
        boolean preferencial = cboTipo.getSelectedItem().toString().equals("Preferencial");

        if (nombre.isEmpty() || dni.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Complete nombre y DNI");
            return;
        }

        model.registrarCliente(nombre, dni, preferencial);
        txtNombre.setText("");
        txtDni.setText("");
    }

    @Override
    public void update(Model model, Object data) {
        if (model instanceof SistemaTurnosModel) {
            SistemaTurnosModel sistema = (SistemaTurnosModel) model;
            StringBuilder sb = new StringBuilder();
            sb.append("Último evento: ").append(data).append("\n\n");
            for (models.Cliente cliente : sistema.getClientes()) {
                sb.append("Código: ").append(cliente.getCodigo())
                        .append(" | Nombre: ").append(cliente.getNombre())
                        .append(" | DNI: ").append(cliente.getDocumento().getNumero())
                        .append("\n");
            }
            areaClientes.setText(sb.toString());
        }
    }
}
