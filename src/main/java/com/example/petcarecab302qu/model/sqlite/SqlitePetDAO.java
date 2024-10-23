package com.example.petcarecab302qu.model.sqlite;

import com.example.petcarecab302qu.model.SqliteConnection;
import com.example.petcarecab302qu.model.entities.Pet;
import com.example.petcarecab302qu.model.interfaces.IPetDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * A data access object (DAO) class for managing pet records in an SQLite database.
 * Provides methods to create the pets table, add, retrieve, update, and delete pet records.
 */
public class SqlitePetDAO implements IPetDAO {

    private Connection connection;

    public SqlitePetDAO() {
        connection = SqliteConnection.getInstance();
        createPetsTable();
    }

    /**
     * Creates the pets table in the database if it does not already exist.
     * The table contains columns for the pet's ID, name, age, gender, breed, weight, height, and image URL.
     */
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

    /**
     * Adds a new pet to the pets table in the database.
     *
     * @param pet The Pet object containing the details of the pet to be added.
     */
    @Override
    public void addPet(Pet pet) {
        String query = "INSERT INTO pets (name, age, gender, breed, weight, height, imageUrl) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {

            if (connection.isClosed()) {
                System.out.println("Database connection is closed. Cannot add pet.");
                return;
            }

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

    /**
     * Retrieves all pets from the pets table in the database.
     *
     * @return A list of Pet objects representing all pets stored in the database.
     */
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

    /**
     * Updates the details of an existing pet in the pets table in the database.
     *
     * @param pet The Pet object containing the updated details of the pet.
     */
    @Override
    public void updatePet(Pet pet) {
        String query = "UPDATE pets SET name = ?, age = ?, gender = ?, breed = ?, weight = ?, height = ?, imageUrl = ? WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {

            pstmt.setString(1, pet.getName());
            pstmt.setInt(2, pet.getAge());
            pstmt.setString(3, pet.getGender());
            pstmt.setString(4, pet.getBreed());
            pstmt.setDouble(5, pet.getWeight());
            pstmt.setDouble(6, pet.getHeight());
            pstmt.setString(7, pet.getImageUrl());
            pstmt.setInt(8, pet.getId());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error updating pet in database: " + e.getMessage());
        }
    }

    @Override
    public Pet getPet(int petId) {
        return null;
    }

    /**
     * Deletes a pet from the pets table in the database based on its ID.
     *
     * @param petId The ID of the pet to delete.
     */
    @Override
    public void deletePet(int petId) {
        String query = "DELETE FROM pets WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {

            pstmt.setInt(1, petId);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error deleting pet from database: " + e.getMessage());
        }
    }
}
