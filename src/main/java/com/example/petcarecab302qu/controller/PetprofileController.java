package com.example.petcarecab302qu.controller;

import com.example.petcarecab302qu.HelloApplication;
import com.example.petcarecab302qu.model.SqliteConnectionPetCare;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import com.example.petcarecab302qu.db.SqlitePetDAO;
import com.example.petcarecab302qu.model.Pet;
import javafx.stage.Stage;

import java.io.IOException;

public class PetprofileController {

    @FXML
    private TextField nameField;

    @FXML
    private TextField ageField;

    @FXML
    private TextField genderField;

    @FXML
    private TextField breedField;

    @FXML
    private TextField weightField;

    @FXML
    private TextField heightField;

    @FXML
    private TextField imageUrlField;

    @FXML
    private Button saveButton;

    private SqlitePetDAO petDAO;

    // Initialize the controller (assuming the petDAO is initialized properly)
    @FXML
    public void initialize() {
        petDAO = new SqlitePetDAO(SqliteConnectionPetCare.getInstance());
    }

    @FXML
    private void savePet() {
        // Get the input from the fields
        String name = nameField.getText();
        String ageText = ageField.getText();
        String gender = genderField.getText();
        String breed = breedField.getText();
        String weightText = weightField.getText();
        String heightText = heightField.getText();
        String imageUrl = imageUrlField.getText();

        // Validate and parse the inputs, set default values if empty
        int age = 0;
        if (!ageText.trim().isEmpty()) {
            try {
                age = Integer.parseInt(ageText);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input for age: " + ageText);
                return;  // Exit method if invalid input
            }
        }

        double weight = 0.0;
        if (!weightText.trim().isEmpty()) {
            try {
                weight = Double.parseDouble(weightText);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input for weight: " + weightText);
                return;  // Exit method if invalid input
            }
        }

        double height = 0.0;
        if (!heightText.trim().isEmpty()) {
            try {
                height = Double.parseDouble(heightText);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input for height: " + heightText);
                return;  // Exit method if invalid input
            }
        }

        // Now create the new Pet object
        Pet newPet = new Pet(0, name, age, gender, breed, weight, height, imageUrl);

        // Save the pet to the database
        petDAO.addPet(newPet);

        // Clear the form after saving
        clearForm();
    }

    private void clearForm() {
        nameField.clear();
        ageField.clear();
        genderField.clear();
        breedField.clear();
        weightField.clear();
        heightField.clear();
        imageUrlField.clear();
    }

    @FXML
    public void handleBackButton(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("home-view.fxml"));
        Scene scene = new Scene(loader.load(), HelloApplication.WIDTH, HelloApplication.HEIGHT);
        stage.setScene(scene);
    }
}
