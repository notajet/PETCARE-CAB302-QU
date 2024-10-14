package com.example.petcarecab302qu.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
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
     * The table contains columns for the exercise ID, type, duration, and notes
     */
    private void createExerciseTable() {
        try {
            Statement statement = connection.createStatement();
            String query = "CREATE TABLE IF NOT EXISTS exercise ("
                    + "exerciseId INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "petName VARCHAR NOT NULL,"
                    + "date VARCHAR NOT NULL"
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
     * Adds exercise to the exercise table in the database
     * The generated ID for the exercise is set on the provided Exercise object
     *
     * @param exercise The Exercise object containing the details of the new exercise
     */
    //@Override
    public void addExercise(Exercise exercise) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO exercise (petName, date, type, duration, notes) VALUES (?, ?, ?, ?, ?)");
            statement.setString(1, exercise.getPetName());
            statement.setString(2, exercise.getDate());
            statement.setString(3, exercise.getType());
            statement.setInt(4, exercise.getDuration());
            statement.setString(5, exercise.getNotes());
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
     * Retrieves an exercise from the database based on its ID
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
                String date = resultSet.getString("date");
                String type = resultSet.getString("type");
                Integer duration = resultSet.getInt("duration");
                String notes = resultSet.getString("notes");
                Exercise exercise = new Exercise(date, type, duration, notes);
                exercise.setEId(id);
                return exercise;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Retrieves all exercises for a specific pet.
     *
     * @param petName The name of the pet to retrieve exercises for.
     * @return A list of Exercise objects for the specified pet.
     */
    public List<Exercise> getExercisesByPet(String petName) {
        List<Exercise> exercises = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM exercise WHERE petName = ? ORDER BY exerciseId DESC");
            statement.setString(1, petName); // Filter by pet name
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String date = resultSet.getString("date");
                String type = resultSet.getString("type");
                int duration = resultSet.getInt("duration");
                String notes = resultSet.getString("notes");
                Exercise exercise = new Exercise(petName, date, type, duration, notes);
                exercise.setExerciseId(resultSet.getInt("exerciseId"));
                exercises.add(exercise);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return exercises;
    }

}
