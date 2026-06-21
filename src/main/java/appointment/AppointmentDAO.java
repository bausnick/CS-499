/* Author: Ben Usnick
 * Date: 6/20/2026
 * Course ID: CS 499 - Capstone
 * Description: Handles database access operations for Appointment objects
 * including creating, retrieving, updating, and deleting records in the
 * SQLite database. Prepared statements are used throughout for improved
 * security.
 */

package appointment;

import database.DatabaseManager;
import java.util.Date;
import java.sql.*;

public class AppointmentDAO {

    // addAppt accepts an Appointment object and attempts to save the object
    // into SQLite. If the database fails, exception is thrown to the caller.
    public void addAppt(Appointment appt) throws SQLException {
        // Prepared statements will be used for security and performance
        String sql = """
                INSERT INTO appointments
                (appt_id, appt_date, description, contact_id)
                VALUES (?, ?, ?, ?)
                """;

    // make sure database in connected. Use a try block with Connection and
    // PreparedStatement as they auto close to help prevent a resource leak
    try (
        Connection conn = DatabaseManager.getConnection();
        PreparedStatement pstmt =  conn.prepareStatement(sql))
        {
            // pstmts 1, 2, 3, and 4 will fill in our "?" placeholders above
            pstmt.setString(1, appt.getApptID());
            pstmt.setLong(2, appt.getApptDate().getTime());
            pstmt.setString(3, appt.getDescription());
            pstmt.setString(4, appt.getContactID());
            // the operation is performed for each pstmt with executeUpdate()
            pstmt.executeUpdate();
        }
    }

    /* The following methods will utilize the same exception, prepared statements,
    and try block as in addAppt. The logic will be very similar as well. Refer to
    the comments in addAppt for explaination if needed */

    // Removes an appointment from the database using apptID
    public void deleteAppt(String apptID) throws SQLException {

        String sql =  """
                DELETE FROM appointments
                WHERE appt_id = ?
                """;

        try (
            Connection conn = DatabaseManager.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql))
            {
                pstmt.setString(1, apptID);
                pstmt.executeUpdate();
            }
    }

    // Updates a specified appointment date
    public void updateDate(String apptID, Date date) throws SQLException {
        String sql = """
            UPDATE appointments
            SET appt_date = ?
            WHERE appt_id = ?
            """;

        try (
            Connection conn = DatabaseManager.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql))
            {
                pstmt.setLong(1, date.getTime());
                pstmt.setString(2, apptID);
                pstmt.executeUpdate();
            }
    }

    // updates a specified appointment description
    public void updateDescription(String apptID, String description) throws SQLException {
        String sql = """
            UPDATE appointments
            SET description = ?
            WHERE appt_id = ?
            """;

        try (
            Connection conn = DatabaseManager.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) 
            {
                pstmt.setString(1, description);
                pstmt.setString(2, apptID);
                pstmt.executeUpdate();
            }
    }

    // Retrieves an appointment using apptID or returns null if not found
    public Appointment getAppt(String apptID) throws SQLException {

        String sql = """
            SELECT * FROM appointments
            WHERE appt_id = ?
            """;

        try (
            Connection conn = DatabaseManager.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql))
            {
                pstmt.setString(1, apptID);

                // Execute query and store the returned records in a ResultSet
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {

                    // Create Appointment obeject from the retrieved database values
                    return new Appointment(
                        rs.getString("appt_id"),
                        new Date(rs.getLong("appt_date")),
                        rs.getString("description"),
                        rs.getString("contact_id"));
                }
        }
        return null;
    }
}