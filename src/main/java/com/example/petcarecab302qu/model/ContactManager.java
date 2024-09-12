package com.example.petcarecab302qu.model;

import java.util.List;
import java.util.stream.Collectors;

public class ContactManager {

    private IContactDAO contactDAO;

    public ContactManager(IContactDAO contactDAO) {
        this.contactDAO = contactDAO;
    }

    public void addContact(Contact contact) {
        contactDAO.addContact(contact);
    }

    public void updateContact(Contact contact) {
        contactDAO.updateContact(contact);
    }

    public void deleteContact(Contact contact) {
        contactDAO.deleteContact(contact);
    }

    public List<Contact> getAllContacts() {
        return contactDAO.getAllContacts();
    }

    public List<Contact> searchContacts(String query) {
        return contactDAO.getAllContacts()
                .stream()
                .filter(contact -> isContactMatched(contact, query))
                .collect(Collectors.toList());
    }

    private boolean isContactMatched(Contact contact, String query) {
        if (query == null || query.isEmpty()) {
            return true;
        }

        query = query.toLowerCase();

        String searchString = contact.getFullName()
                + " " + contact.getEmail()
                + " " + contact.getPhone();

        return searchString.toLowerCase().contains(query);
    }
}
