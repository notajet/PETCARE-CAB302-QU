package com.example.petcarecab302qu.model;

/**
 * A model class representing a diet plan for a pet in the Pet Care application.
 * Contains details about the diet plan such as its name, duration, and the meals for each day.
 */
public class DietPlan {
    private int id;
    private String name;
    private int duration;
    private String breakfast;
    private String lunch;
    private String dinner;

    public DietPlan(String name, int duration, String breakfast, String lunch, String dinner) {
        this.name = name;
        this.duration = duration;
        this.breakfast = breakfast;
        this.lunch = lunch;
        this.dinner = dinner;
    }

    public int getId() { return id; }
    
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public int getDuration() { return duration; }

    public void setDuration(int duration) { this.duration = duration; }

    public String getBreakfast() { return breakfast; }

    public void setBreakfast(String breakfast) { this.breakfast = breakfast; }

    public String getLunch() { return lunch; }

    public void setLunch(String lunch) { this.lunch = lunch; }

    public String getDinner() { return dinner; }

    public void setDinner(String dinner) { this.dinner = dinner; }
}
