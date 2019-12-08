package ru.mrgufiks;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) {
        ApplicationGUI app = new ApplicationGUI(); //Создаем экземпляр нашего приложения
        app.setVisible(true);
    }

    public static void processing(String path, String key, String option) throws IOException {

        File config = new File(path);
        if (!config.exists()) {
            JOptionPane.showMessageDialog(null, "Файл не найден по заданному пути", "error", JOptionPane.ERROR_MESSAGE);
        } else {

            byte[] file;
            byte[] bkey = key.getBytes();
            String newName;
            file = Files.readAllBytes(Paths.get(config.toURI()));

            for (int i = 0; i < file.length; ++i) {
                file[i] = (byte) (file[i] ^ bkey[i % bkey.length]);
            }
            if (option == "encryption") {
                newName = config.getPath().split("\\.")[0] + ".encryption";
                Files.write(Paths.get(newName), file);
                JOptionPane.showMessageDialog(null, "Файл успешно зашифрован в " + newName, "Success", JOptionPane.INFORMATION_MESSAGE);
            }else if (option == "decryption"){
                newName = config.getPath().split("\\.")[0] + ".decryption";
                Files.write(Paths.get(newName), file);
                JOptionPane.showMessageDialog(null, "Файл успешно дешифрован в " + newName, "Success", JOptionPane.INFORMATION_MESSAGE);

            }
        }
    }
}

