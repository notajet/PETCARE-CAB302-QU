package com.example.petcarecab302qu.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * A data access object (DAO) class for managing exercise records in the Pet Care application using SQLite.
 * Provides methods to create the exercise table, add new exercises, and retrieve exercises from the database.
 */
public class SqliteExerciseDAO implements IExerciseDAO {
    private Connection connection;

    public SqliteExerciseDAO() {
        connection = SqliteConnection.getInstance();
        createExerciseTable();
    }

    /**
     * Creates the exercise table in the database if it does not already exist
     * The table contains columns for the exercise ID, date, type, duration, and notes
     */
    private void createExerciseTable() {
        try {
            Statement statement = connection.createStatement();
            String query = "CREATE TABLE IF NOT EXISTS exercise ("
                    + "exerciseId INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "petName TEXT NOT NULL,"
                    + "date TEXT NOT NULL,"
                    + "type VARCHAR NOT NULL,"
                    + "duration VARCHAR NOT NULL,"
                    + "notes TEXT NULL"
                    + ")";

            statement.execute(query);
        } catch (SQLException e) {
            System.err.println("Error creating exercise table:" + e.getMessage());
        }
    }

    /**
     * Adds exercise to the exercise table in the database
     * The generated ID for the exercise is set on the provided Exercise object
     *
     * @param exercise The Exercise object containing the details of the new exercise
     */
    public void addExercise(Exercise exercise) {
        String query = "INSERT INTO exercise (petName, date, type, duration, notes) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            if (connection.isClosed()) {
                System.out.println("Database connection is closed. Cannot add exercise.");
                return;
            }
            statement.setString(1, exercise.getPetName());
            statement.setString(2, exercise.getDate());
            statement.setString(3, exercise.getType());
            statement.setInt(4, exercise.getDuration());
            statement.setString(5, exercise.getNotes());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error adding exercise to database:" + e.getMessage());
        }
    }

    /**
     * Retrieves an exercise from the database based on its ID
     *
     * @param exerciseId The ID of the exercise to retrieve
     * @return The Exercise object if found, or null if the exercise is not found
     */
    public Exercise getExercise(int exerciseId) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM exercise WHERE id = ?");
            statement.setInt(1, exerciseId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String petName = resultSet.getString("petName");
                String date = resultSet.getString("date");
                String type = resultSet.getString("type");
                Integer duration = resultSet.getInt("duration");
                String notes = resultSet.getString("notes");
                Exercise exercise = new Exercise(petName,date, type, duration, notes);
                exercise.setExerciseId(exerciseId);
                return exercise;
            }
        } catch (SQLException e) {
            System.err.println("Error getting exercise:" + e.getMessage());
        }
        return null;
    }

    /**
     * Retrieves all exercise from the database
     * @return a list of exercise
     */
    public List<Exercise> getAllExercises() {
        List<Exercise> exercises = new ArrayList<>();
        String query = "SELECT * FROM exercise";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String petName = resultSet.getString("petName");
                String date = resultSet.getString("date");
                String type = resultSet.getString("type");
                int duration = resultSet.getInt("duration");
                String notes = resultSet.getString("notes");
                exercises.add(new Exercise(petName, date, type, duration, notes));
            }
        } catch (SQLException e) {
            System.err.println("Error getting exercise: " + e.getMessage());
        }
        return exercises;
    }
}
