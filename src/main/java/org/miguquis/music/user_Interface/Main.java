package org.miguquis.music.dev_interface;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.miguquis.music.user_Interface.Metadatos;

public class Main {


    public static void main(String[] args) {
        Main app = new Main();
        List<Metadatos> lista = app.readCSV();

        List listaReproduccion;
        List reproducirAleatorio;
        List reproduccion;
        List carpetas;

        //chequeo de carpeta analizada (implementación temprana)
        //True para analizar carpetas, FALSE para omitir
        boolean checkFolder = true;
        if (checkFolder) {
            ReadFolder();
        }

        app.GetFiles(lista);

    }

    public void GetFiles(List<Metadatos> list) {
        for (Metadatos m : list) {
            System.out.println("Título: " + m.getTitulo());
        }

    }

    public List<Metadatos> readCSV() {
        List<Metadatos> metadataList = new ArrayList<>();

        String csv = "./resources/metadata.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        try {
            br = new BufferedReader(new FileReader(csv));

            while ((line = br.readLine()) != null) {
                String[] data = line.split(cvsSplitBy);
                // data[0]: titile, data[1]: artist, data[2]: Album, data[3]: año, data[4]: genero, data[5]: ruta archivo
                Metadatos m = new Metadatos(data[0], data[1], data[2], data[3], data[4], data[5]);
                metadataList.add(m);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return metadataList;
    }

    public static void ReadFolder() {
        // Leer y guardar en csv los archivos
        Path carpetaMusica = Paths.get("E:\\Música");
        String archivoCSV = "./resources/metadata.csv";

        try (FileWriter writer = new FileWriter(archivoCSV)) {
            // Note: Año = var: anio
            writer.append("Titulo,Artista,Album,Género,Año,Ruta\n");

            Files.walk(carpetaMusica)
                    .filter(Files::isRegularFile)
                    .filter(p -> p.toString().toLowerCase().endsWith(".mp3")
                            || p.toString().toLowerCase().endsWith(".flac")
                            || p.toString().toLowerCase().endsWith(".wav"))
                    .forEach(archivo -> {
                        try {
                            AudioFile audioFile = AudioFileIO.read(archivo.toFile());
                            Tag tag = audioFile.getTag();

                            String titulo = limpiarCSV(tag.getFirst(FieldKey.TITLE));
                            String artista = limpiarCSV(tag.getFirst(FieldKey.ARTIST));
                            String album = limpiarCSV(tag.getFirst(FieldKey.ALBUM));
                            String genero = limpiarCSV(tag.getFirst(FieldKey.GENRE));
                            String anio = limpiarCSV(tag.getFirst(FieldKey.YEAR));
                            String rutaCompleta = limpiarCSV(archivo.toAbsolutePath().toString());

                            try {
                                writer.append(titulo).append(",")
                                        .append(artista).append(",")
                                        .append(album).append(",")
                                        .append(anio).append(",")
                                        .append(genero).append(",")
                                        .append(rutaCompleta).append("\n");
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        } catch (Exception e) {
                            System.err.println("No se pudo leer metadata de: " + archivo.getFileName());
                        }
                    });

            System.out.println("CSV generado en: " + archivoCSV);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Evitar problemas con comas y saltos de línea en el CSV
    private static String limpiarCSV(String texto) {
        if (texto == null)
            return "";
        return texto.replace(",", " ").replace("\n", " ").replace("\r", " ");
    }
}