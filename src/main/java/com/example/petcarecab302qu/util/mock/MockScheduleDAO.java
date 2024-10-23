package com.example.petcarecab302qu.util.mock;

import com.example.petcarecab302qu.model.interfaces.IScheduleDAO;
import com.example.petcarecab302qu.model.entities.Schedule;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * A mock implementation of the IScheduleDAO interface for testing purposes.
 * This class simulates a data access object (DAO) for schedules by storing schedules in memory
 * rather than a real database.
 */
public class MockScheduleDAO implements IScheduleDAO {

    private List<Schedule> schedules;

    public MockScheduleDAO() {
        this.schedules = new ArrayList<>();
    }

    @Override
    public void addSchedule(LocalDate date, String eventType, String time) {
        schedules.add(new Schedule(date.toString(), eventType.trim(), time.trim(), false));
    }

    @Override
    public List<String> getSchedules(LocalDate date) {
        List<String> scheduleList = new ArrayList<>();
        for (Schedule schedule : schedules) {
            if (schedule.getDate().equals(date.toString())) {
                scheduleList.add(schedule.getEventType() + " at " + schedule.getTime());
            }
        }
        return scheduleList;
    }

    @Override
    public void updateTaskCompletionStatus(LocalDate date, String task, boolean complete) {
        for (Schedule schedule : schedules) {
            String taskDesc = schedule.getEventType() + " at " + schedule.getTime();
            if (schedule.getDate().equals(date.toString()) && taskDesc.equals(task)) {
                System.out.println("Updating task: " + taskDesc + " to " + (complete ? "completed" : "incomplete"));
                schedule.setComplete(complete);
                System.out.println("New completion status for '" + taskDesc + "': " + schedule.getComplete());
            } else {
                System.out.println("Task not matching: " + taskDesc + " vs " + task);
            }
        }
    }

    @Override
    public boolean getCompletionStatusForTask(LocalDate date, String task) {
        for (Schedule schedule : schedules) {
            String taskDesc = schedule.getEventType() + " at " + schedule.getTime();
            System.out.println("Checking task: " + taskDesc + " with " + task);
            if (schedule.getDate().equals(date.toString()) && taskDesc.equals(task)) {
                return schedule.getComplete();
            }
        }
        return false;
    }
}
