package excepciones;

/**
 * Excepción envuelve la excepción del sistema
 */
public class ErrorCargaPoemaException extends Exception {

    public ErrorCargaPoemaException(String mensaje) {
        super(mensaje);
    }

    // Constructor que recibe la excepción original
    public ErrorCargaPoemaException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}
