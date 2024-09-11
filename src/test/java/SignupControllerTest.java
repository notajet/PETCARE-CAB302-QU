import com.example.petcarecab302qu.controller.SignupController;
import com.example.petcarecab302qu.model.Contact;
import com.example.petcarecab302qu.model.MockContactDAO;
import javafx.application.Platform;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SignupControllerTest {

    private SignupController signupController;
    private MockContactDAO mockContactDAO;

    @BeforeAll
    public static void initToolkit() {
        Platform.startup(() -> {}); // Initialize the JavaFX toolkit for testing
    }


    @BeforeEach
    public void setUp() {
        // Initialize the mock DAO and the signup controller
        mockContactDAO = new MockContactDAO();
        signupController = new SignupController();
        signupController.setContactDAO(mockContactDAO);

        // Initialize the fields using setter methods
        signupController.setFirstNameField(new TextField());
        signupController.setLastNameField(new TextField());
        signupController.setEmailField(new TextField());
        signupController.setPhoneField(new TextField());
        signupController.setPasswordField(new PasswordField()); // Updated to PasswordField
        signupController.setErrorMessage(new Text());
    }

    @Test
    public void testValidSignUp() {
        // Set valid sign-up data using getter methods
        signupController.getFirstNameField().setText("John");
        signupController.getLastNameField().setText("Doe");
        signupController.getEmailField().setText("johndoe@example.com");
        signupController.getPhoneField().setText("0423423423");
        signupController.getPasswordField().setText("password123");

        // Execute sign-up action
        signupController.handleSignUpAction();

        // Verify that the contact was added correctly
        Contact addedContact = mockContactDAO.getAllContacts().get(0);
        assertEquals("John", addedContact.getFirstName());
        assertEquals("Doe", addedContact.getLastName());
        assertEquals("johndoe@example.com", addedContact.getEmail());
        assertEquals("0423423423", addedContact.getPhone());
        assertEquals("password123", addedContact.getPassword());

        // Verify success message
        assertEquals("Sign-Up Successful!", signupController.getErrorMessage().getText());
    }

    @Test
    public void testMissingFieldsSignUp() {
        // Leave the lastNameField empty using setter methods
        signupController.getFirstNameField().setText("John");
        signupController.getLastNameField().setText("");
        signupController.getEmailField().setText("johndoe@example.com");
        signupController.getPhoneField().setText("0423423423");
        signupController.getPasswordField().setText("password123");

        // Execute sign-up action
        signupController.handleSignUpAction();

        // Ensure no contact was added
        assertTrue(mockContactDAO.getAllContacts().isEmpty());

        // Verify error message
        assertEquals("All fields are required!", signupController.getErrorMessage().getText());
    }

    @Test
    public void testEmailAlreadyExists() {
        // Add an existing contact with the same email
        mockContactDAO.addContact(new Contact("Jane", "Doe", "johndoe@example.com", "0423423424", "password"));

        // Set up sign-up data with the same email
        signupController.getFirstNameField().setText("John");
        signupController.getLastNameField().setText("Doe");
        signupController.getEmailField().setText("johndoe@example.com");
        signupController.getPhoneField().setText("0423423423");
        signupController.getPasswordField().setText("password123");

        // Execute sign-up action
        signupController.handleSignUpAction();

        // Ensure no additional contact was added
        assertEquals(1, mockContactDAO.getAllContacts().size());

        // Verify error message for duplicate email
        assertEquals("Email already exists. Please use a different email.", signupController.getErrorMessage().getText());
    }

    @Test
    public void testWeakPasswordSignUp() {
        // Set up sign-up data with a weak password
        signupController.getFirstNameField().setText("John");
        signupController.getLastNameField().setText("Doe");
        signupController.getEmailField().setText("johndoe@example.com");
        signupController.getPhoneField().setText("0423423423");
        signupController.getPasswordField().setText("123");  // Weak password

        // Execute sign-up action
        signupController.handleSignUpAction();

        // Ensure no contact was added
        assertTrue(mockContactDAO.getAllContacts().isEmpty());

        // Verify error message for weak password
        assertEquals("Password must be at least 8 characters long and contain both letters and numbers.", signupController.getErrorMessage().getText());
    }

    @Test
    public void testInvalidEmailFormatSignUp() {
        // Set up sign-up data with an invalid email format
        signupController.getFirstNameField().setText("John");
        signupController.getLastNameField().setText("Doe");
        signupController.getEmailField().setText("invalid-email");  // Invalid email
        signupController.getPhoneField().setText("0423423423");
        signupController.getPasswordField().setText("password123");

        // Execute sign-up action
        signupController.handleSignUpAction();

        // Ensure no contact was added
        assertTrue(mockContactDAO.getAllContacts().isEmpty());

        // Verify error message for invalid email format
        assertEquals("Please enter a valid email address.", signupController.getErrorMessage().getText());
    }
}
