package com.example.petcarecab302qu.model.entities;

public class Schedule {
    private String date;
    private String eventType;
    private String time;
    private Boolean complete;

    public Schedule(String date, String eventType, String time, Boolean complete){
        this.date = date;
        this.eventType = eventType;
        this.time = time;
        this.complete = complete;
    }

    public String getDate() {return date; }

    public String getEventType() {return eventType; }

    public String getTime() { return time; }

    public Boolean getComplete() {return complete; }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }
}