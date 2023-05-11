package com.example.cryptology.Helper;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Helper {
    public static void saveToFile(String hash, String name) {
        try {
            File dir = null;
            JFileChooser fc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
            fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int resp = fc.showOpenDialog(null);
            if (resp == JFileChooser.APPROVE_OPTION) {
                dir = fc.getSelectedFile();
            }

            File file = new File(dir, name + ".txt");
            FileWriter fw = null;
            try {
                fw = new FileWriter(file);
                fw.write(hash);
                fw.flush();
            } catch (IOException ex) {
                ex.printStackTrace();
            } finally {
                if (fw != null) {
                    try {
                        fw.close();
                    } catch (IOException ex) {
                    }
                }
            }
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null, "Something wrong", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static String generateRandomString(int minLength, int maxLength) {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        int stringLength = random.nextInt(minLength) + maxLength;
        for (int i = 0; i < stringLength; i++) {
            int index = random.nextInt(alphabet.length());
            char randomChar = alphabet.charAt(index);
            sb.append(randomChar);
        }
        String randomString = sb.toString();
        return randomString;
    }

    public static LinkedHashMap<String, String> readFromFile(){
        var map = new LinkedHashMap<String,String>();
        JFileChooser fileopen = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        fileopen.showDialog(null, "Виберіть текстовий файл");
        File file = fileopen.getSelectedFile();
        String s = file.getPath();
        List<String> listString = new ArrayList<>();
        try (BufferedReader br = Files.newBufferedReader(Path.of(s))) {
            listString = br.lines().collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (String str : listString) {
            String[] parts = str.trim().split("=", 2);
            if (parts.length == 2) {
                String parameter = parts[0].trim();
                String value = parts[1].trim();
                map.put(parameter, value);
            }
        }
        return map;
    }
}
