package models;

public class ClientePreferencial
        extends Cliente {

    private String categoria;

    public ClientePreferencial(
            int codigo,
            String nombre,
            DocumentoIdentidad documento,
            String categoria) {

        super(
                codigo,
                nombre,
                documento);

        this.categoria = categoria;
    }

    public String getCategoria() {
        return categoria;
    }
}