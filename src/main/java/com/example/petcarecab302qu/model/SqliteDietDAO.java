package com.example.petcarecab302qu.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class SqliteDietDAO {

    private Connection connection;

    public SqliteDietDAO() {
        connection = SqliteConnection.getInstance();
        createTable();
    }

    // Create the table with lunch and dinner columns
    private void createTable() {
        try {
            Statement statement = connection.createStatement();

            // Create the table with the correct schema including breakfast, lunch, and dinner
            String createQuery = "CREATE TABLE IF NOT EXISTS diet_plans ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "name VARCHAR NOT NULL,"
                    + "duration INTEGER NOT NULL,"
                    + "breakfast VARCHAR NOT NULL,"
                    + "lunch VARCHAR NOT NULL,"
                    + "dinner VARCHAR NOT NULL"
                    + ")";
            statement.execute(createQuery);

            System.out.println("Table diet_table created successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // Updated addDietPlan method to save breakfast, lunch, and dinner
    public void addDietPlan(String name, int duration, String breakfast, String lunch, String dinner) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO diet_plans (name, duration, breakfast, lunch, dinner) VALUES (?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, name);
            statement.setInt(2, duration);
            statement.setString(3, breakfast);
            statement.setString(4, lunch);
            statement.setString(5, dinner);
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int generatedId = generatedKeys.getInt(1);
                System.out.println("Diet Plan saved with ID: " + generatedId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
