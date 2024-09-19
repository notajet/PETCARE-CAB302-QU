package com.example.petcarecab302qu.util.mock;

import com.example.petcarecab302qu.model.Contact;
import com.example.petcarecab302qu.model.IContactDAO;
import com.example.petcarecab302qu.util.PasswordUtil;

/**
 * Class for handling user signup functionality in the Pet Care application.
 * Validates user input, hashes the password, and stores the new contact using the provided IContactDAO implementation.
 */
public class Signup {
    private IContactDAO contactDAO;

    public Signup(IContactDAO contactDAO) {
        this.contactDAO = contactDAO;
    }

    /**
     * Registers a new user by validating the provided information and adding the contact to the database.
     *
     * @param firstName The first name of the user.
     * @param lastName The last name of the user.
     * @param email The email address of the user.
     * @param phone The phone number of the user.
     * @param password The password of the user.
     * @return A message indicating the result of the signup process:
     *         - "All fields must be filled out." if any field is empty.
     *         - "Please enter a valid email address." if the email is not in a valid format.
     *         - "Password must be at least 8 characters long and contain both letters and numbers." if the password is invalid.
     *         - "Email already exists." if the email is already in use.
     *         - "Error occurred while hashing the password." if an error occurs during password hashing.
     *         - "Signup successful!" if the signup is completed successfully.
     */
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
