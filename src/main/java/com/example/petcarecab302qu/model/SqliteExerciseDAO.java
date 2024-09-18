package com.example.petcarecab302qu.model;

import java.sql.Connection;
//import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SqliteExerciseDAO {
    private Connection connection;

    public SqliteExerciseDAO() {
        connection = SqliteConnection.getInstance();
        createExerciseTable();
    }

    private void createExerciseTable() {
        try {
            Statement statement = connection.createStatement();
            // Ensure space between columns
            String query = "CREATE TABLE IF NOT EXISTS exercise ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    //+ "pet_id INTEGER NOT NULL"
                    + "petname VARCHAR NOT NULL,"
                    + "type VARCHAR NOT NULL,"
                    + "duration VARCHAR NOT NULL,"
                    + "notes VARCHAR NOT NULL"
                    //+ "FOREIGN KEY (pet_id) REFERENCES pets(id),"
                    + ")";

            statement.execute(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //@Override
    public void addExercise(Exercise exercise) {
        try {
            // Correct number of fields in the prepared statement and SQL syntax
            PreparedStatement statement = connection.prepareStatement("INSERT INTO exercise (petname, type, duration, notes) VALUES (?, ?, ?, ?)");
            statement.setString(1, exercise.getEName());
            statement.setString(2, exercise.gettype());
            statement.setDouble(3, exercise.getduration());
            statement.setString(4, exercise.getnotes());
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                exercise.setEId(generatedKeys.getInt(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Exercise getExercise(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM exercise WHERE id = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String petname = resultSet.getString("petname");
                String type = resultSet.getString("type");
                Double duration = resultSet.getDouble("duration");
                String notes = resultSet.getString("notes");
                //String password = resultSet.getString("password");
                Exercise exercise = new Exercise(id, petname, type, duration, notes);
                exercise.setEId(id);
                return exercise;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
