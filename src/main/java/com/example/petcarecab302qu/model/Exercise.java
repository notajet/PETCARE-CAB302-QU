package com.example.petcarecab302qu.model;

public class Exercise {
    private int id;
    private String petname;
    private String type;
    private double duration;
    private String notes;

    public Exercise(int id, String petname, String type, double duration, String notes) {
        this.id = id;
        this.petname = petname;
        this.type = type;
        this.duration = duration;
        this.notes = notes;
    }

    public int getEId() { return id; }

    public String getEName() { return petname; }

    public String gettype() { return type; }

    public double getduration() { return duration; }

    public String getnotes() { return notes; }
}
