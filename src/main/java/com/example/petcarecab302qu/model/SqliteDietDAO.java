package com.example.petcarecab302qu.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * A data access object (DAO) class for managing diet plans in an SQLite database.
 * Provides methods to create, retrieve, and delete diet plan records from the database.
 */
public class SqliteDietDAO {

    private Connection connection;

    public SqliteDietDAO() {
        connection = SqliteConnection.getInstance();
        createTable();
    }
    private Connection ensureConnection() {
        Connection connection = SqliteConnection.getInstance();
        try {
            if (connection == null || connection.isClosed()) {
                connection = SqliteConnection.getInstance();  // Re-open the connection if it's closed
            }
        } catch (SQLException e) {
            System.err.println("Error ensuring connection: " + e.getMessage());
        }
        return connection;
    }
    /**
     * Creates the diet_plans table in the database if it does not already exist.
     * The table contains columns for the diet plan's ID, name, duration, and meals (breakfast, lunch, dinner).
     */
    private void createTable() {
        try {
            Statement statement = connection.createStatement();
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


    /**
     * Adds a new diet plan to the diet_plans table in the database.
     * The generated ID for the diet plan is set on the provided DietPlan object.
     *
     * @param dietPlan The DietPlan object containing the details of the new diet plan.
     */
    public void addDietPlan(DietPlan dietPlan) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO diet_plans (name, duration, breakfast, lunch, dinner) VALUES (?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS
            );
            statement.setString(1, dietPlan.getName());
            statement.setInt(2, dietPlan.getDuration());
            statement.setString(3, dietPlan.getBreakfast());
            statement.setString(4, dietPlan.getLunch());
            statement.setString(5, dietPlan.getDinner());
            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int generatedId = generatedKeys.getInt(1);
                dietPlan.setId(generatedId);
                System.out.println("Diet Plan saved with ID: " + generatedId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves all diet plans from the diet_plans table in the database.
     *
     * @return A list of DietPlan objects representing all diet plans stored in the database.
     */
    public List<DietPlan> getAllDietPlans() {
        List<DietPlan> dietPlans = new ArrayList<>();
        String query = "SELECT * FROM diet_plans";

        try (Connection connection = ensureConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                DietPlan dietPlan = new DietPlan(
                        rs.getString("name"),
                        rs.getInt("duration"),
                        rs.getString("breakfast"),
                        rs.getString("lunch"),
                        rs.getString("dinner")
                );
                dietPlan.setId(rs.getInt("id"));
                dietPlans.add(dietPlan);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching diet plans: " + e.getMessage());
        }

        return dietPlans;
    }

    public void updateDietPlan(DietPlan dietPlan) {
        String updateQuery = "UPDATE diet_plans SET name = ?, duration = ?, breakfast = ?, lunch = ?, dinner = ? WHERE id = ?";

        try (Connection connection = ensureConnection();
             PreparedStatement stmt = connection.prepareStatement(updateQuery)) {

            stmt.setString(1, dietPlan.getName());
            stmt.setInt(2, dietPlan.getDuration());
            stmt.setString(3, dietPlan.getBreakfast());
            stmt.setString(4, dietPlan.getLunch());
            stmt.setString(5, dietPlan.getDinner());
            stmt.setInt(6, dietPlan.getId());

            stmt.executeUpdate();  // Perform the update
            System.out.println("Diet plan updated successfully.");
        } catch (SQLException e) {
            System.err.println("Error updating diet plan: " + e.getMessage());
        }
    }

    /**
     * Deletes a diet plan from the diet_plans table in the database based on its ID.
     *
     * @param id The ID of the diet plan to delete.
     */
    public void deleteDietPlan(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM diet_plans WHERE id = ?"
            );
            statement.setInt(1, id);
            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Diet Plan with ID " + id + " deleted successfully.");
            } else {
                System.out.println("No Diet Plan found with ID " + id);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
