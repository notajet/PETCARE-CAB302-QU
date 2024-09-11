
package com.example.petcarecab302qu.controller;

import com.example.petcarecab302qu.HelloApplication;
import com.example.petcarecab302qu.model.SqliteDietDAO;
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
/*
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

*/
public class DietController {

    @FXML
    private AnchorPane rootPane;

    private SqliteDietDAO dietDAO;

    public DietController() {
        dietDAO = new SqliteDietDAO();  // Initialize the DAO to use database functions
    }

    public void handleAddDietPlan(ActionEvent event) {

        VBox dietFormBox = new VBox(10);
        dietFormBox.setPrefSize(300, 400);
        dietFormBox.setStyle("-fx-background-color: #e0e0e0; -fx-padding: 10px; -fx-border-radius: 5px; -fx-background-radius: 5px;");

        // Add Name Input
        Label nameLabel = new Label("Diet Plan Name:");
        TextField nameInput = new TextField();
        nameInput.setPromptText("Enter diet plan name");

        // Add Duration Input
        Label durationLabel = new Label("How long will the diet plan last (in days):");
        TextField durationInput = new TextField();
        durationInput.setPromptText("Enter number of days");

        // Add Breakfast Input
        Label breakfastLabel = new Label("Breakfast:");
        TextField breakfastInput = new TextField();
        breakfastInput.setPromptText("Enter breakfast details");

        // Add Lunch Input
        Label lunchLabel = new Label("Lunch:");
        TextField lunchInput = new TextField();
        lunchInput.setPromptText("Enter lunch details");

        // Add Dinner Input
        Label dinnerLabel = new Label("Dinner:");
        TextField dinnerInput = new TextField();
        dinnerInput.setPromptText("Enter dinner details");

        // Save Button
        Button saveButton = new Button("Save Diet Plan");
        saveButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
        saveButton.setOnAction(e -> {
            String dietName = nameInput.getText();
            String dietDurationStr = durationInput.getText();
            String breakfast = breakfastInput.getText();
            String lunch = lunchInput.getText();
            String dinner = dinnerInput.getText();

            try {
                int dietDuration = Integer.parseInt(dietDurationStr);  // Convert the duration to an integer
                // Save the diet plan using the DAO, passing breakfast, lunch, and dinner values
                dietDAO.addDietPlan(dietName, dietDuration, breakfast, lunch, dinner);
                System.out.println("Diet Plan saved: " + dietName + ", Duration: " + dietDuration + " days, Breakfast: " + breakfast + ", Lunch: " + lunch + ", Dinner: " + dinner);
            } catch (NumberFormatException ex) {
                System.out.println("Invalid input for duration: " + dietDurationStr);
            }
        });

        // Add fields to the form box
        dietFormBox.getChildren().addAll(nameLabel, nameInput, durationLabel, durationInput, breakfastLabel, breakfastInput, lunchLabel, lunchInput, dinnerLabel, dinnerInput, saveButton);

        // Add the form to the UI under the button
        Button addButton = (Button) event.getSource();
        double buttonLayoutY = addButton.getLayoutY() + addButton.getHeight();

        double verticalOffset = 20;
        AnchorPane.setRightAnchor(dietFormBox, 10.0);

        AnchorPane.setTopAnchor(dietFormBox, buttonLayoutY + verticalOffset);


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
