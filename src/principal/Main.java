package principal;

import excepciones.ErrorCargaPoemaException;
import excepciones.PalabraMuyCortaException;
import excepciones.PalabraNoEncontradaException;
import excepciones.PosicionPoemaInvalidaException;
import modelo.Poema;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Clase principal con el menú por consola.
 */
public class Main {

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        Poema poema = new Poema();

        // Carga inicial del poema (usa la excepción encadenada ErrorCargaPoemaException)
        try {
            poema.cargarPoema("poema.txt");
            System.out.println("Poema cargado correctamente. Total de palabras: " + poema.getCantidadPalabras());
        } catch (ErrorCargaPoemaException e) {
            System.out.println("Error: " + e.getMessage());
            System.out.println("Causa original: " + e.getCause());
            System.out.println("El programa no puede continuar sin el poema.");
            return;
        }

        int opcion = -1;
        while (opcion != 0) {
            System.out.println();
            System.out.println("========== MENÚ ==========");
            System.out.println("1. Mostrar todas las palabras");
            System.out.println("2. Abrir un archivo por nombre (FileNotFoundException)");
            System.out.println("3. Consultar palabra por posición (ArrayIndexOutOfBounds / InputMismatch)");
            System.out.println("4. Buscar una palabra (PalabraNoEncontradaException)");
            System.out.println("5. Consultar palabra de 4 letras o más (PalabraMuyCortaException)");
            System.out.println("6. Cargar poema desde otro archivo (ErrorCargaPoemaException)");
            System.out.println("7. Modificar palabra por posición (PosicionPoemaInvalidaException)");
            System.out.println("0. Salir");
            System.out.print("Elija una opción: ");

            try {
                opcion = teclado.nextInt();
                teclado.nextLine(); // limpiar el salto de línea
            } catch (InputMismatchException e) {
                System.out.println("Debe escribir un número.");
                teclado.nextLine(); // descartar lo que escribió mal
                continue;
            }

            switch (opcion) {
                case 1:
                    poema.mostrarPalabras();
                    System.out.println("Total de palabras: " + poema.getCantidadPalabras());
                    break;

                case 2:
                    // A. Excepción nativa: FileNotFoundException
                    System.out.print("Escriba el nombre del archivo a abrir: ");
                    String nombre = teclado.nextLine();
                    try {
                        Poema otro = new Poema();
                        otro.leerArchivo(nombre);
                        System.out.println("Archivo abierto. Tiene " + otro.getCantidadPalabras() + " palabras.");
                    } catch (FileNotFoundException e) {
                        System.out.println("FileNotFoundException capturada: " + e.getMessage());
                    } catch (IOException e) {
                        System.out.println("Error de lectura: " + e.getMessage());
                    } finally {
                        System.out.println("Fin del intento de apertura del archivo.");
                    }
                    break;

                case 3:
                    // A. Excepciones nativas: ArrayIndexOutOfBoundsException e InputMismatchException
                    System.out.print("Escriba la posición (0 a " + (poema.getCantidadPalabras() - 1) + "): ");
                    try {
                        int posicion = teclado.nextInt();
                        teclado.nextLine();
                        System.out.println("Palabra: " + poema.obtenerPalabra(posicion));
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("ArrayIndexOutOfBoundsException capturada: " + e.getMessage());
                    } catch (InputMismatchException e) {
                        System.out.println("InputMismatchException capturada: debe escribir un número entero.");
                        teclado.nextLine();
                    } finally {
                        System.out.println("Fin de la consulta por posición.");
                    }
                    break;

                case 4:
                    // B. Excepción personalizada: PalabraNoEncontradaException
                    System.out.print("Escriba la palabra a buscar: ");
                    String buscada = teclado.nextLine();
                    try {
                        int pos = poema.buscarPalabra(buscada);
                        System.out.println("La palabra está en la posición " + pos);
                    } catch (PalabraNoEncontradaException e) {
                        System.out.println("PalabraNoEncontradaException capturada: " + e.getMessage());
                    } finally {
                        System.out.println("Fin de la búsqueda.");
                    }
                    break;

                case 5:
                    // B. Excepción personalizada: PalabraMuyCortaException
                    System.out.print("Escriba la posición: ");
                    try {
                        int posicion = teclado.nextInt();
                        teclado.nextLine();
                        System.out.println("Palabra: " + poema.obtenerPalabraLarga(posicion));
                    } catch (PalabraMuyCortaException e) {
                        System.out.println("PalabraMuyCortaException capturada: " + e.getMessage());
                    } catch (PosicionPoemaInvalidaException e) {
                        System.out.println("PosicionPoemaInvalidaException capturada: " + e.getMessage());
                        System.out.println("Causa original: " + e.getCause());
                    } catch (InputMismatchException e) {
                        System.out.println("Debe escribir un número entero.");
                        teclado.nextLine();
                    } finally {
                        System.out.println("Fin de la consulta de longitud.");
                    }
                    break;

                case 6:
                    // C. Excepción encadenada: ErrorCargaPoemaException
                    System.out.print("Escriba el nombre del archivo a cargar: ");
                    String archivo = teclado.nextLine();
                    try {
                        Poema otro = new Poema();
                        otro.cargarPoema(archivo);
                        System.out.println("Poema cargado con " + otro.getCantidadPalabras() + " palabras.");
                    } catch (ErrorCargaPoemaException e) {
                        System.out.println("ErrorCargaPoemaException capturada: " + e.getMessage());
                        System.out.println("Causa original: " + e.getCause());
                    } finally {
                        System.out.println("Fin de la carga del archivo.");
                    }
                    break;

                case 7:
                    // C. Excepción encadenada: PosicionPoemaInvalidaException
                    try {
                        System.out.print("Escriba la posición a modificar: ");
                        int posicion = teclado.nextInt();
                        teclado.nextLine();
                        System.out.print("Escriba la nueva palabra: ");
                        String nueva = teclado.nextLine();
                        poema.modificarPalabra(posicion, nueva);
                        System.out.println("Palabra modificada correctamente.");
                    } catch (PosicionPoemaInvalidaException e) {
                        System.out.println("PosicionPoemaInvalidaException capturada: " + e.getMessage());
                        System.out.println("Causa original: " + e.getCause());
                    } catch (InputMismatchException e) {
                        System.out.println("Debe escribir un número entero.");
                        teclado.nextLine();
                    } finally {
                        System.out.println("Fin de la modificación.");
                    }
                    break;

                case 0:
                    System.out.println("¡Hasta luego!");
                    break;

                default:
                    System.out.println("Opción no válida.");
            }
        }

        teclado.close();
    }
}
