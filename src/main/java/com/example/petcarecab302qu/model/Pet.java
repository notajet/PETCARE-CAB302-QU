package com.example.petcarecab302qu.model;

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

    // Getters and setters for all fields
    public int getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getGender() { return gender; }
    public String getBreed() { return breed; }
    public double getWeight() { return weight; }
    public double getHeight() { return height; }
    public String getImageUrl() { return imageUrl; }
    public void setId(int id) {}
    public void setName(String buddyUpdated) {}
}
