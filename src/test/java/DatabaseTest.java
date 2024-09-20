import com.example.petcarecab302qu.model.*;
import org.junit.jupiter.api.*;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit test class for testing the database connection in the Pet Care application.
 * Verifies whether a connection to the SQLite database can be successfully established.
 */
public class DatabaseTest {
    @Test
    public void testConnection() {
        Connection conn = SqliteConnection.getInstance();
        assertEquals(true, conn != null);
    }
}