package com.example.petcarecab302qu.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the IContactDAO interface using SQLite as the database.
 * Provides methods to interact with the contacts database, including adding,
 * retrieving, and checking for the existence of contacts.
 */
public class SqliteContactDAO implements IContactDAO {

    private Connection connection;

    public SqliteContactDAO() {
        connection = SqliteConnection.getInstance();
        createTable();
    }

    /**
     * Creates the contacts table in the database if it does not already exist.
     * The table contains fields for id, first name, last name, phone, email, and password.
     */
    private void createTable() {
        try {
            Statement statement = connection.createStatement();
            // Ensure space between columns
            String query = "CREATE TABLE IF NOT EXISTS contacts ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "firstName VARCHAR NOT NULL,"
                    + "lastName VARCHAR NOT NULL,"
                    + "phone VARCHAR NOT NULL,"
                    + "email VARCHAR NOT NULL,"
                    + "password VARCHAR NOT NULL"
                    + ")";
            statement.execute(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Adds a new contact to the database.
     * Sets the generated ID for the contact after insertion.
     *
     * @param contact The contact to be added.
     */
    @Override
    public void addContact(Contact contact) {
        try {
            // Correct number of fields in the prepared statement and SQL syntax
            PreparedStatement statement = connection.prepareStatement("INSERT INTO contacts (firstName, lastName, phone, email, password) VALUES (?, ?, ?, ?, ?)");
            statement.setString(1, contact.getFirstName());
            statement.setString(2, contact.getLastName());
            statement.setString(3, contact.getPhone());
            statement.setString(4, contact.getEmail());
            statement.setString(5, contact.getPassword());  // Add password to the query
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                contact.setId(generatedKeys.getInt(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves all contacts from the database.
     *
     * @return A list of all contacts stored in the database.
     */
    @Override
    public List<Contact> getAllContacts() {
        List<Contact> contacts = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM contacts";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                String phone = resultSet.getString("phone");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                Contact contact = new Contact(firstName, lastName, phone, email, password);
                contact.setId(id);
                contacts.add(contact);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return contacts;
    }

    /**
     * Checks if a contact with the specified email exists in the database.
     *
     * @param email The email to check for.
     * @return true if a contact with the given email exists, false otherwise.
     */
    @Override
    public boolean emailExists(String email) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT COUNT(*) FROM contacts WHERE email = ?");
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count > 0;  // If count > 0, email exists
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}