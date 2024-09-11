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
        // Initialize the JavaFX toolkit for tests
        Platform.startup(() -> {}); // Initializes the JavaFX toolkit
    }

    @BeforeEach
    public void setUp() {
        // Initialize the mock DAO and the signup controller
        mockContactDAO = new MockContactDAO();
        signupController = new SignupController();
        signupController.setContactDAO(mockContactDAO);

        // Initialize real JavaFX components
        TextField firstNameField = new TextField();
        TextField lastNameField = new TextField();
        TextField emailField = new TextField();
        TextField phoneField = new TextField();
        PasswordField passwordField = new PasswordField();
        Text errorMessage = new Text();

        // Set the fields in the controller
        signupController.setFirstNameField(firstNameField);
        signupController.setLastNameField(lastNameField);
        signupController.setEmailField(emailField);
        signupController.setPhoneField(phoneField);
        signupController.setPasswordField(passwordField);
        signupController.setErrorMessage(errorMessage);
    }

    @Test
    public void testValidSignUp() {
        // Simulate user input for a valid sign-up
        signupController.getFirstNameField().setText("John");
        signupController.getLastNameField().setText("Doe");
        signupController.getEmailField().setText("johndoe@example.com");
        signupController.getPhoneField().setText("0423423423");
        signupController.getPasswordField().setText("password123");

        // Execute the sign-up action
        signupController.handleSignUpAction();

        // Verify that the contact was added to the DAO
        Contact addedContact = mockContactDAO.getAllContacts().get(0);
        assertEquals("John", addedContact.getFirstName());
        assertEquals("Doe", addedContact.getLastName());
        assertEquals("johndoe@example.com", addedContact.getEmail());
        assertEquals("0423423423", addedContact.getPhone());
        assertEquals("password123", addedContact.getPassword());

        // Verify that the success message was set
        assertEquals("Sign-Up Successful!", signupController.getErrorMessage().getText());
    }

    // Other test cases follow the same pattern (e.g., testMissingFieldsSignUp, testEmailAlreadyExists, etc.)
}
