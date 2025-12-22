package cl.salmontt.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase utilitaria para la lectura de archivos planos.
 * Cumple con el Paso 3 de la EFT al gestionar datos externos.
 */
public class FileReader {

    /**
     * Lee un archivo delimitado y devuelve una lista de arreglos de String.
     * * @param ruta String con la ruta del archivo.
     * @param separador Caracter delimitador (ej: ";").
     * @param saltarCabecera Cantidad de líneas iniciales a ignorar.
     * @return Lista de arreglos con los datos segmentados.
     */
    public static List<String[]> leerCSV(String ruta, String separador, int saltarCabecera) {
        List<String[]> filas = new ArrayList<>();
        Path path = Paths.get(ruta); // Convierte el String en un objeto Path

        try (BufferedReader br = Files.newBufferedReader(path)) {
            String linea;
            int contador = 0;
            while ((linea = br.readLine()) != null) {
                // Salta las líneas de título o encabezado definidas
                if (contador++ < saltarCabecera) continue;

                // Ignora líneas vacías
                if (linea.isBlank()) continue;

                // Divide la línea y la agrega a la lista
                filas.add(linea.split(separador));
            }
        } catch (IOException e) {
            System.err.println("Error leyendo archivo: " + ruta + " - " + e.getMessage());
        }
        return filas;
    }
}