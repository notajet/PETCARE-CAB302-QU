package com.example.petcarecab302qu.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqlitePetDAO implements IPetDAO {

    private Connection connection;

    public SqlitePetDAO() {
        connection = SqliteConnection.getInstance();
        createPetsTable();
    }

    // Method to create the pets table if it doesn't exist
    private void createPetsTable() {
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

    @Override
    public void addPet(Pet pet) {
        String query = "INSERT INTO pets (name, age, gender, breed, weight, height, imageUrl) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {

            // Check if the connection is valid before executing
            if (connection.isClosed()) {
                System.out.println("Database connection is closed. Cannot add pet.");
                return;
            }

            // Set parameters and execute the statement
            pstmt.setString(1, pet.getName());
            pstmt.setInt(2, pet.getAge());
            pstmt.setString(3, pet.getGender());
            pstmt.setString(4, pet.getBreed());
            pstmt.setDouble(5, pet.getWeight());
            pstmt.setDouble(6, pet.getHeight());
            pstmt.setString(7, pet.getImageUrl());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error adding pet to database: " + e.getMessage());
        }
    }

    @Override
    public List<Pet> getAllPets() {
        List<Pet> pets = new ArrayList<>();
        String query = "SELECT * FROM pets";

        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Pet pet = new Pet(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getString("gender"),
                        rs.getString("breed"),
                        rs.getDouble("weight"),
                        rs.getDouble("height"),
                        rs.getString("imageUrl")
                );
                pets.add(pet);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pets;
    }

    @Override
    public void updatePet(Pet pet) {
        String query = "UPDATE pets SET name = ?, age = ?, gender = ?, breed = ?, weight = ?, height = ?, imageUrl = ? WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {

            // Set parameters
            pstmt.setString(1, pet.getName());
            pstmt.setInt(2, pet.getAge());
            pstmt.setString(3, pet.getGender());
            pstmt.setString(4, pet.getBreed());
            pstmt.setDouble(5, pet.getWeight());
            pstmt.setDouble(6, pet.getHeight());
            pstmt.setString(7, pet.getImageUrl());
            pstmt.setInt(8, pet.getId());

            // Execute update
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error updating pet in database: " + e.getMessage());
        }
    }

    @Override
    public void deletePet(int petId) {
        String query = "DELETE FROM pets WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {

            // Set parameter and execute
            pstmt.setInt(1, petId);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error deleting pet from database: " + e.getMessage());
        }
    }
}
