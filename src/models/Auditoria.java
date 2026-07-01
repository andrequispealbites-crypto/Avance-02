package models;

import java.io.FileWriter;
import java.time.LocalDateTime;

public class Auditoria {

    public static void registrar(
            String accion) {

        try {

            FileWriter fw =
                    new FileWriter(
                            "auditoria.txt",
                            true);

            fw.write(
                    LocalDateTime.now()
                            + " - "
                            + accion
                            + "\n");

            fw.close();

        } catch(Exception e) {

            e.printStackTrace();
        }
    }
}