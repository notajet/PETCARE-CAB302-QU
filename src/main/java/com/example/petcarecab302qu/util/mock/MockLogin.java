package com.example.petcarecab302qu.util.mock;

import com.example.petcarecab302qu.model.entities.Contact;
import com.example.petcarecab302qu.model.interfaces.IContactDAO;
import com.example.petcarecab302qu.util.PasswordUtil;

/**
 * Class for handling user login functionality in the Pet Care application.
 * Uses the provided IContactDAO implementation to authenticate users based on their email and password.
 */
public class MockLogin {

    private IContactDAO contactDAO;

    public MockLogin(IContactDAO contactDAO) {
        this.contactDAO = contactDAO;
    }

    /**
     * Authenticates a user based on the provided email and password.
     * Checks if the provided email and password match any contact in the database.
     *
     * @param email The email of the user attempting to log in.
     * @param password The password of the user attempting to log in.
     * @return A message indicating the result of the authentication:
     *         - "Authentication Successful" if the email and password are correct.
     *         - "Authentication Unsuccessful" if the credentials are incorrect.
     *         - "Please provide email." if the email is empty.
     *         - "Please provide password." if the password is empty.
     *         - "Error occurred during authentication." if an exception occurs during the process.
     */
    public String login(String email, String password) {

        if (email.isEmpty()) {
            return "Please provide email.";
        }
        if (password.isEmpty()) {
            return "Please provide password.";
        }

        try {

            String hashedInputPassword = PasswordUtil.hashPassword(password);

            for (Contact contact : contactDAO.getAllContacts()) {
                if (contact.getEmail().equals(email) && contact.getPassword().equals(hashedInputPassword)) {
                    return "Authentication Successful";
                }
            }
            return "Authentication Unsuccessful";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error occurred during authentication.";
        }
    }
}