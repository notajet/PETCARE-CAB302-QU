package com.example.petcarecab302qu.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SqliteConnectionPetCare {

    private static Connection instance = null;

    private SqliteConnectionPetCare() {
        // Private constructor to prevent instantiation
    }

    public static Connection getInstance() {
        if (instance == null) {
            try {
                String url = "jdbc:sqlite:petcare.db";  // Your SQLite database path
                instance = DriverManager.getConnection(url);
                createPetsTable(instance);  // Ensure pets table exists
            } catch (SQLException e) {
                System.out.println("Error connecting to the SQLite database: " + e.getMessage());
            }
        }
        return instance;
    }

    // Method to create the pets table if it doesn't exist
    private static void createPetsTable(Connection connection) {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS pets (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT NOT NULL," +
                "age INTEGER," +
                "gender TEXT," +
                "breed TEXT," +
                "weight REAL," +
                "height REAL," +
                "imageUrl TEXT" +
                ");";
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(createTableSQL);
        } catch (SQLException e) {
            System.out.println("Error creating pets table: " + e.getMessage());
        }
    }
}
