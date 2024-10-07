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

        // Ensure the connection is open before performing any operation
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
         */
        private void createTable() {
            try (Statement statement = ensureConnection().createStatement()) {
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
            } catch (SQLException e) {
                System.err.println("Error creating table: " + e.getMessage());
            }
        }

        /**
         * Adds a new diet plan to the diet_plans table in the database.
         */
        public void addDietPlan(DietPlan dietPlan) {
            String query = "INSERT INTO diet_plans (name, duration, breakfast, lunch, dinner) VALUES (?, ?, ?, ?, ?)";

            try (PreparedStatement statement = ensureConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
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
            } catch (SQLException e) {
                System.err.println("Error saving diet plan: " + e.getMessage());
            }
        }

        /**
         * Retrieves all diet plans from the diet_plans table in the database.
         */
        public List<DietPlan> getAllDietPlans() {
            List<DietPlan> dietPlans = new ArrayList<>();
            String query = "SELECT * FROM diet_plans";

            try (Statement stmt = ensureConnection().createStatement();
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

        /**
         * Updates an existing diet plan in the database.
         */
        public void updateDietPlan(DietPlan dietPlan) {
            String updateQuery = "UPDATE diet_plans SET name = ?, duration = ?, breakfast = ?, lunch = ?, dinner = ? WHERE id = ?";

            try (PreparedStatement stmt = ensureConnection().prepareStatement(updateQuery)) {
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
         */
        public void deleteDietPlan(int dietPlanId) {
            String query = "DELETE FROM diet_plans WHERE id = ?";

            try (PreparedStatement stmt = ensureConnection().prepareStatement(query)) {
                stmt.setInt(1, dietPlanId);
                stmt.executeUpdate();
                System.out.println("Diet plan deleted successfully.");
            } catch (SQLException e) {
                System.err.println("Error deleting diet plan: " + e.getMessage());
            }
        }
    }

