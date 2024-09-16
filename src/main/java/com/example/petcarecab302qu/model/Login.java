package com.example.petcarecab302qu.model;

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
