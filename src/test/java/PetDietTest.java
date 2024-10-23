import com.example.petcarecab302qu.controller.DietController;
import com.example.petcarecab302qu.util.mock.MockDietDAO;
import com.example.petcarecab302qu.model.entities.DietPlan;
import com.example.petcarecab302qu.model.interfaces.IDietDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test class for testing the functionality of the DietController and MockDietDAO.
 * Verifies operations like adding, retrieving, updating, and deleting diet plans in the Pet Care application.
 */
public class PetDietTest {
    private DietController dietController;
    private IDietDAO mockDietDAO;

    @BeforeEach
    public void setUp() {
        mockDietDAO = new MockDietDAO();
        dietController = new DietController(mockDietDAO);

        // Creating two initial diet plans
        DietPlan diet1 = new DietPlan("Weight Loss", 30, "Low Carb Diet", "Salad", "Grilled Chicken");
        DietPlan diet2 = new DietPlan("High Protein", 45, "Protein Smoothie", "Chicken Salad", "Steak");

        // Adding the diet plans to the mock DAO
        mockDietDAO.addDietPlan(diet1);
        mockDietDAO.addDietPlan(diet2);
    }

    @Test
    public void testGetAllDietPlans() {
        // Retrieving all diet plans
        List<DietPlan> dietPlans = mockDietDAO.getAllDietPlans();

        // Asserting the correct size and data
        assertEquals(2, dietPlans.size());
        assertEquals("Weight Loss", dietPlans.get(0).getName());
        assertEquals("High Protein", dietPlans.get(1).getName());
    }

    @Test
    public void testAddDietPlan() {
        // Creating a new diet plan and adding it to the mock DAO
        DietPlan newDiet = new DietPlan("Keto", 60, "Low Carb Smoothie", "Avocado Salad", "Grilled Salmon");
        mockDietDAO.addDietPlan(newDiet);

        // Verifying that the new diet plan was added
        List<DietPlan> dietPlans = mockDietDAO.getAllDietPlans();
        assertEquals(3, dietPlans.size());
        DietPlan addedDietPlan = dietPlans.get(2);
        assertEquals("Keto", addedDietPlan.getName());
    }

    @Test
    public void testUpdateDietPlan() {
        // Retrieving the first diet plan for updating
        DietPlan dietToUpdate = mockDietDAO.getAllDietPlans().get(0);
        dietToUpdate.setName("Updated Diet Plan");

        // Updating the diet plan in the mock DAO
        mockDietDAO.updateDietPlan(dietToUpdate);

        // Retrieving the updated diet plan and verifying the update
        DietPlan updatedDietPlan = mockDietDAO.getDietPlan(dietToUpdate.getId());
        assertNotNull(updatedDietPlan);
        assertEquals("Updated Diet Plan", updatedDietPlan.getName());
    }

    @Test
    public void testDeleteDietPlan() {
        // Retrieving the first diet plan for deletion
        DietPlan dietToDelete = mockDietDAO.getAllDietPlans().get(0);

        // Deleting the diet plan
        mockDietDAO.deleteDietPlan(dietToDelete.getId());

        // Verifying the diet plan was deleted
        List<DietPlan> dietPlans = mockDietDAO.getAllDietPlans();
        assertEquals(1, dietPlans.size());
        assertNull(mockDietDAO.getDietPlan(dietToDelete.getId()));
    }
}
