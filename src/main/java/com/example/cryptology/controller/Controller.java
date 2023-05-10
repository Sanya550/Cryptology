package com.example.cryptology.controller;

import com.example.cryptology.Helper.HelperForLab2;
import com.example.cryptology.Helper.HelperForLab4;
import com.example.cryptology.lab1.Hilla;
import com.example.cryptology.lab1.Permutations;
import com.example.cryptology.lab1.PlayFair;
import com.example.cryptology.lab1.Vigenere;
import com.example.cryptology.lab2.BBS;
import com.example.cryptology.lab2.Lemer;
import com.example.cryptology.lab4.MD5;
import com.example.cryptology.lab5.DiffiHelman;
import com.example.cryptology.lab6.DSA;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.bouncycastle.crypto.params.KeyParameter;

import javax.swing.*;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import static com.example.cryptology.lab3.Blowfish.decrypt;
import static com.example.cryptology.lab3.Blowfish.encrypt;

public class Controller {

    @FXML
    private TableView tableView;

    //lab1 and lab3, lab4:
    @FXML
    private TextField textForEncrypt;

    @FXML
    private TextField keyForEncrypt;

    @FXML
    private Label labelEncrypt;
    //lab1:
    char[][] keyTable = {{2, 0, 3}, {0, 4, 0}, {5, 0, 7}};//для метода Hilla

    //lab2:
    List<Integer> listForBBS = new ArrayList<>();
    @FXML
    private TextField strMLemer;

    @FXML
    private TextField strALemer;

    @FXML
    private TextField strCLemer;

    @FXML
    private TextField strX0Lemer;

    @FXML
    private TextField strQuantity;

    @FXML
    private TextField strBBSForP;

    @FXML
    private TextField strBBSForQ;

    @FXML
    private TextField strBBSForS;

    //lab5:
    @FXML
    private TextField openKeyAlisaStr;

    @FXML
    private TextField openKeyBobStr;

    @FXML
    private TextField parameterGForLab5Str;

    @FXML
    private TextField parameterPForLab5Str;

    @FXML
    private TextField calculatedKeyAlisaStr;

    @FXML
    private TextField calculatedKeyBobStr;

    @FXML
    private TextField digitAlisaStr;

    @FXML
    private TextField digitBobStr;

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

    //lab1:
    //Метод Плейфейра:
    @FXML
    protected void encryptTextByPlayFair() {
        String word = textForEncrypt.getText();
        PlayFair playFair = new PlayFair();
        labelEncrypt.setText(playFair.encryptionText(word));
    }

    @FXML
    protected void matrixKey() {
        tableView.getItems().clear();
        tableView.getColumns().clear();
        PlayFair.fillingMatrixForEncryption();
        for (int i = 0; i < PlayFair.matrixForEncryptionByPlayFair.length; i++) {
            TableColumn<char[], String> column = new TableColumn<>(String.valueOf(i));
            final int colIndex = i;
            column.setCellValueFactory(param -> new SimpleStringProperty(String.valueOf(param.getValue()[colIndex])));
            tableView.getColumns().add(column);
        }

        tableView.getItems().addAll(PlayFair.matrixForEncryptionByPlayFair);
        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    @FXML
    protected void fillTestDataForPlayFair() {
        textForEncrypt.setText("pyvovarsasha");
        keyForEncrypt.setText("comander");
    }

    //метод Віженера:
    @FXML
    protected void encryptTextByVingere() {
        String phrase = textForEncrypt.getText();
        String key = keyForEncrypt.getText();
        Vigenere vigenere = new Vigenere();
        labelEncrypt.setText(vigenere.encryptText(phrase, key));
    }

    @FXML
    protected void deEncryptTextByVingere() {
        String encryptText = labelEncrypt.getText();
        String key = keyForEncrypt.getText();
        Vigenere vigenere = new Vigenere();
        labelEncrypt.setText(vigenere.deEncryptText(encryptText, key));
    }

    @FXML
    protected void fillTestDataForVigenere() {
        textForEncrypt.setText("wearediscoveredsaveyourself");
        keyForEncrypt.setText("deceptive");
    }

    //Метод Хілла:
    @FXML
    protected void encryptTextForHill() {
        String text = textForEncrypt.getText();
        Hilla hilla = new Hilla();
        labelEncrypt.setText(hilla.encryptByHill(text, keyTable));
    }

    @FXML
    protected void fillTestDataForHill() {
        textForEncrypt.setText("pyvovarsasha");
        keyForEncrypt.setText("{{2, 0, 3}, {0, 4, 0}, {5, 0, 7}}");
    }

    //Метод Перестановки:
    @FXML
    protected void encryptTextForPermutations() {
        String text = textForEncrypt.getText();
        String key = keyForEncrypt.getText();
        Permutations permutations = new Permutations();
        labelEncrypt.setText(permutations.encryptTextByPermutations(text, key));
    }

    @FXML
    protected void deEncryptTextForPermutations() {
        String encryptText = labelEncrypt.getText();
        String key = keyForEncrypt.getText();
        Permutations permutations = new Permutations();
        labelEncrypt.setText(permutations.deEncryptTextByPermutations(encryptText, key));
    }

    @FXML
    protected void fillTestDataForPermutations() {
        textForEncrypt.setText("enemyattactstonights");
        keyForEncrypt.setText("31452");
    }

    //lab2:
    //Lemera:
    @FXML
    protected void lemera() {
        int m = Integer.parseInt(strMLemer.getText());
        int a = Integer.parseInt(strALemer.getText());
        int c = Integer.parseInt(strCLemer.getText());
        int x0 = Integer.parseInt(strX0Lemer.getText());
        int quantity = Integer.parseInt(strQuantity.getText());
        Lemer lemer = new Lemer();
        var list = lemer.generateListOfDigit(quantity, m, a, c, x0);
        JOptionPane.showMessageDialog(null, "Період = " + lemer.findPeriod(list), "Info details", JOptionPane.INFORMATION_MESSAGE);
        HelperForLab2.showListOfPsewdoSequence(tableView, list);
    }

    //BBS:
    @FXML
    protected void bbsForDigits() {
        int p = Integer.parseInt(strBBSForP.getText());
        int q = Integer.parseInt(strBBSForQ.getText());
        int s = Integer.parseInt(strBBSForS.getText());
        int quantity = Integer.parseInt(strQuantity.getText());
        BBS bbs = new BBS();
        listForBBS = bbs.generateListOfDigit(p, q, s, quantity);
        HelperForLab2.showListOfPsewdoSequence(tableView, listForBBS);
    }

    @FXML
    protected void bbsForTests() {
        int p = Integer.parseInt(strBBSForP.getText());
        int q = Integer.parseInt(strBBSForQ.getText());
        int s = Integer.parseInt(strBBSForS.getText());
        int quantity = Integer.parseInt(strQuantity.getText());
        BBS bbs = new BBS();
        var listOneOrZero = bbs.listZeroOrOne(listForBBS);
        JOptionPane.showMessageDialog(null, bbs.testsForBBS(listOneOrZero), "Tests", JOptionPane.INFORMATION_MESSAGE);
    }

    @FXML
    protected void testData() {
        strBBSForP.setText("383");
        strBBSForQ.setText("503");
        strBBSForS.setText("101355");
        strQuantity.setText("20000");
        strALemer.setText("4");
        strMLemer.setText("10000");
        strX0Lemer.setText("67");
        strCLemer.setText("5");
    }

    //lab3:
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

    //lab4:
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

    //lab5:
    @FXML
    protected void findSecretKeyForLab5() {
        DiffiHelman diffiHelman = new DiffiHelman();
        diffiHelman.calculationKeys(Integer.parseInt(parameterPForLab5Str.getText()), Integer.parseInt(parameterGForLab5Str.getText()),
                Integer.parseInt(digitAlisaStr.getText()), Integer.parseInt(digitBobStr.getText()), openKeyAlisaStr, openKeyBobStr, calculatedKeyAlisaStr, calculatedKeyBobStr);
    }

    @FXML
    protected void testDataForLab5() {
        parameterGForLab5Str.setText("5");
        parameterPForLab5Str.setText("97");
        digitAlisaStr.setText("36");
        digitBobStr.setText("58");
    }

    //lab6:
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