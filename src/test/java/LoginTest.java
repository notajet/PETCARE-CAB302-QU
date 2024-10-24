import com.example.petcarecab302qu.util.mock.MockLogin;
import com.example.petcarecab302qu.util.mock.MockContactDAO;
import com.example.petcarecab302qu.model.entities.Contact;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit test class for testing the login functionality in the MockLogin service.
 * Verifies different login scenarios such as successful login, incorrect password, non-existent user,
 * empty email, and empty password.
 */
public class LoginTest {
    private MockLogin mockLoginService;
    private MockContactDAO mockContactDAO;

    @BeforeEach
    public void setUp() {
        mockContactDAO = new MockContactDAO();
        mockLoginService = new MockLogin(mockContactDAO);
        mockContactDAO.addContact(new Contact("John", "Doe", "john.doe@example.com", "0423423423", "password123"));
    }

    @Test
    public void testSuccessfulLogin() {
        String result = mockLoginService.login("john.doe@example.com", "password123");
        assertEquals("Authentication Successful", result);
    }

    @Test
    public void testLoginWithIncorrectPassword() {
        String result = mockLoginService.login("john.doe@example.com", "wrongpassword");
        assertEquals("Authentication Unsuccessful", result);
    }

    @Test
    public void testLoginWithNonExistentUser() {
        String result = mockLoginService.login("jane.doe@example.com", "password123");
        assertEquals("Authentication Unsuccessful", result);
    }

    @Test
    public void testLoginWithEmptyEmail() {
        String result = mockLoginService.login("", "password123");
        assertEquals("Please provide email.", result);
    }

    @Test
    public void testLoginWithEmptyPassword() {
        String result = mockLoginService.login("john.doe@example.com", "");
        assertEquals("Please provide password.", result);
    }
}
