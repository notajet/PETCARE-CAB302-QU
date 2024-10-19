package com.example.petcarecab302qu.util.mock;

import com.example.petcarecab302qu.model.Exercise;
import com.example.petcarecab302qu.model.IExerciseDAO;

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

    public MockExerciseDAO() {
        this.exercises = new ArrayList<>();
    }

    @Override
    public void addExercise(Exercise exercise) {
        exercise.setExerciseId(nextId++);
        exercises.add(exercise);
    }

    @Override
    public Exercise getExercise(int id) {
        for (Exercise exercise : exercises) {
            if (exercise.getExerciseId() == id) {
                return exercise;
            }
        }
        return null;
    }

    public List<Exercise> getAllExercises() {
        return new ArrayList<>(exercises);
    }

}
