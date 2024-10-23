package com.example.petcarecab302qu.controller;
import com.example.petcarecab302qu.util.SceneLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;
import java.io.IOException;

/**
 * Controller class for managing the navigation bar in the PETHUB application.
 * Loads the navigation bar from an FXML file and adds it to the view.
 */
public class NavigationController {

    @FXML
    protected VBox navigationBar;

    /**
     * Loads the navigation bar from the FXML file and adds it to the VBox.
     * This method is responsible for initialising and setting up the navigation bar.
     */
    protected void NavigationBar() {
        if (navigationBar.getChildren().isEmpty()) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/petcarecab302qu/navigation-bar.fxml"));
                VBox navigation = loader.load();
                navigationBar.getChildren().add(navigation); // Only add if navigationBar is empty
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     *
     * @param event triggered by home button
     */
    public void handleHome(ActionEvent event) {
        SceneLoader.loadScene(event,"/com/example/petcarecab302qu/mainhome-view.fxml");
    }

    /**
     *
     * @param event triggered by pet profile button
     */
    public void handlePetProfile(ActionEvent event) {
        SceneLoader.loadScene(event,"/com/example/petcarecab302qu/petprofile-view.fxml");
    }

    /**
     *
     * @param event triggered by diet button
     */
    public void handleDietPlan(ActionEvent event) {
        SceneLoader.loadScene(event, "/com/example/petcarecab302qu/diet-view.fxml");
    }

    /**
     *
     * @param event triggered by exercise button
     */

    public void handleExercise(ActionEvent event){
        SceneLoader.loadScene(event, "/com/example/petcarecab302qu/exercise-view.fxml");
    }

    /**
     *
     * @param event triggered by schedule button
     */
    public void handleSchedule(ActionEvent event){
        SceneLoader.loadScene(event, "/com/example/petcarecab302qu/schedule-view.fxml");
    }

    /**
     *
     * @param event triggered by setting button
     */
    public void handleSetting(ActionEvent event){
        SceneLoader.loadScene(event, "/com/example/petcarecab302qu/setting-view.fxml");
    }
}
