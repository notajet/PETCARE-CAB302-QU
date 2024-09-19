import com.example.petcarecab302qu.util.mock.Login;
import com.example.petcarecab302qu.util.mock.MockContactDAO;
import com.example.petcarecab302qu.model.Contact;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginTest {
    private Login loginService;
    private MockContactDAO mockContactDAO;

    @BeforeEach
    public void setUp() {
        // Initialize the mock DAO
        mockContactDAO = new MockContactDAO();
        // Initialize the Login service with the mock DAO
        loginService = new Login(mockContactDAO);

        // Add a sample user for testing
        mockContactDAO.addContact(new Contact("John", "Doe", "john.doe@example.com", "0423423423", "password123"));
    }

    @Test
    public void testSuccessfulLogin() {
        String result = loginService.login("john.doe@example.com", "password123");
        assertEquals("Authentication Successful", result);
    }

    @Test
    public void testLoginWithIncorrectPassword() {
        String result = loginService.login("john.doe@example.com", "wrongpassword");
        assertEquals("Authentication Unsuccessful", result);
    }

    @Test
    public void testLoginWithNonExistentUser() {
        String result = loginService.login("jane.doe@example.com", "password123");
        assertEquals("Authentication Unsuccessful", result);
    }

    @Test
    public void testLoginWithEmptyEmail() {
        String result = loginService.login("", "password123");
        assertEquals("Please provide email.", result);
    }

    @Test
    public void testLoginWithEmptyPassword() {
        String result = loginService.login("john.doe@example.com", "");
        assertEquals("Please provide password.", result);
    }
}
