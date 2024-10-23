package com.example.petcarecab302qu.model;

/**
 * A model class representing a pet in the PETHUB application.
 * Contains details about the pet including its ID, name, age, gender, breed, weight, height, and an image URL.
 */
public class Pet {
    private int id;
    private String name;
    private int age;
    private String gender;
    private String breed;
    private double weight;
    private double height;
    private String imageUrl;

    public Pet(int id, String name, int age, String gender, String breed, double weight, double height, String imageUrl) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.breed = breed;
        this.weight = weight;
        this.height = height;
        this.imageUrl = imageUrl;
    }

    public int getId() { return id; }

    public String getName() { return name; }

    public int getAge() { return age; }

    public String getGender() { return gender; }

    public String getBreed() { return breed; }

    public double getWeight() { return weight; }

    public double getHeight() { return height; }

    public String getImageUrl() { return imageUrl; }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
