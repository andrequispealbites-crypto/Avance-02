package models;

public class DocumentoIdentidad {

    private String numero;
    private String tipo;

    public DocumentoIdentidad(
            String numero,
            String tipo) {

        this.numero = numero;
        this.tipo = tipo;
    }

    public String getNumero() {
        return numero;
    }

    public String getTipo() {
        return tipo;
    }
}