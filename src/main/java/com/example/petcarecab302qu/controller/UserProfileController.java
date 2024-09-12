package com.example.petcarecab302qu.controller;

import com.example.petcarecab302qu.util.SceneLoader;
import com.example.petcarecab302qu.HelloApplication;
import com.example.petcarecab302qu.model.Contact;
import com.example.petcarecab302qu.model.ContactManager;
import com.example.petcarecab302qu.model.SqliteContactDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.List;

public class UserProfileController {

    @FXML
    private ListView<Contact> contactsListView;

    @FXML
    private TextField searchTextField;

    private ContactManager contactManager;

    @FXML
    private TextField firstNameTextField;
    @FXML
    private TextField lastNameTextField;
    @FXML
    private TextField emailTextField;
    @FXML
    private TextField phoneTextField;
    @FXML
    private TextField passwordTextField;

    @FXML
    private VBox contactContainer;

    public UserProfileController() {
        contactManager = new ContactManager(new SqliteContactDAO());
    }

    private void selectContact(Contact contact) {
        contactsListView.getSelectionModel().select(contact);
        if (contact != null) {
            firstNameTextField.setText(contact.getFirstName());
            lastNameTextField.setText(contact.getLastName());
            emailTextField.setText(contact.getEmail());
            phoneTextField.setText(contact.getPhone());

            if (passwordTextField != null) {
                passwordTextField.setText(contact.getPassword());
            }
        }
    }

    private ListCell<Contact> renderCell(ListView<Contact> contactListView) {
        return new ListCell<>() {
            @Override
            protected void updateItem(Contact contact, boolean empty) {
                super.updateItem(contact, empty);
                if (empty || contact == null || contact.getFullName() == null) {
                    setText(null);
                } else {
                    setText(contact.getFullName());
                }
            }
        };
    }

    private void syncContacts() {
        contactsListView.getItems().clear();
        String query = searchTextField.getText();
        List<Contact> contacts = contactManager.searchContacts(query);
        boolean hasContact = !contacts.isEmpty();
        if (hasContact) {
            contactsListView.getItems().addAll(contacts);
        }
        contactContainer.setVisible(hasContact);
    }

    @FXML
    public void initialize() {
        contactsListView.setCellFactory(this::renderCell);
        syncContacts();

        contactsListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                selectContact(newValue);
            }
        });

        searchTextField.textProperty().addListener((observable, oldValue, newValue) -> syncContacts());

        contactsListView.getSelectionModel().selectFirst();
        Contact firstContact = contactsListView.getSelectionModel().getSelectedItem();
        if (firstContact != null) {
            selectContact(firstContact);
        }
    }

    @FXML
    private void onEditConfirm() {
        Contact selectedContact = contactsListView.getSelectionModel().getSelectedItem();
        if (selectedContact != null) {
            selectedContact.setFirstName(firstNameTextField.getText());
            selectedContact.setLastName(lastNameTextField.getText());
            selectedContact.setEmail(emailTextField.getText());
            selectedContact.setPhone(phoneTextField.getText());

            if (passwordTextField != null) {
                selectedContact.setPassword(passwordTextField.getText());
            }

            contactManager.updateContact(selectedContact);
            syncContacts();
        }
    }

    @FXML
    private void onDelete() {
        Contact selectedContact = contactsListView.getSelectionModel().getSelectedItem();
        if (selectedContact != null) {
            contactManager.deleteContact(selectedContact);
            syncContacts();
        }
    }

    @FXML
    private void onAdd() {
        final String DEFAULT_FIRST_NAME = "New";
        final String DEFAULT_LAST_NAME = "Contact";
        final String DEFAULT_EMAIL = "";
        final String DEFAULT_PHONE = "";
        final String DEFAULT_PASSWORD = "";

        Contact newContact = new Contact(DEFAULT_FIRST_NAME, DEFAULT_LAST_NAME, DEFAULT_EMAIL, DEFAULT_PHONE, DEFAULT_PASSWORD);
        contactManager.addContact(newContact);
        syncContacts();
        selectContact(newContact);
        firstNameTextField.requestFocus();
    }

    @FXML
    private void onCancel() {
        Contact selectedContact = contactsListView.getSelectionModel().getSelectedItem();
        if (selectedContact != null) {
            selectContact(selectedContact);
        }
    }

    @FXML
    public void handleBackButton(ActionEvent event) throws IOException {
        SceneLoader.handleBackButton(event);
    }
}
