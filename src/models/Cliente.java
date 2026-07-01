package models;

public class Cliente {

    private int codigo;
    private String nombre;
    private DocumentoIdentidad documento;

    public Cliente(
            int codigo,
            String nombre,
            DocumentoIdentidad documento) {

        this.codigo = codigo;
        this.nombre = nombre;
        this.documento = documento;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public DocumentoIdentidad getDocumento() {
        return documento;
    }

    @Override
    public String toString() {
        return nombre;
    }
}