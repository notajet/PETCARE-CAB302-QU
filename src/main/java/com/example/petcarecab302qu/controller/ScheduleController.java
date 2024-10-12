package com.example.petcarecab302qu.controller;

import com.example.petcarecab302qu.model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Objects;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * Controller for the schedule page
 * Handles calendar display, logging of events, and manages completion of events
 */
public class ScheduleController extends NavigationController {
    public VBox petBox;
    private SqliteScheduleDAO scheduleDAO = new SqliteScheduleDAO();
    private SqlitePetDAO petDAO = new SqlitePetDAO();

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

    @FXML
    private GridPane calendar;

    private Button selectedDateButton;

    private LocalDate currentDate = LocalDate.now();

    public LocalDate selectedDate = null;

    @FXML
    private ListView<HBox> todayTaskList;

    @FXML
    private Text errorMessage;


    /**
     * Initializes the navigation bar, logo image, and default stage for AM/PM and DatePicker
     * Loads the current dates month calendar and any changes in DatePicker
     */
    @FXML
    public void initialize() {
        NavigationBar();

        if (logoImage != null) {
            Image logo = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/logo.png")));
            logoImage.setImage(logo);
        }

        amPmComboBox.getSelectionModel().select("AM");
        datePicker.setValue(currentDate);

        loadCalendar(currentDate);
        datePicker.valueProperty().addListener((observable, oldDate, newDate) -> loadCalendar(newDate));
    }

    /**
     * Loads the calendar for the selected day/month
     * Clears existing calendar dates on different days and replace it with new dates based on selected dates on selected month
     *
     * @param selectedDate the selected date on how the calendar will be loaded
     */
    private void loadCalendar(LocalDate selectedDate) {
        calendar.getChildren().clear();  // Clear previous buttons

        //headers for the days of the week
        addDayHeaders();

        YearMonth yearMonth = YearMonth.of(selectedDate.getYear(), selectedDate.getMonth());
        LocalDate firstOfMonth = yearMonth.atDay(1);
        int daysInMonth = yearMonth.lengthOfMonth();
        //monday as first day of the week
        int dayOfWeekOffset = (firstOfMonth.getDayOfWeek().getValue() - 1) % 7;

        int row = 1;
        int col = dayOfWeekOffset;

        // highlights todays date
        LocalDate today = LocalDate.now();

        // create buttons for each day in the month
        for (int day = 1; day <= daysInMonth; day++) {
            Button dateButton = new Button(String.valueOf(day));

            dateButton.setPrefWidth(40);
            dateButton.setPrefHeight(40);

            if (selectedDate.getYear() == today.getYear() &&
                    selectedDate.getMonth() == today.getMonth() &&
                    day == today.getDayOfMonth()) {
                dateButton.setStyle("-fx-border-radius: 50%; -fx-background-color: lightblue; -fx-border-color: red;");
            }

            dateButton.setOnAction(this::selectedClickedOnDate);

            calendar.add(dateButton, col, row);

            col++;
            if (col > 6) {
                col = 0;
                row++;
            }
        }
    }

    /**
     * Handles the event action of selected date on calendar
     * Updates and highlights the selected date
     * @param event action event triggers when a date is selected
     */
    private void selectedClickedOnDate(ActionEvent event) {
        Button clickedButton = (Button) event.getSource();
        int day = Integer.parseInt(clickedButton.getText());
        selectedDate = LocalDate.of(currentDate.getYear(), currentDate.getMonth(), day);
        highlightSelectDate(clickedButton, selectedDate);
    }


    /**
     * Adds day headers from Sunday to Saturday to the first row of the calendar
     */
    private void addDayHeaders() {
        String[] dayOfWeek = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
        for (int i = 0; i < dayOfWeek.length; i++) {
            Label dayLabel = new Label(dayOfWeek[i]);
            dayLabel.setStyle("-fx-font-weight: bold; -fx-padding: 5px;");
            dayLabel.setPrefWidth(40);
            calendar.add(dayLabel, i, 0);
        }
    }





    /**
     * Handles the selection of date button, changing selected dates appearance and keeping the actual current date highlighted 
     * @param dateButton selected date
     * @param selectedDate currently selected date
     */
    private void highlightSelectDate(Button dateButton, LocalDate selectedDate) {
        LocalDate today = LocalDate.now();
        // Reset
        if (selectedDateButton != null) {
            // Keep the highlight for today date
            if (selectedDateButton.getText().equals(String.valueOf(today.getDayOfMonth())) &&
                    selectedDate.getYear() == today.getYear() &&
                    selectedDate.getMonth() == today.getMonth()) {
                selectedDateButton.setStyle("-fx-background-color: lightblue; -fx-border-color: red; -fx-border-radius: 50%;");
            } else {
                // reset to default style
                selectedDateButton.setStyle("");
            }
        }

        // Highlight the currently selected day button
        dateButton.setStyle("-fx-border-color: orange; -fx-background-radius: 50%; -fx-border-radius: 50%; -fx-border-width: 2px; -fx-padding: 2px;");
        selectedDateButton = dateButton;

    }


    /**
     * Handles the save button action
     * Logs the event, validates inputs and updates schedule list with new event 
     */
    @FXML
    public void handleSaveSchedule() {
        // Get the event type and time input
        String eventType = eventTypeComboBox.getSelectionModel().getSelectedItem();
        String hour = scheduleHour.getText();
        String minute = scheduleMin.getText();
        String amPm = amPmComboBox.getSelectionModel().getSelectedItem();

        // ensure fields are not empty ( repeat checkbox is optional)
        if (eventType == null || hour.isEmpty() || minute.isEmpty()) {
            errorMessage.setText("Please fill in all fields.");
            errorMessage.setVisible(true);
            return;
        }

        String loggedScheduleEvent = selectedDate.toString() + ":" + eventType + " at " + hour + ":" + minute + " " + amPm;

        // checkbox to mark the schedule as done
        CheckBox checkBox = new CheckBox();
        Label scheduleEventLabel = new Label(loggedScheduleEvent);

        // HBox hold the checkbox and the event
        HBox hbox = new HBox(10);
        hbox.getChildren().addAll(checkBox, scheduleEventLabel);
        todayTaskList.getItems().add(hbox);

        // Clear
        eventTypeComboBox.getSelectionModel().clearSelection();
        scheduleHour.clear();
        scheduleMin.clear();
        repeatCheckBox.setSelected(false);

        //NEED TO SAVE TO DATABASE
    }

    /**
     * Handles the cancel button action
     * clears input and resets selections
     */
    @FXML
    public void handleCancelSchedule() {
        eventTypeComboBox.getSelectionModel().clearSelection();
        scheduleHour.clear();
        scheduleMin.clear();
        repeatCheckBox.setSelected(false);
    }
}
