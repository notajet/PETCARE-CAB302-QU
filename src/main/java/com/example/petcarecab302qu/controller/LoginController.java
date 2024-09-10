package com.example.petcarecab302qu.controller;

import com.example.petcarecab302qu.HelloApplication;
import com.example.petcarecab302qu.model.SqliteConnectionContacts;
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
    @FXML
    /***
     * Handles the login process taking user inputs from the GUI.
     */
    public void handlelogin() {
        //Gets username and password text
        String email = Email.getText();
        String password = Password.getText();

        //Checks if the firstname field is empty
        if (email.isEmpty())
        {
            error.setText("Please provide firstname.");
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

        //Checks that username and password are both contained in the database under the same ID
        try {
            String query = "SELECT * FROM contacts WHERE email = ? AND password = ?";
            connection = SqliteConnectionContacts.getInstance();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1,email);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                // firstname and password contained in database
                error.setText("Authentication Successful");
                // Will need to then lead to homepage, will be put in later
            }
            else if (!resultSet.next())
            {
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


    public void handleBackButton(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("home-view.fxml"));
        Scene scene = new Scene(loader.load(), HelloApplication.WIDTH, HelloApplication.HEIGHT);
        stage.setScene(scene);
    }

    public void handleSignUpAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/petcarecab302qu/signup-view.fxml"));
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(loader.load(), HelloApplication.WIDTH, HelloApplication.HEIGHT);
            stage.setScene(scene);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
