package com.example.cryptology.controller;

import com.example.cryptology.Helper.HelperForLab2;
import com.example.cryptology.lab1.Hilla;
import com.example.cryptology.lab1.Permutations;
import com.example.cryptology.lab1.PlayFair;
import com.example.cryptology.lab1.Vigenere;
import com.example.cryptology.lab2.BBS;
import com.example.cryptology.lab2.Lemer;
import com.example.cryptology.lab3.Blowfish;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Controller {

    @FXML
    private TableView tableView;

    //lab1 and lab3:
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
        String key = keyForEncrypt.getText();
        SecretKey secretKey = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "Blowfish");
//        labelEncrypt.setText(Blowfish.encrypt(phrase, secretKey));
    }

    @FXML
    protected void deEncryptTextByBlowFish() throws Exception {
        String encryptText = labelEncrypt.getText();
        String key = keyForEncrypt.getText();
        SecretKey secretKey = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "Blowfish");
//        labelEncrypt.setText(Blowfish.decrypt(encryptText, secretKey));
    }

    @FXML
    protected void fillTestDataForBlowFish() {
        textForEncrypt.setText("wearediscoveredsaveyourself");
        keyForEncrypt.setText("deceptive");
    }
}