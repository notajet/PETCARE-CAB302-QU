package com.example.petcarecab302qu.controller;

import com.example.petcarecab302qu.HelloApplication;
import com.example.petcarecab302qu.model.SqliteContactDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
public class LoginController {
    @FXML
    private TextField Username;
    @FXML
    private TextField Password;
    @FXML
    private Button Confirm;
    @FXML
    private Text error;
    @FXML
    private SqliteContactDAO contactDAO = new SqliteContactDAO();
    @FXML
    protected void handlelogin() {
        //Gets username and password text
        String username = Username.getText();
        String password = Password.getText();

        //Checks if the username field is empty
        if (username.isEmpty())
        {
            error.setText("Please provide username.");
            error.setVisible(true);
            return;
        }
        //Checks if the password field is empty
        else if (password.isEmpty())
        {
            error.setText("Please provide Password.");
            error.setVisible(true);
            return;
        }

        //Checks that username and password are both contained in the database





    }
}
