package com.example.petcarecab302qu.util.mock;

import com.example.petcarecab302qu.model.entities.DietPlan;
import com.example.petcarecab302qu.model.interfaces.IDietDAO;

import java.util.ArrayList;
import java.util.List;

/**
 * A mock implementation of the IDietDAO interface for testing purposes.
 * This class simulates a data access object (DAO) for diet plans by storing diets in memory
 * rather than a real database.
 */
public class MockDietDAO implements IDietDAO {

    private List<DietPlan> dietPlans;
    private int nextId = 1;

    /**
     * Constructs a new MockDietDAO instance with an empty list of diet plans.
     * This mock DAO is designed to manage diet plans for testing purposes.
     */
    public MockDietDAO() {
        this.dietPlans = new ArrayList<>();
    }

    /**
     * Adds a new diet plan to the in-memory list, assigning it a unique ID.
     *
     * @param dietPlan The DietPlan object to be added.
     */
    @Override
    public void addDietPlan(DietPlan dietPlan) {
        dietPlan.setId(nextId++);
        dietPlans.add(dietPlan);
    }

    @Override
    public List<DietPlan> getAllDietPlans() {
        return new ArrayList<>(dietPlans);
    }

    /**
     * Updates the information of an existing diet plan in the in-memory list.
     *
     * @param dietPlan The diet plan object with updated information.
     */
    @Override
    public void updateDietPlan(DietPlan dietPlan) {
        for (int i = 0; i < dietPlans.size(); i++) {
            if (dietPlans.get(i).getId() == dietPlan.getId()) {
                dietPlans.set(i, dietPlan);
                return;
            }
        }
    }

    /**
     * Retrieves a diet plan by its unique ID.
     *
     * @param id the ID of the diet plan to retrieve
     * @return the diet plan with the specified ID, or null if not found
     */
    @Override
    public DietPlan getDietPlan(int id) {
        for (DietPlan dietPlan : dietPlans) {
            if (dietPlan.getId() == id) {
                return dietPlan;
            }
        }
        return null;
    }

    /**
     * Deletes a diet plan by its unique ID.
     *
     * @param dietPlanId the ID of the diet plan to delete
     */
    @Override
    public void deleteDietPlan(int dietPlanId) {
        dietPlans.removeIf(dietPlan -> dietPlan.getId() == dietPlanId);
    }
}
