package com.example.petcarecab302qu.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SqliteContactDAO implements IContactDAO {

    @Override
    public void addContact(Contact contact) {
        // Implementation to add a contact to the SQLite database
    }

    @Override
    public void updateContact(Contact contact) {
        // Implementation to update a contact in the SQLite database
    }

    @Override
    public void deleteContact(Contact contact) {
        // Implementation to delete a contact from the SQLite database
    }

    @Override
    public Contact getContact(int id) {
        // Implementation to get a contact by ID from the SQLite database
        return null;
    }

    @Override
    public List<Contact> getAllContacts() {
        // Implementation to get all contacts from the SQLite database
        return null;
    }

    @Override
    public boolean emailExists(String email) {
        // Implementation to check if an email exists in the SQLite database
        return false;
    }

    @Override
    public boolean authenticateUser(String email, String password) throws SQLException {
        String query = "SELECT * FROM contacts WHERE email = ? AND password = ?";
        try (Connection connection = SqliteConnection.getInstance();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, email);
            statement.setString(2, password);
            try (ResultSet resultSet = statement.executeQuery()) {

                return resultSet.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }
}
