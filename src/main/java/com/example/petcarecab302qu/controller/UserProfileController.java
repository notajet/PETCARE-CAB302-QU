package com.example.petcarecab302qu.controller;

import com.example.petcarecab302qu.util.SceneLoader;
import com.example.petcarecab302qu.model.Contact;
import com.example.petcarecab302qu.model.SqliteContactDAO;
import com.example.petcarecab302qu.model.IContactDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import java.io.IOException;
import java.util.List;

public class UserProfileController {

    // Use IContactDAO directly
    private IContactDAO contactDAO;

    public UserProfileController() {

        this.contactDAO = new SqliteContactDAO();
    }

    @FXML
    private void onAdd() {
        final String DEFAULT_FIRST_NAME = "New";
        final String DEFAULT_LAST_NAME = "Contact";
        final String DEFAULT_EMAIL = "";
        final String DEFAULT_PHONE = "";
        final String DEFAULT_PASSWORD = "";

        Contact newContact = new Contact(DEFAULT_FIRST_NAME, DEFAULT_LAST_NAME, DEFAULT_EMAIL, DEFAULT_PHONE, DEFAULT_PASSWORD);
        contactDAO.addContact(newContact);
    }
}
