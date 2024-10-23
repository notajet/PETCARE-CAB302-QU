package com.example.petcarecab302qu.model.interfaces;

import com.example.petcarecab302qu.model.entities.Contact;

import java.util.List;

/**
 * Interface for the Contact Data Access Object that handles
 * the CRUD operations for the Contact class with the database.
 */
public interface IContactDAO {
    void addContact(Contact contact);
    List<Contact> getAllContacts();
    boolean emailExists(String email);
}
