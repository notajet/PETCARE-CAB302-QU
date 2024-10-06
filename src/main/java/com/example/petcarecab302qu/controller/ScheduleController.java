package com.example.petcarecab302qu.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.time.DayOfWeek;
import java.time.LocalDate;

import java.time.YearMonth;
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
    private Label selectLabel;
    @FXML
    private GridPane calendarGrid;

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

        // Load the initial calendar for the current month
        loadCalendar(LocalDate.now());


    }


    /**
     * Loads the calendar for the given month.
     * Clears and populates the calendar grid based on the selected month.
     */
    private void loadCalendar(LocalDate selectedDate) {
        calendarGrid.getChildren().clear();

        YearMonth yearMonth = YearMonth.of(selectedDate.getYear(), selectedDate.getMonth());
        LocalDate firstOfMonth = yearMonth.atDay(1);
        int daysInMonth = yearMonth.lengthOfMonth();
        int dayOfWeekOffset = firstOfMonth.getDayOfWeek().getValue() % 7;

        int row = 1;
        int col = dayOfWeekOffset;

        // Create buttons for each day in the month
        for (int day = 1; day <= daysInMonth; day++) {
            final int selectedDay = day;
            Button dayButton = new Button(String.valueOf(day));
            dayButton.setOnAction(event -> selectLabel.setText("Selected Day: " + LocalDate.of(selectedDate.getYear(), selectedDate.getMonth(), selectedDay)));

            // Add button to the grid
            calendarGrid.add(dayButton, col, row);

            col++;
            if (col > 6) {
                col = 0;
                row++;
            }
        }
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