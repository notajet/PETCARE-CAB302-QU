module com.example.petcarecab302qu {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;
    requires javafx.graphics;


    opens com.example.petcarecab302qu to javafx.fxml;
    exports com.example.petcarecab302qu;
    exports com.example.petcarecab302qu.model;
    exports com.example.petcarecab302qu.controller;
    opens com.example.petcarecab302qu.controller to javafx.fxml;
    opens com.example.petcarecab302qu.model to javafx.fxml;
}