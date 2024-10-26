package com.example.petcarecab302qu.model.entities;

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

    /**
     * Constructs a new Exercise with the specified pet name, date, type, duration, and notes.
     *
     * @param petName   the name of the pet associated with this exercise
     * @param date      the date of the exercise
     * @param type      the type of exercise (e.g., walk, run, play)
     * @param duration  the duration of the exercise in minutes
     * @param notes     additional notes related to the exercise
     */
    public Exercise(String petName, String date, String type, Integer duration, String notes) {
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

    public void setDate(String date) {
        this.date = date;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
