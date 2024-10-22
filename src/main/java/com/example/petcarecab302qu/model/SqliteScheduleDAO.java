package com.example.petcarecab302qu.model;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class SqliteScheduleDAO implements IScheduleDAO{
    private Connection connection;

    public SqliteScheduleDAO() {
        connection = SqliteConnection.getInstance();
        createScheduleTable();
    }

    /**
     * Creates the schedule table in the database if it does not already exist.
     * The table contains columns for the schedule ID, data, event, time and repeat
     */
    private void createScheduleTable() {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS schedules (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "date TEXT NOT NULL," +
                "eventType TEXT NOT NULL," +
                "time TEXT NOT NULL," +
                //"repeat INTEGER NOT NULL" +
                "complete TEXT NOT NULL"+
                ");";
        try (Statement statement = connection.createStatement()) {
            statement.execute(createTableSQL);
        } catch (SQLException e) {
            System.out.println("Error creating schedules table: " + e.getMessage());
        }

    }

    /**
     * Adds a new task to the schedule table in the database
     *
     * @param date The date of the schedule
     * @param eventType The type of the event
     * @param time The time of the event
     */
    public void addSchedule(LocalDate date, String eventType, String time) {
        String query = "INSERT INTO schedules (date, eventType, time, complete) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {

            if (connection.isClosed()) {
                System.out.println("Database connection is closed. Cannot add schedule.");
                return;
            }

            statement.setString(1, date.toString());
            statement.setString(2, eventType);
            statement.setString(3, time);
            statement.setString(4, "NO" );
            statement.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error adding schedule:" + e.getMessage());
        }
    }

    /**
     * Retrieves all task from the schedule table in the database based on selected date
     *
     * @param date The date for which schedule of task to retrieve
     * @return A list of event descriptions stored in the database for that date
     */
    public List<String> getSchedules(LocalDate date) {
        List<String> schedules = new ArrayList<>();
        String query = "SELECT eventType, time, complete FROM schedules WHERE date = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, date.toString());
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String eventType = resultSet.getString("eventType");
                String time = resultSet.getString("time");
                schedules.add(eventType + " at " + time);

            }

        } catch (SQLException e) {
            System.err.println("Error getting schedules:" + e.getMessage());
        }

        return schedules;
    }

    /**
     * Updates the completion status of a task in database
     *
     * @param date The date of the task
     * @param task The event type and time combined string
     * @param complete true or false for the task is completed
     */
    public void updateTaskCompletionStatus(LocalDate date, String task, boolean complete) {
        String query = "UPDATE schedules SET complete = ? WHERE date = ? AND (eventType || ' at ' || time) = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, complete ? "YES" : "NO");
            statement.setString(2, date.toString());
            statement.setString(3, task);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error updating completion status:" + e.getMessage());
        }
    }

    /**
     * Gets the completed tasks
     * @param date The date of the task
     * @param task The event type and time details
     * @return true if the task is completed (yes), false otherwise (no)
     */
    public boolean getCompletionStatusForTask(LocalDate date, String task) {
        String query = "SELECT complete FROM schedules WHERE date = ? AND (eventType || ' at ' || time) = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, date.toString());
            statement.setString(2, task);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getString("complete").equals("YES");
            }
        } catch (SQLException e) {
            System.err.println("Error getting completion status:" + e.getMessage());
        }
        return false;
    }




}
