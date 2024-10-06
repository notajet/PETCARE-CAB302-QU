package com.example.petcarecab302qu.controller;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.time.LocalDate;


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

    @FXML
    public void initialize() {

        NavigationBar();
        if (logoImage != null) {
            Image logo = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/logo.png")));
            logoImage.setImage(logo);
        }

        amPmComboBox.getSelectionModel().select("AM");
        datePicker.setValue(currentDate);

    }



    @FXML
    public void handleSaveSchedule() {
        // Save schedule
        System.out.println("Schedule saved.");
    }

    @FXML
    public void handleCancelSchedule() {
        // Clear
        eventTypeComboBox.getSelectionModel().clearSelection();
        scheduleHour.clear();
        scheduleMin.clear();
        repeatCheckBox.setSelected(false);
    }
}
