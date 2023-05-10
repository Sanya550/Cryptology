module com.example.cryptology {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires org.bouncycastle.provider;


    opens com.example.cryptology to javafx.fxml;
    exports com.example.cryptology;
    exports com.example.cryptology.controller;
    opens com.example.cryptology.controller to javafx.fxml;
}