package modelo;

import excepciones.ErrorCargaPoemaException;
import excepciones.PalabraMuyCortaException;
import excepciones.PalabraNoEncontradaException;
import excepciones.PosicionPoemaInvalidaException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * Clase que representa el poema. Lee el archivo de texto y guarda
 * cada palabra en un vector de tipo String.
 */
public class Poema {

    // Vector donde se guardan las palabras del poema
    private String[] palabras;

    /**
     * Lee el archivo y llena el vector de palabras.
     * Este mé NO captura las excepciones: las deja pasar
     */
    public void leerArchivo(String nombreArchivo) throws FileNotFoundException, IOException {
        // 1. Leer el texto del archivo línea por línea
        BufferedReader lector = new BufferedReader(new FileReader(nombreArchivo, StandardCharsets.UTF_8));
        String texto = "";
        String linea;
        while ((linea = lector.readLine()) != null) {
            texto = texto + linea + " ";
        }
        lector.close();

        // 2. Limpiar los signos de puntuación (comas y puntos)
        texto = texto.replace(",", "");
        texto = texto.replace(".", "");
        texto = texto.trim();

        // 3. Separar el texto por espacios
        String[] partes = texto.split("\\s+");

        // 4. Contar cuántas palabras hay
        int contador = 0;
        for (int i = 0; i < partes.length; i++) {
            if (!partes[i].isEmpty()) {
                contador++;
            }
        }

        // 5. Crear el vector con el tamaño exacto y guardar las palabras en orden
        palabras = new String[contador];
        int posicion = 0;
        for (int i = 0; i < partes.length; i++) {
            if (!partes[i].isEmpty()) {
                palabras[posicion] = partes[i];
                posicion++;
            }
        }
    }

    /**
     * Carga el poema usando una excepción.
     * Captura la excepción del sistema y lanza una propia con la original como causa.
     */
    public void cargarPoema(String nombreArchivo) throws ErrorCargaPoemaException {
        try {
            leerArchivo(nombreArchivo);
        } catch (FileNotFoundException e) {
            throw new ErrorCargaPoemaException("No se encontró el archivo del poema: " + nombreArchivo, e);
        } catch (IOException e) {
            throw new ErrorCargaPoemaException("Ocurrió un error al leer el archivo del poema", e);
        }
    }

    /**
     * Devuelve la palabra de una posición
     * Si la posición no existe, Java lanza ArrayIndexOutOfBoundsException
     */
    public String obtenerPalabra(int posicion) {
        return palabras[posicion];
    }

    /**
     * Busca una palabra en el vector y devuelve su posición
     * Si no existe, se lanza la excepción personalizada PalabraNoEncontradaException
     */
    public int buscarPalabra(String palabraBuscada) throws PalabraNoEncontradaException {
        for (int i = 0; i < palabras.length; i++) {
            if (palabras[i].equalsIgnoreCase(palabraBuscada)) {
                return i;
            }
        }
        throw new PalabraNoEncontradaException("La palabra \"" + palabraBuscada + "\" no existe en el poema");
    }

    /**
     * Devuelve la palabra de una posición solo si tiene 4 letras o más
     * Si tiene menos de 4 letras, se lanza PalabraMuyCortaException
     * Si la posición no existe, se lanza PosicionPoemaInvalidaException
     */
    public String obtenerPalabraLarga(int posicion) throws PalabraMuyCortaException, PosicionPoemaInvalidaException {
        String palabra;
        try {
            palabra = palabras[posicion];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new PosicionPoemaInvalidaException("La posición " + posicion + " no existe en el poema", e);
        }

        if (palabra.length() < 4) {
            throw new PalabraMuyCortaException("La palabra \"" + palabra + "\" tiene menos de 4 letras");
        }
        return palabra;
    }

    /**
     * Cambia la palabra de una posición por una nueva.
     * Captura ArrayIndexOutOfBoundsException y lanza PosicionPoemaInvalidaException
     */
    public void modificarPalabra(int posicion, String nuevaPalabra) throws PosicionPoemaInvalidaException {
        try {
            palabras[posicion] = nuevaPalabra;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new PosicionPoemaInvalidaException("No se puede modificar la posición " + posicion, e);
        }
    }

    /** Devuelve la cantidad de palabras del poema. */
    public int getCantidadPalabras() {
        return palabras.length;
    }

    /** Muestra todas las palabras con su posición. */
    public void mostrarPalabras() {
        for (int i = 0; i < palabras.length; i++) {
            System.out.println("[" + i + "] " + palabras[i]);
        }
    }
}
