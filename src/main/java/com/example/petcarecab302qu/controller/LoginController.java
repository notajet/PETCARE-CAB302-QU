package com.example.petcarecab302qu.controller;

import com.example.petcarecab302qu.HelloApplication;
import com.example.petcarecab302qu.model.SqliteConnection;
import com.example.petcarecab302qu.model.SqliteContactDAO;
import com.example.petcarecab302qu.util.SceneLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import java.sql.Connection;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.sql.*;
import java.io.IOException;

import static java.lang.System.out;


public class LoginController {
    @FXML
    private TextField Email;
    @FXML
    private PasswordField Password;
    @FXML
    private Button Confirm;
    @FXML
    private Text error;

    private Connection connection;

    //private SqliteContactDAO connection = new SqliteContactDAO();

    /**
     * A method to handle the login process based on the login details, Email and Password, gathered from the GUI
     */
    @FXML
    public void handlelogin(ActionEvent event) {
        //Gets username and password text
        String email = Email.getText();
        String password = Password.getText();

        //Checks if the firstname field is empty
        if (email.isEmpty()) {
            error.setText("Please provide email.");
            error.setVisible(true);
            return;
        }
        //Checks if the password field is empty
        else if (password.isEmpty()) {
            error.setText("Please provide password.");
            error.setVisible(true);
            return;
        }

        try {
            String query = "SELECT * FROM contacts WHERE email = ? AND password = ?";
            connection = SqliteConnection.getInstance();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                error.setText("Authentication Successful");
                SceneLoader.loadScene(event, "/com/example/petcarecab302qu/homemain-view.fxml");
            } else if (!resultSet.next()) {
                error.setText("Authentication Unsuccessful");
            }
            error.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //Clears fields
        Email.clear();
        Password.clear();
        //Include link to homepage here
    }


    @FXML
    public void handleBackButton(ActionEvent event) throws IOException {
        SceneLoader.handleBackButton(event);
    }

    @FXML
    public void handleSignUpAction(ActionEvent event) {
        SceneLoader.loadScene(event, "/com/example/petcarecab302qu/signup-view.fxml");
    }
}