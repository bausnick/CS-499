package database;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseInitializer {
    
    public static void initializeDatabase() {

        String apptTable = """
                CREATE TABLE IF NOT EXISTS appointments (
                appt_id TEXT PRIMARY KEY,
                appt_date INTEGER NOT NULL,
                description TEXT NOT NULL,
                contact_id TEXT,
                FOREIGN KEY (contact_id)
                REFERENCES contacts(contact_id)
                ON DELETE SET NULL); 
                """;

        String contactTable = """
                CREATE TABLE IF NOT EXISTS contacts (
                contact_id TEXT PRIMARY KEY,
                first_name TEXT NOT NULL,
                last_name TEXT NOT NULL,
                phone_number TEXT NOT NULL,
                address TEXT NOT NULL);
                """;

        String taskTable = """
                CREATE TABLE IF NOT EXISTS tasks (
                task_id TEXT PRIMARY KEY,
                name TEXT NOT NULL,
                description TEXT NOT NULL,
                contact_id TEXT,
                FOREIGN KEY (contact_id)
                REFERENCES contacts(contact_id)
                ON DELETE SET NULL);
                """;

        try (
            Connection conn = DatabaseManager.getConnection();
            Statement stmt = conn.createStatement();) {
            
                stmt.execute(contactTable);
                stmt.execute(apptTable);
                stmt.execute(taskTable);

        } catch (SQLException e) {
            e.printStackTrace();
        } 
    }
}