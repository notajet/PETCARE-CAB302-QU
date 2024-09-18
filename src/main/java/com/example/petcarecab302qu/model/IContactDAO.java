package com.example.petcarecab302qu.model;

import java.util.List;

/**
 * Interface for the Contact Data Access Object that handles
 * the CRUD operations for the Contact class with the database.
 */
public interface IContactDAO {

    /**
     * Adds a new contact to the database.
     * @param contact The contact to add.
     */
    void addContact(Contact contact);

    /**
     * Retrieves a contact from the database by its ID.
     * @param id The ID of the contact to retrieve.
     * @return The contact with the given ID, or null if not found.
     */
    Contact getContact(int id);

    /**
     * Retrieves all contacts from the database.
     * @return A list of all contacts in the database.
     */
    List<Contact> getAllContacts();

    /**
     * Checks if a contact with the given email exists in the database.
     * @param email The email to check.
     * @return True if a contact with the given email exists, false otherwise.
     */
    boolean emailExists(String email);

    /**
     * Authenticates a user with the given email and password.
     * @param email The email of the user to authenticate.
     * @param password The password of the user to authenticate.
     * @return True if a user with the given email and password exists, false otherwise.
     * @throws Exception If an error occurs during authentication.
     */
    boolean authenticateUser(String email, String password) throws Exception;
}
