package com.example.petcarecab302qu.controller;

import com.example.petcarecab302qu.HelloApplication;
import com.example.petcarecab302qu.model.SqliteContactDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;

import java.io.IOException;
import java.sql.PreparedStatement;

public class LoginController {
    @FXML
    private TextField Firstname;
    @FXML
    private PasswordField Password;
    @FXML
    private Button Confirm;
    @FXML
    private Text error;
    @FXML
    private Connection connection;
    @FXML
    private SqliteContactDAO contactDAO = new SqliteContactDAO();

    protected void handlelogin() {
        //Gets username and password text
        String firstname = Firstname.getText();
        String password = Password.getText();

        //Checks if the firstname field is empty
        if (firstname.isEmpty())
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
            String query = "SELECT * FROM contacts WHERE firstName = ? AND password = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1,firstname);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                // Valid firstname and password
            } else {
                // Invalid firstname and password
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //Clears Password field
        Firstname.clear();
        Password.clear();



    }
}
