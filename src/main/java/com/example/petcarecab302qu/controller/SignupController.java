package com.example.petcarecab302qu.controller;

import com.example.petcarecab302qu.model.Contact;
import com.example.petcarecab302qu.model.SqliteContactDAO;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

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
    private TextField passwordField;


    @FXML
    private Text errorMessage;

    private SqliteContactDAO contactDAO = new SqliteContactDAO();

    @FXML
    public void handleSignUpAction(){
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
}
