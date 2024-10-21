package com.example.petcarecab302qu.model;

import java.util.List;

/**
 * Interface for the Exercise Data Access Object that handles
 * the CRUD operations for the Exercise class with the database.
 */
public interface IExerciseDAO {
    void addExercise(Exercise exercise);
    Exercise getExercise(int id);
    List<Exercise> getAllExercises();
}
