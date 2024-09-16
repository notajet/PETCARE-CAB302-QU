package com.example.petcarecab302qu.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
            // Assuming `IContactDAO` has a method `authenticateUser`
            if (contactDAO.authenticateUser(email, password)) {
                return "Authentication Successful";
            } else {
                return "Authentication Unsuccessful";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Error occurred during authentication.";
        }
    }
}
