package com.example.petcarecab302qu;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class HelloApplication extends Application {

    public static final int WIDTH = 800;  // Define the width
    public static final int HEIGHT = 570; // Define the height

    @Override
    public void start(Stage stage) throws Exception {
        // Load the homepage view
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/petcarecab302qu/home-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), WIDTH, HEIGHT);
        stage.setTitle("PETHUB");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
