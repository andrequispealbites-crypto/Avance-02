package models;

public class Ventanilla {

    private int numero;
    private boolean disponible;

    public Ventanilla(int numero) {

        this.numero = numero;
        this.disponible = true;
    }

    public int getNumero() {
        return numero;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void ocupar() {
        disponible = false;
    }

    public void liberar() {
        disponible = true;
    }
}