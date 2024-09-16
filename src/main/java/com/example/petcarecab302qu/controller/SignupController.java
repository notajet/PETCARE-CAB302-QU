package com.example.petcarecab302qu.controller;

import com.example.petcarecab302qu.HelloApplication;
import com.example.petcarecab302qu.model.Contact;
import com.example.petcarecab302qu.model.SqliteContactDAO;
import com.example.petcarecab302qu.model.IContactDAO;
import com.example.petcarecab302qu.util.SceneLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

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

    public SignupController() {
        this.contactDAO = new SqliteContactDAO();
    }


    @FXML
    public void handleSignUpAction() {
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
            return;  // Prevent adding the contact
        }

        Contact newContact = new Contact(firstName, lastName, email, phone, password);
        contactDAO.addContact(newContact);

        // Clear the form after successful sign-up
        firstNameField.clear();
        lastNameField.clear();
        emailField.clear();
        phoneField.clear();
        passwordField.clear();
        errorMessage.setText("Sign-Up Successful!");
        errorMessage.setStyle("-fx-text-fill: green;");
        errorMessage.setVisible(true);
    }

    @FXML
    public void handleBackButton(ActionEvent event) throws IOException {
        SceneLoader.handleBackButton(event);
    }

    @FXML
    public void handleLogin(ActionEvent event) {
        SceneLoader.loadScene(event, "/com/example/petcarecab302qu/login-view.fxml");
    }
}