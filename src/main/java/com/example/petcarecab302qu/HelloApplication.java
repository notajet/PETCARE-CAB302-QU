package com.example.petcarecab302qu;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main application class for the Pet Care application.
 * Sets up and displays the primary application window.
 */
public class HelloApplication extends Application {

    public static final int WIDTH = 800;
    public static final int HEIGHT = 570;

    /**
     * Starts the application by loading the main FXML view and setting the stage.
     *
     * @param stage the primary stage for this application
     * @throws Exception if there is an issue loading the FXML resource
     */
    @Override
    public void start(Stage stage) throws Exception {
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
