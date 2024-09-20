package com.example.petcarecab302qu.controller;

import com.example.petcarecab302qu.model.SqliteConnection;
import com.example.petcarecab302qu.util.PasswordUtil;
import com.example.petcarecab302qu.util.SceneLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.io.IOException;

/** A Class that contains all the methods needed for the login interface to run and be
 *  controlled by the user's input
 */
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

    /**
     * A method to handle the login process based on the login details, Email and Password, gathered from the GUI
     */
    @FXML
    public void handleLogin(ActionEvent event) {
        String email = Email.getText();
        String password = Password.getText();

        if (email.isEmpty()) {
            error.setText("Please provide email.");
            error.setVisible(true);
            return;
        }
        else if (password.isEmpty()) {
            error.setText("Please provide password.");
            error.setVisible(true);
            return;
        }

        try {
            String query = "SELECT password FROM contacts WHERE email = ?";
            connection = SqliteConnection.getInstance();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String storedHashedPassword = resultSet.getString("password");

                String hashedInputPassword = PasswordUtil.hashPassword(password);

                if (storedHashedPassword.equals(hashedInputPassword)) {
                    error.setText("Authentication Successful");
                    SceneLoader.loadScene(event, "/com/example/petcarecab302qu/homemain-view.fxml");
                } else {
                    error.setText("Authentication Unsuccessful");
                }
            } else {
                error.setText("Authentication Unsuccessful");
            }

            error.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Email.clear();
        Password.clear();
    }

    @FXML
    public void handleBackButtonOnHome(ActionEvent event) throws IOException {
        SceneLoader.handleBackOnHome(event);
    }

    @FXML
    public void handleSignUpAction(ActionEvent event) {
        SceneLoader.loadScene(event, "/com/example/petcarecab302qu/signup-view.fxml");
    }
}
