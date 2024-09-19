package com.example.petcarecab302qu.model;

import com.example.petcarecab302qu.util.PasswordUtil;

public class Signup {
    private IContactDAO contactDAO;

    public Signup(IContactDAO contactDAO) {
        this.contactDAO = contactDAO;
    }

    public String signup(String firstName, String lastName, String email, String phone, String password) {
        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || phone.isEmpty() || password.isEmpty()) {
            return "All fields must be filled out.";
        }

        if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            return "Please enter a valid email address.";
        }

        if (password.length() < 8 || !password.matches(".*\\d.*") || !password.matches(".*[a-zA-Z].*")) {
            return "Password must be at least 8 characters long and contain both letters and numbers.";
        }

        if (contactDAO.emailExists(email)) {
            return "Email already exists.";
        }

        String hashedPassword;
        try {
            hashedPassword = PasswordUtil.hashPassword(password);
        } catch (RuntimeException e) {
            return "Error occurred while hashing the password.";
        }

        Contact newContact = new Contact(firstName, lastName, email, phone, hashedPassword);
        contactDAO.addContact(newContact);

        return "Signup successful!";
    }
}
