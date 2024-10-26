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

    /**
     * Constructs a new MockScheduleDAO instance with an empty list of schedules.
     * This mock DAO is intended to manage schedules for testing scenarios.
     */
    public MockScheduleDAO() {
        this.schedules = new ArrayList<>();
    }

    /**
     * Adds a new schedule entry with the specified date, event type, and time.
     *
     * @param date      the date of the event
     * @param eventType the type of event to be added
     * @param time      the time of the event
     */
    @Override
    public void addSchedule(LocalDate date, String eventType, String time) {
        schedules.add(new Schedule(date.toString(), eventType.trim(), time.trim(), false));
    }

    /**
     * Retrieves a list of schedules for the specified date.
     *
     * @param date the date for which schedules are retrieved
     * @return a list of strings representing the schedules for the specified date
     */
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

    /**
     * Updates the completion status of a specified task on a specific date.
     *
     * @param date     the date of the task
     * @param task     the description of the task (event type and time)
     * @param complete true if the task is completed, false otherwise
     */
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

    /**
     * Checks the completion status of a specified task on a specific date.
     *
     * @param date the date of the task
     * @param task the description of the task (event type and time)
     * @return true if the task is completed, false if not or if the task is not found
     */
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
