package org.miguquis.music.dev_Interface;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileGestor {

    public static void fileExists(){
        try{
            Files.createDirectories(Paths.get("./data"));
            File yourFile = new File("./data/metadata.json");
            yourFile.createNewFile(); // if file already exists will do nothing
            FileOutputStream oFile = new FileOutputStream(yourFile, false);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
