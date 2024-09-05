/*package com.example.petcarecab302qu.controller;
import com.example.petcarecab302qu.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;
public class DietController {





    public void handleBackButton(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("home-view.fxml"));
        Scene scene = new Scene(loader.load(), HelloApplication.WIDTH, HelloApplication.HEIGHT);
        stage.setScene(scene);
    }
}
*/package com.example.petcarecab302qu.controller;

import com.example.petcarecab302qu.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class DietController {

    @FXML
    private AnchorPane rootPane;




    public void handleAddDietPlan(ActionEvent event) {
        TextArea newDietPlan = new TextArea("New Diet Plan Details");
        newDietPlan.setPrefSize(300, 200);
        newDietPlan.setWrapText(true);
        newDietPlan.setStyle("-fx-font-size: 14px; -fx-padding: 10px;");

        AnchorPane.setLeftAnchor(newDietPlan, 350.0);
        AnchorPane.setTopAnchor(newDietPlan, 20.0);

        rootPane.getChildren().add(newDietPlan);

        System.out.println("Add Diet Plan button clicked and box created!");

    }




    public void handleBackButton(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("home-view.fxml"));
        Scene scene = new Scene(loader.load(), HelloApplication.WIDTH, HelloApplication.HEIGHT);
        stage.setScene(scene);
    }
}

