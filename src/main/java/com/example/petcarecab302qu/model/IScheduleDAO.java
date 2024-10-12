package com.example.petcarecab302qu.model;

import java.time.LocalDate;
import java.util.List;

public interface IScheduleDAO {
    void addSchedule(LocalDate date, String eventType, String time);
    List<String> getSchedules(LocalDate date);
    void updateTaskCompletionStatus(LocalDate date, String task, boolean complete);
    boolean getCompletionStatusForTask(LocalDate date, String task);

}
