package com.example.petcarecab302qu.controller;

import com.example.petcarecab302qu.model.Contact;
import com.example.petcarecab302qu.model.SqliteContactDAO;
import com.example.petcarecab302qu.model.IContactDAO;
import com.example.petcarecab302qu.util.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;

/**
 * Controller class for managing the sign-up process in the Pet Care application.
 * Handles user input, validates the data, and creates a new user account in the database.
 */
public class SignupController {

    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField phoneField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Text errorMessage;
    @FXML
    private IContactDAO contactDAO;

    /**
     * Constructs a new SignupController and initializes the contactDAO.
     */
    public SignupController() {
        this.contactDAO = new SqliteContactDAO();
    }

    /**
     * Handles the sign-up action when the user submits the sign-up form.
     * Validates input fields, checks if the email already exists, hashes the password,
     * and adds the new user to the database if all validations pass.
     *
     * @param event The action event triggered by the sign-up button.
     */
    @FXML
    public void handleSignUpAction(ActionEvent event) {
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String email = emailField.getText();
        String phone = phoneField.getText();
        String password = passwordField.getText();

        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || phone.isEmpty() || password.isEmpty()) {
            errorMessage.setText("All fields are required!");
            errorMessage.setVisible(true);
            return;
        }

        if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            errorMessage.setText("Please enter a valid email address.");
            errorMessage.setStyle("-fx-text-fill: red;");
            errorMessage.setVisible(true);
            return;
        }

        if (password.length() < 8 || !password.matches(".*\\d.*") || !password.matches(".*[a-zA-Z].*")) {
            errorMessage.setText("Password must be at least 8 characters long and contain both letters and numbers.");
            errorMessage.setStyle("-fx-text-fill: red;");
            errorMessage.setVisible(true);
            return;
        }

        if (contactDAO.emailExists(email)) {
            errorMessage.setText("Email already exists. Please use a different email.");
            errorMessage.setStyle("-fx-text-fill: red;");
            errorMessage.setVisible(true);
            return;
        }

        String hashedPassword = PasswordUtil.hashPassword(password);

        Contact newContact = new Contact(firstName, lastName, email, phone, hashedPassword);
        contactDAO.addContact(newContact);

        firstNameField.clear();
        lastNameField.clear();
        emailField.clear();
        phoneField.clear();
        passwordField.clear();
        errorMessage.setText("Sign-Up Successful!");
        errorMessage.setStyle("-fx-text-fill: green;");
        errorMessage.setVisible(true);
        SceneLoader.loadScene(event, "/com/example/petcarecab302qu/homemain-view.fxml");
    }

    /**
     * Handles the back button action to navigate to the previous screen.
     *
     * @param event The action event triggered by the back button.
     * @throws IOException If the scene fails to load.
     */
    @FXML
    public void handleBackButtonOnHome(ActionEvent event) throws IOException {
        SceneLoader.handleBackOnHome(event);
    }

    /**
     * Handles the action to navigate to the login screen.
     *
     * @param event The action event triggered by the login button.
     */
    @FXML
    public void handleLogin(ActionEvent event) {
        SceneLoader.loadScene(event, "/com/example/petcarecab302qu/login-view.fxml");
    }
}