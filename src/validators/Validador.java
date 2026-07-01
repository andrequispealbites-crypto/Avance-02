package validators;

public interface Validador<T> {
    boolean esValido(T dato);
    String getMensajeError();
}
