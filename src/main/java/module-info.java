module com.example.petcarecab302qu {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.petcarecab302qu to javafx.fxml;
    exports com.example.petcarecab302qu;
}