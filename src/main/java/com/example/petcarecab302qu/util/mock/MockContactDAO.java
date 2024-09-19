package com.example.petcarecab302qu.util.mock;

import com.example.petcarecab302qu.model.Contact;
import com.example.petcarecab302qu.model.IContactDAO;
import com.example.petcarecab302qu.util.PasswordUtil;
import java.util.ArrayList;
import java.util.List;

/**
 * A mock implementation of the IContactDAO interface for testing purposes.
 * Uses an in-memory list of contacts to simulate database operations.
 */
public class MockContactDAO implements IContactDAO {

    public final ArrayList<Contact> contacts = new ArrayList<>();

    private int autoIncrementedId = 0;

    /**
     * Adds a new contact to the mock database.
     * Hashes the contact's password and assigns a unique ID.
     *
     * @param contact The contact to be added.
     */
    @Override
    public void addContact(Contact contact) {
        contact.setPassword(PasswordUtil.hashPassword(contact.getPassword()));
        contact.setId(autoIncrementedId);
        autoIncrementedId++;
        contacts.add(contact);
    }

    @Override
    public List<Contact> getAllContacts() {
        return new ArrayList<>(contacts);
    }

    /**
     * Checks if a contact with the given email already exists in the mock database.
     *
     * @param email The email to check for.
     * @return true if a contact with the given email exists, false otherwise.
     */
    @Override
    public boolean emailExists(String email) {
        for (Contact contact : contacts) {
            if (contact.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }
}
