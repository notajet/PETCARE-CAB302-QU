import com.example.petcarecab302qu.util.mock.MockScheduleDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ScheduleTest {

    private MockScheduleDAO mockScheduleDAO;

    @BeforeEach
    public void setUp() {
        // Initialize the mock DAO for testing
        mockScheduleDAO = new MockScheduleDAO();

        // Add some test schedules
        mockScheduleDAO.addSchedule(LocalDate.now(), "Eating", "10:00 AM");
        mockScheduleDAO.addSchedule(LocalDate.now(), "Walking", "5:00 PM");
    }

    @Test
    public void testAddSchedule() {
        // Add a new schedule
        mockScheduleDAO.addSchedule(LocalDate.now(), "Grooming", "7:00 PM");

        // Retrieve the schedules for the current date
        List<String> schedules = mockScheduleDAO.getSchedules(LocalDate.now());

        // Verify that the new schedule was added successfully
        assertEquals(3, schedules.size());
        assertTrue(schedules.contains("Grooming at 7:00 PM"));
    }

    @Test
    public void testGetSchedules() {
        // Retrieve the schedules for the current date
        List<String> schedules = mockScheduleDAO.getSchedules(LocalDate.now());
        assertEquals(2, schedules.size());
        assertTrue(schedules.contains("Eating at 10:00 AM"));
        assertTrue(schedules.contains("Walking at 5:00 PM"));
    }

    @Test
    public void testUpdateTaskCompletionStatus() {
        mockScheduleDAO.updateTaskCompletionStatus(LocalDate.now(), "Eating at 10:00 AM", true);
        assertTrue(mockScheduleDAO.getCompletionStatusForTask(LocalDate.now(), "Eating at 10:00 AM"));
    }

    @Test
    public void testGetCompletionStatusForTask() {
        mockScheduleDAO.updateTaskCompletionStatus(LocalDate.now(), "Walking at 5:00 PM", true);
        boolean completionStatus = mockScheduleDAO.getCompletionStatusForTask(LocalDate.now(), "Walking at 5:00 PM");
        assertTrue(completionStatus);
        assertFalse(mockScheduleDAO.getCompletionStatusForTask(LocalDate.now(), "Eating at 10:00 AM"));
    }
}

