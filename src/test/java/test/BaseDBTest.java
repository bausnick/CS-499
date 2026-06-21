package test;

import database.DatabaseInitializer;
import database.DatabaseManager;
import org.junit.jupiter.api.BeforeEach;

import java.sql.Connection;
import java.sql.Statement;

/* Instead of repeating this logic in every test that requires use of a
database, an abstract class will be created to centralize switching to
the testing database and clearing it for testing. */
public abstract class BaseDBTest {

    @BeforeEach
    void setupDatabase() throws Exception  {

        // swtich to testing database
        DatabaseManager.setDatabaseUrl("jdbc:sqlite:test.db");

        // build clean database
        DatabaseInitializer.initializeDatabase();
        clearTables();
    }

    private void clearTables() throws Exception {
        
        try (Connection conn = DatabaseManager.getConnection();
            Statement stmt = conn.createStatement()) {

                stmt.executeUpdate("DELETE FROM appointments");
                stmt.executeUpdate("DELETE FROM contacts");
                stmt.executeUpdate("DELETE FROM tasks");
            }
    }
}
