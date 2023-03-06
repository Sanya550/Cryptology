module com.example.cryptology {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.cryptology to javafx.fxml;
    opens com.example.cryptology.lab2 to javafx.base;
    exports com.example.cryptology;
    exports com.example.cryptology.controller;
    opens com.example.cryptology.controller to javafx.fxml;
}