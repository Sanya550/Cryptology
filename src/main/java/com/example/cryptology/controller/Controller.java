package com.example.cryptology.controller;

import com.example.cryptology.lab1.Hilla;
import com.example.cryptology.lab1.PlayFair;
import com.example.cryptology.lab1.Vigenere;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Controller {
    char[][] keyTable = {{2, 0, 3}, {0, 4, 0}, {5, 0, 7}};//для метода Hilla
    @FXML
    private TextField textForEncrypt;

    @FXML
    private TextField keyForEncrypt;

    @FXML
    private Label labelEncrypt;

    @FXML
    private TableView tableView;

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
    protected void fillTestDataForPlayFair(){
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
    protected void fillTestDataForVigenere(){
        textForEncrypt.setText("wearediscoveredsaveyourself");
        keyForEncrypt.setText("deceptive");
    }

    //Метод Хілла:
    @FXML
    protected void encryptTextForHill(){
        String text = textForEncrypt.getText();
        Hilla hilla = new Hilla();
        labelEncrypt.setText(hilla.encryptByHill(text,keyTable));
    }

    @FXML
    protected void fillTestDataForHill(){
        textForEncrypt.setText("pyvovarsasha");
        keyForEncrypt.setText("{{2, 0, 3}, {0, 4, 0}, {5, 0, 7}}");
    }
}