package excepciones;

/**
 * Excepción personalizada que se lanza cuando la palabra de una
 * posición del vector tiene menos de 4 letras.
 */
public class PalabraMuyCortaException extends Exception {

    public PalabraMuyCortaException(String mensaje) {
        super(mensaje);
    }
}
