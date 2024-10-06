package com.example.petcarecab302qu.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Controller for the schedule page.
 * It handles calendar display, logging of tasks, and management of schedules.
 */
public class ScheduleController extends NavigationController {

    @FXML
    public ImageView logoImage;

    @FXML
    private ComboBox<String> eventTypeComboBox;

    @FXML
    private TextField scheduleHour;

    @FXML
    private TextField scheduleMin;

    @FXML
    private ComboBox<String> amPmComboBox;

    @FXML
    private CheckBox repeatCheckBox;

    @FXML
    private DatePicker datePicker;

    private LocalDate currentDate = LocalDate.now();


    /**
     * Initializes the controller after its root element has been completely processed.
     */
    @FXML
    public void initialize() {

        NavigationBar();
        if (logoImage != null) {
            Image logo = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/logo.png")));
            logoImage.setImage(logo);
        }

        // Initialize the AM/PM ComboBox with default value
        amPmComboBox.getSelectionModel().select("AM");

        // Initialize DatePicker with the current date
        datePicker.setValue(currentDate);


    }


    @FXML
    public void handleSaveSchedule() {
        // Save schedule logic here
        System.out.println("Schedule saved.");
    }

    @FXML
    public void handleCancelSchedule() {
        // Clear the form fields
        eventTypeComboBox.getSelectionModel().clearSelection();
        scheduleHour.clear();
        scheduleMin.clear();
        amPmComboBox.getSelectionModel().select("AM");
        repeatCheckBox.setSelected(false);
    }
}