package ru.mrgufiks;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class ApplicationGUI extends JFrame {
    public ApplicationGUI() {
        super("File encryption-decryption - 1.0 by MrGufiks");
        setBounds(100,100,500,150);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel labelin = new JLabel("Input");
        labelin.setSize(30,15);
        labelin.setLocation(5,15);
        JTextField input = new JTextField("file path");
        input.setSize(350,20);
        input.setLocation(45,13);
        JButton inputbt = new JButton("Choose");
        inputbt.setSize(80,20);
        inputbt.setLocation(400,13);
        JLabel labelkey = new JLabel("Key");
        labelkey.setSize(30,15);
        labelkey.setLocation(5,45);
        JTextField key = new JTextField("Key");
        key.setSize(350,20);
        key.setLocation(45,43);
        JButton generatorbt = new JButton("Generator");
        generatorbt.setSize(80,20);
        generatorbt.setLocation(400,43);
        JButton encode = new JButton("Encryption");
        encode.setSize(100,20);
        encode.setLocation(50,85);
        JButton decode = new JButton("Decryption");
        decode.setSize(100,20);
        decode.setLocation(175,85);
        panel.add(labelin);
        panel.add(input);
        panel.add(inputbt);
        panel.add(labelkey);
        panel.add(key);
        panel.add(generatorbt);
        panel.add(encode);
        panel.add(decode);
        setContentPane(panel);

        inputbt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileopen = new JFileChooser();
                fileopen.showDialog(null, "Choose File");
                File file = fileopen.getSelectedFile();
                input.setText(file.getPath());
            }
        });

        decode.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Main.processing(input.getText(),key.getText(), "decryption");
                } catch (IOException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "ОШИБКА!", "error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        encode.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Main.processing(input.getText(),key.getText(), "encryption");
                } catch (IOException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "ОШИБКА!", "error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        generatorbt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int length = 16;
                Random r = new Random();
                String s = r.ints(48, 122)
                        .filter(i -> (i < 57 || i > 65) && (i < 90 || i > 97))
                        .mapToObj(i -> (char) i)
                        .limit(length)
                        .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                        .toString();
                key.setText(s);
            }
        });

    }
}
