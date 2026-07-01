package validators;

public class ValidadorCliente implements Validador<String> {

    private String mensajeError;

    @Override
    public boolean esValido(String nombre) {
        if (nombre == null || nombre.trim().length() < 3) {
            mensajeError = "El nombre del cliente debe tener mínimo 3 caracteres.";
            return false;
        }
        mensajeError = "";
        return true;
    }

    @Override
    public String getMensajeError() {
        return mensajeError;
    }
}
