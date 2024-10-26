package com.example.petcarecab302qu.util.mock;

import com.example.petcarecab302qu.model.entities.Exercise;
import com.example.petcarecab302qu.model.interfaces.IExerciseDAO;

import java.util.ArrayList;
import java.util.List;

/**
 * A mock implementation of the IExerciseDAO interface for testing purposes.
 * This class simulates a data access object (DAO) for exercises by storing exercises in memory
 * rather than a real database.
 */
public class MockExerciseDAO implements IExerciseDAO {

    private List<Exercise> exercises;
    private int nextId = 1;

    /**
     * Constructs a new MockExerciseDAO instance with an empty list of exercises.
     * This mock DAO is intended for managing exercises in a test environment.
     */
    public MockExerciseDAO() {
        this.exercises = new ArrayList<>();
    }

    /**
     * Adds a new exercise to the list and assigns it a unique ID.
     *
     * @param exercise the exercise to be added
     */
    @Override
    public void addExercise(Exercise exercise) {
        exercise.setExerciseId(nextId++);
        exercises.add(exercise);
    }

    /**
     * Retrieves an exercise by its unique ID.
     *
     * @param id the ID of the exercise to retrieve
     * @return the exercise with the specified ID, or null if not found
     */
    @Override
    public Exercise getExercise(int id) {
        for (Exercise exercise : exercises) {
            if (exercise.getExerciseId() == id) {
                return exercise;
            }
        }
        return null;
    }

    /**
     * Retrieves all exercises in the list.
     *
     * @return a list of all exercises
     */
    public List<Exercise> getAllExercises() {
        return new ArrayList<>(exercises);
    }
}
