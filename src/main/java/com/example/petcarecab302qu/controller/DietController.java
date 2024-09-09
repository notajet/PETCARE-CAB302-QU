
package com.example.petcarecab302qu.controller;

import com.example.petcarecab302qu.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class DietController {

    @FXML
    private AnchorPane rootPane;




    public void handleAddDietPlan(ActionEvent event) {

            // Create a new VBox to hold the form elements
            VBox dietFormBox = new VBox(10);
            dietFormBox.setPrefSize(300, 250);
            dietFormBox.setStyle("-fx-background-color: #e0e0e0; -fx-padding: 10px; -fx-border-radius: 5px; -fx-background-radius: 5px;");

            Label nameLabel = new Label("Diet Plan Name:");
            TextField nameInput = new TextField();
            nameInput.setPromptText("Enter diet plan name");

            Label durationLabel = new Label("How long will the diet plan last (in days):");
            TextField durationInput = new TextField();
            durationInput.setPromptText("Enter number of days");

            Button saveButton = new Button("Save Diet Plan");
            saveButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
            saveButton.setOnAction(e -> {
                System.out.println("Diet Plan Name: " + nameInput.getText() + ", Duration: " + durationInput.getText() + " days");
            });


            dietFormBox.getChildren().addAll(nameLabel, nameInput, durationLabel, durationInput, saveButton);

            // Calculate the position to place the form right under the button
            Button addButton = (Button) event.getSource();
            double buttonLayoutY = addButton.getLayoutY() + addButton.getHeight();
            double buttonLayoutX = addButton.getLayoutX();
            double verticalOffset = 110.0;

            // Center the form horizontally relative to the button
            AnchorPane.setTopAnchor(dietFormBox, buttonLayoutY + verticalOffset);
            AnchorPane.setLeftAnchor(dietFormBox, buttonLayoutX - (dietFormBox.getPrefWidth() - addButton.getPrefWidth()) / 2); // Center it horizontally

            // Add the VBox (form) to the root pane
            rootPane.getChildren().add(dietFormBox);

            System.out.println("Add Diet Plan form created below the button!");

    }








    public void handleBackButton(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("home-view.fxml"));
        Scene scene = new Scene(loader.load(), HelloApplication.WIDTH, HelloApplication.HEIGHT);
        stage.setScene(scene);
    }
}

