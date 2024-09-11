package com.example.petcarecab302qu.controller;

import com.example.petcarecab302qu.model.Signup;
import com.example.petcarecab302qu.model.IContactDAO;
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
    private PasswordField passwordField;
    @FXML
    private Text errorMessage;

    private Signup signupService;

    public void setContactDAO(IContactDAO contactDAO) {
        this.signupService = new Signup(contactDAO);
    }

    @FXML
    public void handleSignup() {
        String firstName = firstNameField.getText().trim();
        String lastName = lastNameField.getText().trim();
        String email = emailField.getText().trim();
        String phone = phoneField.getText().trim();
        String password = passwordField.getText().trim();

        String result = signupService.signup(firstName, lastName, email, phone, password);
        errorMessage.setText(result);
    }
}
