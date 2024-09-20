package com.example.petcarecab302qu.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * A data access object (DAO) class for managing exercise records in the Pet Care application using SQLite.
 * Provides methods to create the exercise table, add new exercises, and retrieve exercises from the database.
 */
public class SqliteExerciseDAO {
    private Connection connection;

    public SqliteExerciseDAO() {
        connection = SqliteConnection.getInstance();
        createExerciseTable();
    }

    /**
     * Creates the exercise table in the database if it does not already exist.
     * The table contains columns for the exercise ID, type, duration, and notes.
     */
    private void createExerciseTable() {
        try {
            Statement statement = connection.createStatement();
            String query = "CREATE TABLE IF NOT EXISTS exercise ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "type VARCHAR NOT NULL,"
                    + "duration VARCHAR NOT NULL,"
                    + "notes VARCHAR NOT NULL"
                    + ")";

            statement.execute(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Adds a new exercise to the exercise table in the database.
     * The generated ID for the exercise is set on the provided Exercise object.
     *
     * @param exercise The Exercise object containing the details of the new exercise.
     */
    //@Override
    public void addExercise(Exercise exercise) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO exercise (type, duration, notes) VALUES (?, ?, ?)");
            statement.setString(1, exercise.gettype());
            statement.setDouble(2, exercise.getduration());
            statement.setString(3, exercise.getnotes());
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                exercise.setEId(generatedKeys.getInt(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves an exercise from the database based on its ID.
     *
     * @param id The ID of the exercise to retrieve.
     * @return The Exercise object if found, or null if the exercise is not found.
     */
    public Exercise getExercise(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM exercise WHERE id = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String type = resultSet.getString("type");
                Double duration = resultSet.getDouble("duration");
                String notes = resultSet.getString("notes");
                Exercise exercise = new Exercise(type, duration, notes);
                exercise.setEId(id);
                return exercise;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
