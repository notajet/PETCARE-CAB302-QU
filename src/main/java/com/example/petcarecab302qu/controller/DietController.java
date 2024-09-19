
package com.example.petcarecab302qu.controller;

import com.example.petcarecab302qu.HelloApplication;
import com.example.petcarecab302qu.model.DietPlan;
import com.example.petcarecab302qu.model.SqliteDietDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.util.List;
import javafx.geometry.Insets;


/**
 * Controller class for managing diet plans.
 * It uses {@link SqliteDietDAO} to handle database operations.
 * @Oliver
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
    private VBox dietListBox;


    private SqliteDietDAO dietDAO;

    public DietController() {
        dietDAO = new SqliteDietDAO();
    }

    /**
     * Initializes the UI and loads all diet plans from the database.
     * This method is called when the UI is initialized.
     */
    public void initialize() {
        loadAllDietPlans();
        displayDietPlans();
        rootPane.getChildren().remove(dietListBox);
        rootPane.getChildren().add(dietListBox);
    }

    /**
     * Loads all diet plans from the database and adds them to the UI.
     */
    private void loadAllDietPlans() {
        List<DietPlan> dietPlans = dietDAO.getAllDietPlans();
        for (DietPlan dietPlan : dietPlans) {
            addDietPlanToUI(dietPlan);
        }
    }

    /**
     * Handles the action for adding a new diet plan.
     * This method displays a form for the user to input diet details and save them.
     * @param event The action event triggered by the UI.
     */
    public void handleAddDietPlan(ActionEvent event) {

        VBox dietFormBox = getvBox();

        Label nameLabel = new Label("Diet Plan Name:");
        nameInput = new TextField();
        nameInput.setPromptText("Enter diet plan name");

        Label durationLabel = new Label("How long will the diet plan last (in days):");
        durationInput = new TextField();
        durationInput.setPromptText("Enter number of days");

        Label breakfastLabel = new Label("Breakfast:");
        breakfastInput = new TextField();
        breakfastInput.setPromptText("Enter breakfast details");

        Label lunchLabel = new Label("Lunch:");
        lunchInput = new TextField();
        lunchInput.setPromptText("Enter lunch details");

        Label dinnerLabel = new Label("Dinner:");
        dinnerInput = new TextField();
        dinnerInput.setPromptText("Enter dinner details");

        Button saveButton = new Button("Save Diet Plan");

        Button cancelButton = new Button("Cancel");
        cancelButton.setOnAction(e -> {
            rootPane.getChildren().remove(dietFormBox);
            reloadPage();
        });

        saveButton.setOnAction(e -> {
            String dietName = nameInput.getText();
            String dietDurationStr = durationInput.getText();
            String breakfast = breakfastInput.getText();
            String lunch = lunchInput.getText();
            String dinner = dinnerInput.getText();

            try {
                int dietDuration = Integer.parseInt(dietDurationStr);
                DietPlan newDietPlan = new DietPlan(dietName, dietDuration, breakfast, lunch, dinner);
                dietDAO.addDietPlan(newDietPlan);
                addDietPlanToUI(newDietPlan);
                reloadPage();
            } catch (NumberFormatException ex) {
                System.out.println("Invalid input for duration: " + dietDurationStr);
            }
        });

        HBox buttonBox = new HBox(10);
        buttonBox.getChildren().addAll(saveButton, cancelButton);

        dietFormBox.getChildren().addAll(
                nameLabel, nameInput,
                durationLabel, durationInput,
                breakfastLabel, breakfastInput,
                lunchLabel, lunchInput,
                dinnerLabel, dinnerInput,
                buttonBox
        );

        Button addButton = (Button) event.getSource();
        double buttonLayoutY = addButton.getLayoutY() + addButton.getHeight();
        double verticalOffset = 110.0;
        AnchorPane.setRightAnchor(dietFormBox, 10.0);
        AnchorPane.setTopAnchor(dietFormBox, buttonLayoutY + verticalOffset);
        rootPane.getChildren().add(dietFormBox);
    }

    /**
     * Creates and returns a styled VBox for the diet plan form.
     * @return A VBox styled for the diet plan input form.
     */
    private static VBox getvBox() {
        VBox dietFormBox = new VBox(5);
        dietFormBox.setPrefSize(250, 100);
        dietFormBox.setStyle(
                "-fx-background-color: white; " +
                        "-fx-padding: 20px; " +
                        "-fx-border-color: #dcdcdc; " +
                        "-fx-border-radius: 8px; " +
                        "-fx-background-radius: 8px; " +
                        "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.15), 10, 0.5, 0, 4);"
        );
        return dietFormBox;
    }

    /**
     * Adds a diet plan button to the UI dynamically.
     * Each button represents a diet plan and can be clicked to load it for editing.
     * @param dietPlan The diet plan to add to the UI.
     */
    private void addDietPlanToUI(DietPlan dietPlan) {
        Button dietPlanButton = new Button(dietPlan.getName());
        dietPlanButton.setStyle("-fx-background-color: #90CAF9; -fx-text-fill: white;");
        dietPlanButton.setOnAction(e -> {
            loadDietPlanIntoForm(dietPlan);
        });
        dietListBox.getChildren().add(dietPlanButton);
    }

    /**
     * Loads a selected diet plan into the input form for editing.
     * @param dietPlan The diet plan to load into the form.
     */
    private void loadDietPlanIntoForm(DietPlan dietPlan) {
        nameInput.setText(dietPlan.getName());
        durationInput.setText(String.valueOf(dietPlan.getDuration()));
        breakfastInput.setText(dietPlan.getBreakfast());
        lunchInput.setText(dietPlan.getLunch());
        dinnerInput.setText(dietPlan.getDinner());
    }

    /**
     * Handles the back button action and navigates to the main view.
     * @param event The action event triggered by the UI.
     * @throws IOException If the FXML file cannot be loaded.
     */
    public void handleBackButton(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("homemain-view.fxml"));
        Scene scene = new Scene(loader.load(), HelloApplication.WIDTH + 100, HelloApplication.HEIGHT + 300);
        stage.setScene(scene);
    }

    /**
     * Displays all diet plans by clearing the UI and fetching all plans from the database.
     */
    private void displayDietPlans() {
        dietListBox.getChildren().clear();
        List<DietPlan> dietPlans = dietDAO.getAllDietPlans();

        for (DietPlan dietPlan : dietPlans) {
            Label dietPlanLabel = new Label(dietPlan.getName());

            Button deleteButton = new Button("Delete");
            deleteButton.setStyle("-fx-background-color: #FF6347; -fx-text-fill: white;");

            Button viewButton = new Button("View");
            viewButton.setStyle("-fx-background-color: #1e90ff; -fx-text-fill: white;");

            deleteButton.setOnAction(e -> {
                dietDAO.deleteDietPlan(dietPlan.getId());
                displayDietPlans();
            });

            viewButton.setOnAction(e -> {
                showDietPlanDetails(dietPlan);
            });

            HBox dietPlanHBox = new HBox(10);
            dietPlanHBox.getChildren().addAll(dietPlanLabel, viewButton, deleteButton);
            dietListBox.getChildren().add(dietPlanHBox);
        }
    }

    /**
     * Reloads the current page by reloading the FXML file.
     */
    private void reloadPage() {
        try {
            Stage stage = (Stage) dietListBox.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/petcarecab302qu/diet-view.fxml"));
            Parent root = loader.load();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Displays the details of a selected diet plan in a new view box.
     * @param dietPlan The diet plan whose details are displayed.
     */

    private void showDietPlanDetails(DietPlan dietPlan) {

        VBox detailsBox = new VBox(10);
        detailsBox.setPadding(new Insets(20));
        detailsBox.setStyle(
                "-fx-background-color: white; " +
                        "-fx-border-color: #dcdcdc; " +
                        "-fx-border-radius: 8px; " +
                        "-fx-background-radius: 8px; " +
                        "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.15), 10, 0.5, 0, 4);"
        );
        detailsBox.setPrefSize(300, 200);


        Label nameLabel = new Label("Details for: " + dietPlan.getName());
        Label durationLabel = new Label("Duration: " + dietPlan.getDuration() + " days");
        Label breakfastLabel = new Label("Breakfast: " + dietPlan.getBreakfast());
        Label lunchLabel = new Label("Lunch: " + dietPlan.getLunch());
        Label dinnerLabel = new Label("Dinner: " + dietPlan.getDinner());


        nameLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 16px;");
        durationLabel.setStyle("-fx-font-size: 14px;");
        breakfastLabel.setStyle("-fx-font-size: 14px;");
        lunchLabel.setStyle("-fx-font-size: 14px;");
        dinnerLabel.setStyle("-fx-font-size: 14px;");

        Button closeButton = new Button("Close");
        closeButton.setStyle("-fx-background-color: #F44336; -fx-text-fill: white; -fx-font-weight: bold;");
        Button editButton = new Button("Edit");
        editButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-weight: bold;");

        closeButton.setOnAction(e -> {
            rootPane.getChildren().remove(detailsBox);
            reloadPage();
        });


        detailsBox.getChildren().addAll(nameLabel, durationLabel, breakfastLabel, lunchLabel, dinnerLabel,closeButton);


        rootPane.getChildren().add(detailsBox);


        AnchorPane.setTopAnchor(detailsBox, 210.0);  // Position it vertically
        AnchorPane.setLeftAnchor(detailsBox, 230.0); // Position it horizontally
    }


}

