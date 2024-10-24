package com.example.petcarecab302qu.model.entities;

/**
 * A model class representing a contact with a first name, last name, email, phone number, and password.
 * It includes methods for getting and setting these properties.
 */
public class Contact {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String password;

    /**
     * Constructs a new Contact with the specified first name, last name, email, phone number, and password.
     *
     * @param firstName The first name of the contact.
     * @param lastName The last name of the contact.
     * @param email The email address of the contact.
     * @param phone The phone number of the contact.
     * @param password The password for the contact's account.
     */
    public Contact(String firstName, String lastName, String email, String phone, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }
}