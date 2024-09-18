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
                    + "description VARCHAR NOT NULL,"
                    //+ "FOREIGN KEY (pet_id) REFERENCES pets(id),"
                    + ")";

            statement.execute(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //@Override
    public void addExercise(Contact contact) {
        try {
            // Correct number of fields in the prepared statement and SQL syntax
            PreparedStatement statement = connection.prepareStatement("INSERT INTO exercise (petname, type, description) VALUES (?, ?, ?)");
            statement.setString(1, contact.getFirstName());
            statement.setString(2, contact.getLastName());
            statement.setString(3, contact.getPhone());
            statement.setString(4, contact.getEmail());
            statement.setString(5, contact.getPassword());  // Add password to the query
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                contact.setId(generatedKeys.getInt(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
