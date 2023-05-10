package com.example.cryptology.controller;

import com.example.cryptology.Helper.HelperForLab4;
import com.example.cryptology.lab4.MD5;
import com.example.cryptology.lab6.DSA;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.bouncycastle.crypto.params.KeyParameter;
import javax.swing.*;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import static com.example.cryptology.lab3.Blowfish.decrypt;
import static com.example.cryptology.lab3.Blowfish.encrypt;

public class Controller {

    @FXML
    private TextField textForEncrypt;

    @FXML
    private TextField keyForEncrypt;

    @FXML
    private Label labelEncrypt;

    //lab6:
    @FXML
    private TextField parameterGForLab6;
    @FXML
    private TextField parameterPForLab6;
    @FXML
    private TextField parameterQForLab6;
    @FXML
    private TextField secretLab6;
    @FXML
    private TextField openLab6;
    @FXML
    private TextField hashLab6;
    @FXML
    private TextField resultRLab6;
    @FXML
    private TextField resultSLab6;
    @FXML
    private TextField textLab6;

    @FXML
    TextArea textArea;

    @FXML
    protected void encryptTextByBlowFish() throws Exception {
        String phrase = textForEncrypt.getText();
        String key1 = keyForEncrypt.getText();
        byte[] keyBytes = key1.getBytes(StandardCharsets.UTF_8);
        KeyParameter key = new KeyParameter(keyBytes);
        byte[] encryptedBytes = encrypt(phrase.getBytes(StandardCharsets.UTF_8), key);
        String encryptedString = Base64.getEncoder().encodeToString(encryptedBytes);
        labelEncrypt.setText(encryptedString);
    }

    @FXML
    protected void deEncryptTextByBlowFish() throws Exception {
        String encryptText = labelEncrypt.getText();
        String key1 = keyForEncrypt.getText();
        byte[] keyBytes = key1.getBytes(StandardCharsets.UTF_8);
        KeyParameter key = new KeyParameter(keyBytes);
        byte[] encryptedBytes = Base64.getDecoder().decode(encryptText);
        byte[] decryptedBytes = decrypt(encryptedBytes, key);
        String decryptedString = new String(decryptedBytes, StandardCharsets.UTF_8);
        labelEncrypt.setText(decryptedString);
    }

    @FXML
    protected void fillTestDataForBlowFish() {
        textForEncrypt.setText("pyvovarsasha");
        keyForEncrypt.setText("secret");
    }

    @FXML
    protected void saveHash() {
        if (!labelEncrypt.getText().isEmpty()) {
            HelperForLab4.saveHash(labelEncrypt.getText());
        } else {
            JOptionPane.showMessageDialog(null, "Hash is empty", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @FXML
    protected void mdFive() {
        labelEncrypt.setText(MD5.generateMD5(textForEncrypt.getText()));
    }

    @FXML
    protected void signLab6() throws NoSuchAlgorithmException {
        DSA.generateSignature(parameterPForLab6, parameterGForLab6, parameterQForLab6, secretLab6, openLab6, hashLab6, resultRLab6, resultSLab6, textLab6);
    }

    @FXML
    protected void checkSignLab6() throws NoSuchAlgorithmException {
        var flag = DSA.verifySignature(openLab6, resultRLab6, resultSLab6, textLab6);
        if (flag){
            JOptionPane.showMessageDialog(null, "Підтверджено" ,"Перевірка підпису", JOptionPane.INFORMATION_MESSAGE);
        }else {
            JOptionPane.showMessageDialog(null, "Відхилено" ,"Перевірка підпису", JOptionPane.ERROR_MESSAGE);
        }
    }
}