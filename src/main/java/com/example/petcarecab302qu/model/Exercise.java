package com.example.petcarecab302qu.model;

/**
 * A model class representing an exercise session for a pet.
 * Contains details about the type of exercise, its duration, and any additional notes.
 */
public class Exercise {
    private int exerciseId;
    private String petName;
    private String type;
    private Integer duration;
    private String notes;
    private String date;

    public Exercise(String petName, String date, String type, Integer duration, String notes) {
        this.petName = petName;
        this.date = date;
        this.type = type;
        this.duration = duration;
        this.notes = notes;
    }

    public int getExerciseId() { return exerciseId; }

    public void setExerciseId(int exerciseId) {
        this.exerciseId = exerciseId;
    }

    public String getPetName() { return petName; }
    public void setPetName(String petName) { this.petName = petName;}

    public String getDate() {return date; }
    public void setDate(String date) { this.date = date;}

    public String getType() { return type; }
    public void setType(String type) {this.type = type;}

    public Integer getDuration() { return duration; }
    public void setDuration(Integer duration) {this.duration = duration;}

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes;}
    @Override
    public String toString() {
        return "Pet: " + petName + ", Type: " + type + ", Duration: " + duration + " mins, Date: " + date;
    }
}
