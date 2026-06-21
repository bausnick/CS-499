package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {
    
    // main database
    private static final String DEFAULT_URL = "jdbc:sqlite:cs499_project.db";

    // active database defaults to main database, can switch to testing database
    private static String currentUrl = DEFAULT_URL;

    public static void setDatabaseUrl(String url) {
        currentUrl = url;
    }

    public static Connection getConnection() throws SQLException {
        
        Connection conn = DriverManager.getConnection(currentUrl);

        // Enable use of foreign keys in tables
        try (Statement stmt = conn.createStatement())  {
            stmt.execute("PRAGMA foreign_keys = ON");
        }

        return conn;
    }
}
