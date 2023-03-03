package com.example.cryptology.Helper;

import com.example.cryptology.lab2.NumberWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HelperForLab2 {
    public static void showListOfPsewdoSequence(TableView tableView, List<Integer> list) {
        tableView.getItems().clear();
        tableView.getColumns().clear();

        final ObservableList<NumberWrapper> data = FXCollections.observableArrayList();
        for (int i = 0; i < list.size(); i++) {
            data.add(new NumberWrapper(list.get(i)));
        }
        //Creating columns
        TableColumn<Integer, Integer> columnForNumber = new TableColumn<>("Числa");
        columnForNumber.setCellValueFactory(new PropertyValueFactory<>("number"));

        //Adding data to the table
        tableView.setItems(data);
        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        tableView.getColumns().addAll(columnForNumber);
    }
}
