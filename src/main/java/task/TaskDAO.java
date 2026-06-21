/* Author: Ben Usnick
 * Date: 6/20/2026
 * Course ID: CS 499 - Capstone
 * Description: Handles database access operations for Task objects
 * including creating, retrieving, updating, and deleting records in the
 * SQLite database. Prepared statements are used throughout for improved
 * security.
 */

package task;

import java.sql.*;

import database.DatabaseManager;

public class TaskDAO {
    // addTask accepts a Task object and attempts to save the object
    // into SQLite. If the database fails, exception is thrown to the caller.
    public void addTask(Task task) throws SQLException {
        // Prepared statements will be used for security and performance
        String sql = """
                INSERT INTO tasks
                (task_id, name, description, contact_id)
                VALUES (?, ?, ?, ?)
                """;

    // make sure database in connected. Use a try block with Connection and
    // PreparedStatement as they auto close to help prevent a resource leak
    try (
        Connection conn = DatabaseManager.getConnection();
        PreparedStatement pstmt =  conn.prepareStatement(sql))
        {
            // pstmts 1, 2, 3, and 4 will fill in our "?" placeholders above
            pstmt.setString(1, task.getTaskID());
            pstmt.setString(2, task.getName());
            pstmt.setString(3, task.getDescription());
            pstmt.setString(4, task.getContactID());
            // the operation is performed for each pstmt with executeUpdate()
            pstmt.executeUpdate();
        }
    }

    /* The following methods will utilize the same exception, prepared statements,
    and try block as in addTask. The logic will be very similar as well. Refer to
    the comments in addTask for explaination if needed */

    // Removes a task from the database using taskID
    public void deleteTask(String taskID) throws SQLException {

        String sql =  """
                DELETE FROM tasks
                WHERE task_id = ?
                """;

        try (
            Connection conn = DatabaseManager.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql))
            {
                pstmt.setString(1, taskID);
                pstmt.executeUpdate();
            }
    }

    // updates a specified name
    public void updateName(String taskID, String name) throws SQLException {
        String sql = """
            UPDATE tasks
            SET name = ?
            WHERE task_id = ?
            """;

        try (
            Connection conn = DatabaseManager.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql))
            {
                pstmt.setString(1, name);
                pstmt.setString(2, taskID);
                pstmt.executeUpdate();
            }
    }

    // updates a specified description
    public void updateDescription(String taskID, String description) throws SQLException {
        String sql = """
            UPDATE tasks
            SET description = ?
            WHERE task_id = ?
            """;

        try (
            Connection conn = DatabaseManager.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) 
            {
                pstmt.setString(1, description);
                pstmt.setString(2, taskID);
                pstmt.executeUpdate();
            }
    }

    // Retrieves a task using taskID or returns null if not found
    public Task getTask(String taskID) throws SQLException {

        String sql = """
            SELECT * FROM tasks
            WHERE task_id = ?
            """;

        try (
            Connection conn = DatabaseManager.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql))
            {
                pstmt.setString(1, taskID);

                // Execute query and store the returned records in a ResultSet
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {

                    // Create Task obeject from the retrieved database values
                    return new Task(
                        rs.getString("task_id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getString("contact_id"));
                }
        }
        return null;
    }
}
