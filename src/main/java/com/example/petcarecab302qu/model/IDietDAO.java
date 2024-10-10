package com.example.petcarecab302qu.model;

import java.util.List;

public interface IDietDAO {
    void addDietPlan(DietPlan dietPlan);
    List<DietPlan> getAllDietPlans();
    void updateDietPlan(DietPlan dietPlan);
    void deleteDietPlan(int dietPlanId);
    DietPlan getDietPlan(int id); // for retrieving a single plan
}
