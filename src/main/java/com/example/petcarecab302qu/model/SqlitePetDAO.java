package com.example.petcarecab302qu.db;

import com.example.petcarecab302qu.model.Pet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SqlitePetDAO {
    private Connection connection;

    public SqlitePetDAO(Connection connection) {
        this.connection = connection;
    }

    public void addPet(Pet pet) {
        String sql = "INSERT INTO pets (name, age, gender, breed, weight, height, imageUrl) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
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
        }
    }
}
