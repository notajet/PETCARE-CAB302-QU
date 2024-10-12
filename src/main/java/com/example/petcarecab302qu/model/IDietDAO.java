package com.example.petcarecab302qu.model;

import java.util.List;

/**
 * Interface for the Diet Data Access Object that handles
 * the CRUD operations for the DietPlan class with the database.
 */
public interface IDietDAO {
    void addDietPlan(DietPlan dietPlan);
    List<DietPlan> getAllDietPlans();
    void updateDietPlan(DietPlan dietPlan);
    void deleteDietPlan(int dietPlanId);
    DietPlan getDietPlan(int id); // for retrieving a single plan
}
