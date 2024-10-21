import com.example.petcarecab302qu.util.mock.MockExerciseDAO;
import com.example.petcarecab302qu.model.IExerciseDAO;
import com.example.petcarecab302qu.model.Exercise;
import com.example.petcarecab302qu.controller.ExerciseController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class ExerciseTest {

    private ExerciseController exerciseController;
    private IExerciseDAO mockExerciseDAO;

    @BeforeEach
    public void setUp() {
        // Use the mock DAO for testing
        mockExerciseDAO = new MockExerciseDAO();
        exerciseController = new ExerciseController(mockExerciseDAO); // Pass the mock DAO

        Exercise exercise1 = new Exercise("Buddy", "2023-10-14", "walk", 30, "Morning walk");
        Exercise exercise2 = new Exercise("Mittens", "2023-10-14", "run", 20, "Quick run");
        mockExerciseDAO.addExercise(exercise1);
        mockExerciseDAO.addExercise(exercise2);
    }

    @Test
    public void testGetAllExercises() {
        List<Exercise> exercises = mockExerciseDAO.getAllExercises();
        assertEquals(2, exercises.size());

        assertEquals("Buddy", exercises.get(0).getPetName());
        assertEquals("Mittens", exercises.get(1).getPetName());
    }

    @Test
    public void testAddExercise() {
        Exercise newExercise = new Exercise("Charlie", "2023-10-15", "play", 15, "Fun time");
        mockExerciseDAO.addExercise(newExercise);

        List<Exercise> exercises = mockExerciseDAO.getAllExercises();
        assertEquals(3, exercises.size());

        Exercise addedExercise = exercises.get(2);
        assertEquals("Charlie", addedExercise.getPetName());
    }
}
