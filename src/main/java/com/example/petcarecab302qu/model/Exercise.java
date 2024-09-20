package com.example.petcarecab302qu.model;

/**
 * A model class representing an exercise session for a pet.
 * Contains details about the type of exercise, its duration, and any additional notes.
 */
public class Exercise {
    private int id;
    private String petname;
    private String type;
    private double duration;
    private String notes;

    public Exercise(String type, double duration, String notes) {
        //this.id = id;
        //this.petname = petname;
        this.type = type;
        this.duration = duration;
        this.notes = notes;
    }

    public int getEId() { return id; }

    public void setEId(int id) {
        this.id = id;
    }

    public String getEName() { return petname; }

    public String gettype() { return type; }

    public double getduration() { return duration; }

    public String getnotes() { return notes; }
}
