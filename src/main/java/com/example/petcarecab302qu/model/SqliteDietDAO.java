package com.example.petcarecab302qu.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
                dietPlan.setId(generatedId);  // Set the generated ID back into the DietPlan object
                System.out.println("Diet Plan saved with ID: " + generatedId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public List<DietPlan> getAllDietPlans() {
        List<DietPlan> dietPlans = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM diet_plans");
            while (resultSet.next()) {
                DietPlan dietPlan = new DietPlan(
                        resultSet.getString("name"),
                        resultSet.getInt("duration"),
                        resultSet.getString("breakfast"),
                        resultSet.getString("lunch"),
                        resultSet.getString("dinner")
                );
                dietPlan.setId(resultSet.getInt("id"));
                dietPlans.add(dietPlan);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dietPlans;
    }
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
