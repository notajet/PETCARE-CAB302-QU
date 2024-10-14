package com.example.petcarecab302qu.model;

/**
 * A model class representing an exercise session for a pet.
 * Contains details about the type of exercise, its duration, and any additional notes.
 */
public class Exercise {
    private int exerciseId;
    private String petName;
    private String date;
    private String type;
    private Integer duration;
    private String notes;

    public Exercise(String petName, String date, String type, Integer duration, String notes) {
        //this.id = id;
        this.petName = petName;
        this.exerciseId = exerciseId;
        this.date = date;
        this.type = type;
        this.duration = duration;
        this.notes = notes;
    }



    public int getExerciseId() { return exerciseId; }

    public String getPetName() { return petName; }

    public String getDate() {return date; }

    public String getType() { return type; }

    public int getDuration() { return duration; }

    public String getNotes() { return notes; }


    public void setExerciseId(int exerciseId) {
        this.exerciseId = exerciseId;
    }
    public void setPetName(String petName) { this.petName = petName; }

    public void setDate(String date) {
        this.date = date;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
