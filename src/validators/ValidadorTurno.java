package validators;

import models.TipoTurno;

public class ValidadorTurno implements Validador<TipoTurno> {

    private String mensajeError;

    @Override
    public boolean esValido(TipoTurno tipo) {
        if (tipo == null) {
            mensajeError = "Debe seleccionar un tipo de turno.";
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
