package com.example.petcarecab302qu.util.mock;

import com.example.petcarecab302qu.model.Contact;
import com.example.petcarecab302qu.model.IContactDAO;
import com.example.petcarecab302qu.util.PasswordUtil;

public class Login {
    private IContactDAO contactDAO;

    public Login(IContactDAO contactDAO) {
        this.contactDAO = contactDAO;
    }

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