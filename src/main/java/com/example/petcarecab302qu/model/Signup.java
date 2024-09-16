package com.example.petcarecab302qu.model;

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

        Contact newContact = new Contact(firstName, lastName, email, phone, password);
        contactDAO.addContact(newContact);

        return "Signup successful!";
    }
}
