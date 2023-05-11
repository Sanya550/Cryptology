package com.example.cryptology.controller;

import com.example.cryptology.Helper.Helper;
import com.example.cryptology.lab4.MD5;
import com.example.cryptology.lab6.DSA;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.bouncycastle.crypto.params.KeyParameter;

import javax.swing.*;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import static com.example.cryptology.Helper.Helper.readFromFile;
import static com.example.cryptology.lab3.Blowfish.decrypt;
import static com.example.cryptology.lab3.Blowfish.encrypt;

public class Controller {
    public static String encrText;
    public static String keyForBlowfish;

    public static int pDSA;
    public static int qDSA;
    public static int gDSA;

    public static int secretDSA;
    public static int openDSA;
    public static int rDSA;
    public static int sDSA;

    @FXML
    private TextField textForEncrypt;

    @FXML
    private TextField keyForEncrypt;
    @FXML
    private TextField parameterPForLab6;
    @FXML
    private TextField parameterQForLab6;

    @FXML
    private TextArea textArea;
    @FXML
    private CheckBox checkBoxForKey;
    @FXML
    private CheckBox checkBoxForText;

    @FXML
    private TextField textFieldForBlowFishKey;
    @FXML
    private TextField textFieldForKeyLetter;
    @FXML
    private TextField textFieldForLetter;

    @FXML
    protected void randomForTextAndKey() {
        if (checkBoxForKey.isSelected()) {
            keyForEncrypt.setText(Helper.generateRandomString(7, 10));
        }
        if (checkBoxForText.isSelected()) {
            textForEncrypt.setText(Helper.generateRandomString(10, 15));
        }
    }

    @FXML
    protected void encryptTextByBlowFish() throws Exception {
        String phrase = textForEncrypt.getText();
        String key1 = keyForEncrypt.getText();
        keyForBlowfish = key1;
        byte[] keyBytes = key1.getBytes(StandardCharsets.UTF_8);
        KeyParameter key = new KeyParameter(keyBytes);
        byte[] encryptedBytes = encrypt(phrase.getBytes(StandardCharsets.UTF_8), key);
        String encryptedString = Base64.getEncoder().encodeToString(encryptedBytes);
        encrText = encryptedString;
        textArea.appendText("Зашифрований текст методом BlowFish:\n");
        textArea.appendText(encrText);
    }


    @FXML
    protected void fillTestDataForBlowFish() {
        textForEncrypt.setText("pyvovarsasha");
        keyForEncrypt.setText("secret");
    }

    @FXML
    protected void sign() throws NoSuchAlgorithmException {
        DSA.generateSignature(parameterPForLab6, parameterQForLab6, textForEncrypt.getText());
        textArea.appendText("\n\nХеш зашифрованого тексту за допомогою метода MD5:\n");
        textArea.appendText(MD5.generateMD5(textForEncrypt.getText()));
        textArea.appendText("\n\nПідпис методом DSA:\n");
        textArea.appendText("[r,s] = [" + rDSA + ", " + sDSA + "]");
    }

    @FXML
    protected void checkSign() throws Exception {
        try {
            String encryptText = encrText;
            String key1 = keyForBlowfish;
            byte[] keyBytes = key1.getBytes(StandardCharsets.UTF_8);
            KeyParameter key = new KeyParameter(keyBytes);
            byte[] encryptedBytes = Base64.getDecoder().decode(encryptText);
            byte[] decryptedBytes = decrypt(encryptedBytes, key);
            String decryptedString = new String(decryptedBytes, StandardCharsets.UTF_8);

            var flag = DSA.verifySignature(openDSA, rDSA, sDSA, decryptedString);
            if (flag) {
                JOptionPane.showMessageDialog(null, "Підтверджено", "Перевірка підпису", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Відхилено", "Перевірка підпису", JOptionPane.ERROR_MESSAGE);
            }
        }catch (IllegalArgumentException illegalArgumentException){
            JOptionPane.showMessageDialog(null, "Відхилено", "Перевірка підпису", JOptionPane.ERROR_MESSAGE);
        }
    }

    @FXML
    protected void saveBlowFishKey() {
        if (!textFieldForBlowFishKey.getText().isEmpty()) {
            Helper.saveToFile("key = " + keyForEncrypt.getText(), textFieldForBlowFishKey.getText());
        } else {
            JOptionPane.showMessageDialog(null, "Name of blowFish file is empty", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @FXML
    protected void saveKeyForLetter() {
        if (!textFieldForKeyLetter.getText().isEmpty()) {
            Helper.saveToFile(String.format("p = %d\nq = %d\ng = %d\nopen = %d\nsecret = %d", pDSA, qDSA, gDSA, openDSA, secretDSA), textFieldForKeyLetter.getText());
        } else {
            JOptionPane.showMessageDialog(null, "Name of DSA keys file is empty", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @FXML
    protected void saveLetter() {
        if (!textFieldForLetter.getText().isEmpty()) {
            Helper.saveToFile(String.format("secretText = %s\nsign[r] = %d\nsign[s] = %d", encrText, rDSA, sDSA), textFieldForLetter.getText());
        } else {
            JOptionPane.showMessageDialog(null, "Name of DSA keys file is empty", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @FXML
    protected void readBlowFishKey() {
        var map = readFromFile();
        keyForBlowfish = map.get("key");
    }

    @FXML
    protected void readDSAKey() {
        var map = readFromFile();
        pDSA = Integer.parseInt(map.get("p"));
        qDSA = Integer.parseInt(map.get("q"));
        gDSA = Integer.parseInt(map.get("g"));
        openDSA = Integer.parseInt(map.get("open"));
        secretDSA = Integer.parseInt(map.get("secret"));
    }

    @FXML
    protected void readLetter() {
        var map = readFromFile();
        encrText = map.get("secretText");
        rDSA = Integer.parseInt(map.get("sign[r]"));
        sDSA = Integer.parseInt(map.get("sign[s]"));
    }

    @FXML
    protected void clearTextArea() {
        textArea.clear();
    }
}