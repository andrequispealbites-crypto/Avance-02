package models;

public class Cajero extends Empleado {

    public Cajero(
            int codigo,
            String nombre) {

        super(codigo, nombre);
    }

    public void atenderCliente() {

        System.out.println(
                "Cajero "
                        + nombre
                        + " atendiendo cliente");
    }
}