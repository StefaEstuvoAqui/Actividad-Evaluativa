package excepciones;

/**
 * Excepción encadenada: envuelve la excepción del sistema (IOException o
 * FileNotFoundException) que ocurre al leer el archivo del poema.
 */
public class ErrorCargaPoemaException extends Exception {

    public ErrorCargaPoemaException(String mensaje) {
        super(mensaje);
    }

    // Constructor que recibe la excepción original como causa
    public ErrorCargaPoemaException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}
