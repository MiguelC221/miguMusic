package org.miguquis.music.user_Interface;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;
import org.json.JSONArray;

import com.google.gson.Gson;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.miguquis.music.dev_Interface.FileGestor;
import org.miguquis.music.dev_Interface.Metadatos;

public class Main {
    public static void main(String[] args) {
        Main app = new Main();
        List<Metadatos> canciones = app.readCSV();
        

        //crear carpetas de metadata
        FileGestor fg = new FileGestor();
        fg.fileExists();

        //chequeo de carpeta analizada (implementación temprana)
        //True para analizar carpetas, FALSE para omitir
        boolean checkFolder = true;
        if (checkFolder) {
            ReadFolderJSON();
        }

        app.GetFiles(canciones);
        writeJSON(canciones);
    }

    public static void writeJSON(List<Metadatos> lista) {
        Gson gson = new Gson();
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

    public static void ReadFolderJSON() {
        Path carpetaMusica = Paths.get("E:\\Música");
        String archivoJSON = "./cache/metadata.json";

        JSONArray listaCanciones = new JSONArray();

        try {
            Files.walk(carpetaMusica)
                    .filter(Files::isRegularFile)
                    .filter(p -> p.toString().toLowerCase().endsWith(".mp3")
                            || p.toString().toLowerCase().endsWith(".flac")
                            || p.toString().toLowerCase().endsWith(".wav"))
                    .forEach(archivo -> {
                        try {
                            AudioFile audioFile = AudioFileIO.read(archivo.toFile());
                            Tag tag = audioFile.getTag();

                            String json = """
                                    {
                                        "titulo" : titulo,
                                        "artista" : artista,
                                        "album" : album,
                                        "genero" : genero,
                                        "anio" : anio,
                                        "ruta" : ruta,
                                    }
                                    """;

                            String titulo = tag.getFirst(FieldKey.TITLE);
                            String artista = (tag.getFirst(FieldKey.ARTIST));
                            String album = (tag.getFirst(FieldKey.ALBUM));
                            String genero = (tag.getFirst(FieldKey.GENRE));
                            String anio = (tag.getFirst(FieldKey.YEAR));
                            String rutaCompleta = (archivo.toAbsolutePath().toString());

                            JSONObject cancion = new JSONObject();
                            cancion.put("Titulo:", titulo);
                            cancion.put("Artista:", artista);
                            cancion.put("Album:", album);
                            cancion.put("Genero:", genero);
                            cancion.put("Anio", anio);
                            cancion.put("Ruta", rutaCompleta);

                            listaCanciones.put(cancion);

                        } catch (Exception e) {
                            System.err.println("No se pudo leer metadata de: " + archivo.getFileName());
                        }
                    });

            // Guardar el JSON en disco
            try (FileWriter file = new FileWriter(archivoJSON)) {
                file.write(listaCanciones.toString());
            }

            System.out.println("JSON generado en: " + archivoJSON);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}