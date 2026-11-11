package excepciones;

/**
 * Excepción personalizada que se lanza cuando el usuario busca
 * una palabra que no existe dentro del vector del poema.
 */
public class PalabraNoEncontradaException extends Exception {

    public PalabraNoEncontradaException(String mensaje) {
        super(mensaje);
    }
}
