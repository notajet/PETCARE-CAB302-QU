import com.example.petcarecab302qu.model.entities.Contact;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit test class for testing the Contact model in the Pet Care application.
 * Verifies the getter and setter methods for the Contact class fields such as id, first name, last name, email, and phone.
 */
public class ContactTest {
    private Contact contact;

    @BeforeEach
    public void setUp() {
        contact = new Contact("John", "Doe", "john.doe@example.com", "1234567890", "qutiscool123");
    }

    @Test
    public void testGetId() {
        contact.setId(1);
        assertEquals(1, contact.getId());
    }

    @Test
    public void testGetFirstName() {
        assertEquals("John", contact.getFirstName());
    }

    @Test
    public void testSetFirstName() {
        contact.setFirstName("Jane");
        assertEquals("Jane", contact.getFirstName());
    }

    @Test
    public void testGetLastName() {
        assertEquals("Doe", contact.getLastName());
    }

    @Test
    public void testSetLastName() {
        contact.setLastName("Smith");
        assertEquals("Smith", contact.getLastName());
    }

    @Test
    public void testGetEmail() {
        assertEquals("john.doe@example.com", contact.getEmail());
    }

    @Test
    public void testSetEmail() {
        contact.setEmail("jane.smith@example.com");
        assertEquals("jane.smith@example.com", contact.getEmail());
    }

    @Test
    public void testGetPhone() {
        assertEquals("1234567890", contact.getPhone());
    }

    @Test
    public void testSetPhone() {
        contact.setPhone("0987654321");
        assertEquals("0987654321", contact.getPhone());
    }

    @Test
    public void testGetFullName() {
        assertEquals("John Doe", contact.getFullName());
    }
}