package com.example.petcarecab302qu.util;

import com.example.petcarecab302qu.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;
import java.io.IOException;

/**
 * Utility class for loading and switching between scenes in the Pet Care application.
 * Provides methods to load new scenes and handle navigation between different views.
 */
public class SceneLoader {

    /**
     * Loads a new scene specified by the given FXML path and sets it on the current stage.
     *
     * @param event The action event that triggered the scene change.
     * @param fxmlPath The path to the FXML file to load.
     */
    public static void loadScene(ActionEvent event, String fxmlPath) {
        try {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(SceneLoader.class.getResource(fxmlPath));
            Scene scene = new Scene(loader.load(), HelloApplication.WIDTH, HelloApplication.HEIGHT);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Handles the back button action to navigate to the home view.
     *
     * @param event The action event that triggered the navigation.
     * @throws IOException if loading the FXML file fails.
     */
    public static void handleBackOnHome(ActionEvent event) throws IOException {
        loadScene(event, "/com/example/petcarecab302qu/home-view.fxml");
    }

    /**
     * Handles the back button action to navigate to the main home view.
     *
     * @param event The action event that triggered the navigation.
     * @throws IOException if loading the FXML file fails.
     */
    public static void handleBackButton(ActionEvent event) throws IOException {
        loadScene(event, "/com/example/petcarecab302qu/homemain-view.fxml");
    }
}
