package com.example.petcarecab302qu.model;

/**
 * A model class representing an exercise session for a pet.
 * Contains details about the type of exercise, its duration, and any additional notes.
 */
public class Exercise {
    private int id;
    private String petname;
    private String type;
    private Integer duration;
    private String notes;
    private String date;

    public Exercise(String date, String type, Integer duration, String notes) {
        //this.id = id;
        //this.petname = petname;
        this.date = date;
        this.type = type;
        this.duration = duration;
        this.notes = notes;
    }

    public int getEId() { return id; }

    public void setEId(int id) {
        this.id = id;
    }

    public String getEName() { return petname; }

    public String getdate() {return date; }

    public String gettype() { return type; }

    public Integer getduration() { return duration; }

    public String getnotes() { return notes; }
}
