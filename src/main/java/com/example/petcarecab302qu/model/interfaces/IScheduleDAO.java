package com.example.petcarecab302qu.model.interfaces;

import java.time.LocalDate;
import java.util.List;

/**
 * Interface for the Schedules Data Access Object that handles
 * the CRUD operations for the Schedule class with the database.
 */

public interface IScheduleDAO {
    void addSchedule(LocalDate date, String eventType, String time);
    List<String> getSchedules(LocalDate date);
    void updateTaskCompletionStatus(LocalDate date, String task, boolean complete);
    boolean getCompletionStatusForTask(LocalDate date, String task);
}
