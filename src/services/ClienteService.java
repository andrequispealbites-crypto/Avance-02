package services;

import models.Cliente;
import models.SistemaTurnosModel;
import validators.ValidadorCliente;

public class ClienteService {

    private SistemaTurnosModel model;
    private ValidadorCliente validadorCliente;

    public ClienteService(SistemaTurnosModel model) {
        this.model = model;
        this.validadorCliente = new ValidadorCliente();
    }

    public Cliente registrar(String nombre, String dni, boolean preferencial) {
        if (!validadorCliente.esValido(nombre)) {
            throw new IllegalArgumentException(validadorCliente.getMensajeError());
        }
        return model.registrarCliente(nombre, dni, preferencial);
    }
}
