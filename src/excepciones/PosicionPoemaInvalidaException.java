package excepciones;

/**
 * Excepción envuelve la ArrayIndexOutOfBoundsException que ocurre
 * al intentar acceder o modificar una posición inválida del vector.
 */
public class PosicionPoemaInvalidaException extends Exception {

    public PosicionPoemaInvalidaException(String mensaje) {
        super(mensaje);
    }

    // Constructor que recibe la excepción original
    public PosicionPoemaInvalidaException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}
