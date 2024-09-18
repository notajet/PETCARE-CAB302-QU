package com.example.petcarecab302qu.model;

import com.example.petcarecab302qu.util.PasswordUtil;
import java.util.ArrayList;
import java.util.List;

public class MockContactDAO implements IContactDAO {
    /**
     * A static list of contacts to be used as a mock database.
     */
    public final ArrayList<Contact> contacts = new ArrayList<>();
    private int autoIncrementedId = 0;

    @Override
    public void addContact(Contact contact) {
        contact.setPassword(PasswordUtil.hashPassword(contact.getPassword()));
        contact.setId(autoIncrementedId);
        autoIncrementedId++;
        contacts.add(contact);
    }

    @Override
    public Contact getContact(int id) {
        for (Contact contact : contacts) {
            if (contact.getId() == id) {
                return contact;
            }
        }
        return null;
    }

    @Override
    public List<Contact> getAllContacts() {
        return new ArrayList<>(contacts);
    }

    @Override
    public boolean authenticateUser(String email, String password) {
        String hashedPassword = PasswordUtil.hashPassword(password);
        for (Contact contact : contacts) {
            if (contact.getEmail().equals(email) && contact.getPassword().equals(hashedPassword)) {
                return true;
            }
        }
        return false;
    }

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
