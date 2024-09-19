import com.example.petcarecab302qu.model.Contact;
import com.example.petcarecab302qu.util.mock.MockContactDAO;
import com.example.petcarecab302qu.util.mock.MockSignup;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MockSignupTest {
    private MockSignup mockSignupService;
    private MockContactDAO mockContactDAO;

    @BeforeEach
    public void setUp() {
        mockContactDAO = new MockContactDAO();
        mockSignupService = new MockSignup(mockContactDAO);
    }

    @Test
    public void testSuccessfulSignup() {
        String result = mockSignupService.signup("John", "Doe", "john.doe@example.com", "0423423423", "Password123!");
        assertEquals("Signup successful!", result);
        assertEquals(1, mockContactDAO.getAllContacts().size());
    }

    @Test
    public void testSignupWithExistingEmail() {
        // Add a contact to simulate an existing email
        mockContactDAO.addContact(new Contact("Jane", "Doe", "jane.doe@example.com", "0423423424", "Password123!"));

        String result = mockSignupService.signup("John", "Doe", "jane.doe@example.com", "0423423423", "Password123!");
        assertEquals("Email already exists.", result);
    }

    @Test
    public void testSignupWithMissingFields() {
        String result = mockSignupService.signup("", "Doe", "john.doe@example.com", "0423423423", "Password123!");
        assertEquals("All fields must be filled out.", result);
    }

    @Test
    public void testSignupWithInvalidEmail() {
        String result = mockSignupService.signup("John", "Doe", "invalid-email", "0423423423", "Password123!");
        assertEquals("Please enter a valid email address.", result);
    }

    @Test
    public void testSignupWithWeakPassword() {
        String result = mockSignupService.signup("John", "Doe", "john.doe@example.com", "0423423423", "weakpassword");
        assertEquals("Password must be at least 8 characters long and contain both letters and numbers.", result);
    }
}
