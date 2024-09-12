
package com.example.petcarecab302qu.controller;

import com.example.petcarecab302qu.HelloApplication;
import com.example.petcarecab302qu.model.SqliteDietDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.util.List;
import javafx.geometry.Insets;

/*
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

 */
public class DietController {
    @FXML
    private TextField nameInput;

    @FXML
    private TextField durationInput;

    @FXML
    private TextField breakfastInput;

    @FXML
    private TextField lunchInput;

    @FXML
    private TextField dinnerInput;
    @FXML
    private AnchorPane rootPane;

    @FXML
    private VBox dietListBox;  // A VBox to display the diet plans dynamically

    private SqliteDietDAO dietDAO;

    public DietController() {
        dietDAO = new SqliteDietDAO();  // Initialize the DAO to use database functions
    }

    public void initialize() {
        loadAllDietPlans();  // Load all diet plans when the UI is initialized
    }

    // Method to load all diet plans from the database and display them
    private void loadAllDietPlans() {
        List<DietPlan> dietPlans = dietDAO.getAllDietPlans();
        for (DietPlan dietPlan : dietPlans) {
            addDietPlanToUI(dietPlan);
        }
    }

    // Method to handle adding a new diet plan
    public void handleAddDietPlan(ActionEvent event) {

        VBox dietFormBox = new VBox(10);
        dietFormBox.setPrefSize(200, 300);
        dietFormBox.setStyle("-fx-background-color: #e0e0e0; -fx-padding: 10px; -fx-border-radius: 5px; -fx-background-radius: 5px;");

        Label nameLabel = new Label("Diet Plan Name:");
        TextField nameInput = new TextField();
        nameInput.setPromptText("Enter diet plan name");

        Label durationLabel = new Label("How long will the diet plan last (in days):");
        TextField durationInput = new TextField();
        durationInput.setPromptText("Enter number of days");

        Label breakfastLabel = new Label("Breakfast:");
        TextField breakfastInput = new TextField();
        breakfastInput.setPromptText("Enter breakfast details");

        Label lunchLabel = new Label("Lunch:");
        TextField lunchInput = new TextField();
        lunchInput.setPromptText("Enter lunch details");

        Label dinnerLabel = new Label("Dinner:");
        TextField dinnerInput = new TextField();
        dinnerInput.setPromptText("Enter dinner details");

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
                DietPlan newDietPlan = new DietPlan(dietName, dietDuration, breakfast, lunch, dinner);
                dietDAO.addDietPlan(newDietPlan);

                // After saving, display the diet plan dynamically in the UI
                addDietPlanToUI(newDietPlan);

                System.out.println("Diet Plan saved: " + dietName + ", Duration: " + dietDuration + " days, Breakfast: " + breakfast + ", Lunch: " + lunch + ", Dinner: " + dinner);
            } catch (NumberFormatException ex) {
                System.out.println("Invalid input for duration: " + dietDurationStr);
            }
        });

        dietFormBox.getChildren().addAll(nameLabel, nameInput, durationLabel, durationInput, breakfastLabel, breakfastInput, lunchLabel, lunchInput, dinnerLabel, dinnerInput, saveButton);

        Button addButton = (Button) event.getSource();
        double buttonLayoutY = addButton.getLayoutY() + addButton.getHeight();

        double verticalOffset = 10.0;
        AnchorPane.setRightAnchor(dietFormBox, 10.0);

        AnchorPane.setTopAnchor(dietFormBox, buttonLayoutY + verticalOffset);


        rootPane.getChildren().add(dietFormBox);

        System.out.println("Add Diet Plan form created below the button!");
    }

    // Method to dynamically add a diet plan to the UI
    private void addDietPlanToUI(DietPlan dietPlan) {
        Button dietPlanButton = new Button(dietPlan.getName());  // Button representing the diet plan
        dietPlanButton.setStyle("-fx-background-color: #90CAF9; -fx-text-fill: white;");
        dietPlanButton.setOnAction(e -> {
            // Load the diet plan into the form for editing
            loadDietPlanIntoForm(dietPlan);
        });
        dietListBox.setSpacing(10);  // Space between buttons
        dietListBox.setPadding(new Insets(10));  // Padding around VBox
        dietListBox.setAlignment(Pos.TOP_CENTER);
        dietListBox.getChildren().add(dietPlanButton);
        // Add the button to the VBox on the side
    }


        private void loadDietPlanIntoForm(DietPlan dietPlan) {
            // Populate the form fields with the selected diet plan's details
            nameInput.setText(dietPlan.getName());
            durationInput.setText(String.valueOf(dietPlan.getDuration()));  // Convert int to String for duration
            breakfastInput.setText(dietPlan.getBreakfast());
            lunchInput.setText(dietPlan.getLunch());
            dinnerInput.setText(dietPlan.getDinner());

            System.out.println("Loading diet plan for editing: " + dietPlan.getName());
            // You would populate the form fields here with dietPlan.getName(), dietPlan.getDuration(), etc.
        }


    public void handleBackButton(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("home-view.fxml"));
        Scene scene = new Scene(loader.load(), HelloApplication.WIDTH, HelloApplication.HEIGHT);
        stage.setScene(scene);
    }
}

