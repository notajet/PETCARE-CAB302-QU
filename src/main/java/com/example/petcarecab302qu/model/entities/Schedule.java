package com.example.petcarecab302qu.model.entities;

/**
 * Represents a schedule entry for a specific date and event type in the Pet Care application.
 * Contains details about the event's date, type, time, and completion status.
 */
public class Schedule {
    private String date;
    private String eventType;
    private String time;
    private Boolean complete;

    /**
     * Constructs a new Schedule entry with the specified date, event type, time, and completion status.
     *
     * @param date       the date of the scheduled event
     * @param eventType  the type of event scheduled (e.g., exercise, feeding)
     * @param time       the time of the scheduled event
     * @param complete   the completion status of the event
     */
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