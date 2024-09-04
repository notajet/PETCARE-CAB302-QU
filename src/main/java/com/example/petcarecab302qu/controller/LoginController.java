package com.example.petcarecab302qu.controller;

import com.example.petcarecab302qu.HelloApplication;
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
import java.sql.ResultSet;
import java.io.IOException;


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
                // firstname and password contained in database
                // Will need to then lead to homepage, will be put in later

            }
            else {
                // firstname and password not contained in database
                error.setText("Authentication Unsuccessful");
                error.setVisible(true);
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //Clears fields
        Firstname.clear();
        Password.clear();
        error.setText("Authentication Successful");
        error.setVisible(true);
        //Include link to homepage here
    }


    public void handleBackButton(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("home-view.fxml"));
        Scene scene = new Scene(loader.load(), HelloApplication.WIDTH, HelloApplication.HEIGHT);
        stage.setScene(scene);
    }
}
