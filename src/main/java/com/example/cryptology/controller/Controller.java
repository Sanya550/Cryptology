package com.example.cryptology.controller;

import com.example.cryptology.Helper.HelperForLab2;
import com.example.cryptology.lab1.Hilla;
import com.example.cryptology.lab1.Permutations;
import com.example.cryptology.lab1.PlayFair;
import com.example.cryptology.lab1.Vigenere;
import com.example.cryptology.lab2.Lemer;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class Controller {

    @FXML
    private TableView tableView;

    //lab1:
    char[][] keyTable = {{2, 0, 3}, {0, 4, 0}, {5, 0, 7}};//для метода Hilla
    @FXML
    private TextField textForEncrypt;

    @FXML
    private TextField keyForEncrypt;

    @FXML
    private Label labelEncrypt;

    //lab2:
    @FXML
    private TextField strMLemer;

    @FXML
    private TextField strALemer;

    @FXML
    private TextField strCLemer;

    @FXML
    private TextField strX0Lemer;

    @FXML
    private TextField strQuantityLemer;

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
    @FXML
    protected void lemera() {
        int m = Integer.parseInt(strMLemer.getText());
        int a = Integer.parseInt(strALemer.getText());
        int c = Integer.parseInt(strCLemer.getText());
        int x0 = Integer.parseInt(strX0Lemer.getText());
        int quantity = Integer.parseInt(strQuantityLemer.getText());
        Lemer lemer = new Lemer();
        var list = lemer.generateListOfDigit(quantity, m, a, c, x0);
        HelperForLab2.showListOfPsewdoSequence(tableView, list);
    }
}