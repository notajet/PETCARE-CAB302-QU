package com.example.petcarecab302qu.model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Singleton class for managing a connection to the SQLite database for the Pet Care application.
 * Ensures that only one instance of the database connection is used throughout the application.
 */
public class SqliteConnection {

    private static Connection instance = null;

    /**
     * Private constructor to prevent direct instantiation.
     * Establishes a connection to the SQLite database using the specified URL.
     */
    private SqliteConnection() {
        String url = "jdbc:sqlite:petcare.db";
        try {
            instance = DriverManager.getConnection(url);
            System.out.println("Database connection established.");
        } catch (SQLException sqlEx) {
            System.err.println("Failed to create connection: " + sqlEx.getMessage());
        }
    }

    /**
     * Returns the single instance of the database connection.
     * If the connection is not established or closed, it creates a new connection.
     *
     * @return The single instance of the SQLite database connection.
     */
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