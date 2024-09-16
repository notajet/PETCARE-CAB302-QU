package com.example.petcarecab302qu.model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqliteConnection {
    private static Connection instance = null;

    private SqliteConnection() {
        String url = "jdbc:sqlite:petcare.db";
        try {
            instance = DriverManager.getConnection(url);
            System.out.println("Database connection established.");
        } catch (SQLException sqlEx) {
            System.err.println("Failed to create connection: " + sqlEx.getMessage());
        }
    }


    public static Connection getInstance() {
        try {
            if (instance == null || instance.isClosed()) {
                System.out.println("Opening new database connection.");
                new SqliteConnection();
            } else {
                System.out.println("Reusing existing open connection.");
            }
        } catch (SQLException e) {
            System.err.println("Error checking connection status: " + e.getMessage());
        }
        return instance;
    }
}