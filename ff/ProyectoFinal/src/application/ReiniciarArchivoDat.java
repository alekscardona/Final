package application;

import java.io.File;
import java.io.IOException;

public class ReiniciarArchivoDat {

    public static void main(String[] args) {
        // Obtener la ruta del directorio de trabajo del proyecto en Eclipse
        String directorioProyecto = System.getProperty("user.dir");
        
        // Nombre del archivo .dat con ruta relativa al directorio del proyecto
        String nombreArchivo = directorioProyecto + "/src/archivos/clientes.dat";

        // Reiniciar el archivo .dat
        reiniciarArchivo(nombreArchivo);
    }

    // M�todo para reiniciar (borrar todo el contenido) de un archivo .dat
    private static void reiniciarArchivo(String nombreArchivo) {
        File archivo = new File(nombreArchivo);

        try {
            // Eliminar el archivo existente
            if (archivo.exists()) {
                if (archivo.delete()) {
                    System.out.println("Archivo " + nombreArchivo + " eliminado correctamente.");
                } else {
                    System.out.println("No se pudo eliminar el archivo " + nombreArchivo);
                    return;
                }
            }

            // Crear un nuevo archivo
            if (archivo.createNewFile()) {
                System.out.println("Archivo " + nombreArchivo + " reiniciado correctamente.");
            } else {
                System.out.println("No se pudo crear el archivo " + nombreArchivo);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
