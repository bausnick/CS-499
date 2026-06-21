/* Author: Ben Usnick
 * Date: 6/20/2026
 * Course ID: CS 499 - Capstone
 * Description: Handles database access operations for Contact objects
 * including creating, retrieving, updating, and deleting records in the
 * SQLite database. Prepared statements are used throughout for improved
 * security.
 */

package contact;

import java.sql.*;

import database.DatabaseManager;

public class ContactDAO {

    // addContact accepts a Contact object and attempts to save the object
    // into SQLite. If the database fails, exception is thrown to the caller.
    public void addContact(Contact contact) throws SQLException {
        // Prepared statements will be used for security and performance
        String sql = """
                INSERT INTO contacts
                (contact_id, first_name, last_name, phone_number, address)
                VALUES (?, ?, ?, ?, ?)
                """;

    // make sure database in connected. Use a try block with Connection and
    // PreparedStatement as they auto close to help prevent a resource leak
    try (
        Connection conn = DatabaseManager.getConnection();
        PreparedStatement pstmt =  conn.prepareStatement(sql))
        {
            // pstmts 1, 2, 3, 4, and 5 will fill in our "?" placeholders above
            pstmt.setString(1, contact.getContactID());
            pstmt.setString(2, contact.getFirstName());
            pstmt.setString(3, contact.getLastName());
            pstmt.setString(4, contact.getNumber());
            pstmt.setString(5, contact.getAddress());
            // the operation is performed for each pstmt with executeUpdate()
            pstmt.executeUpdate();
        }
    }

    /* The following methods will utilize the same exception, prepared statements,
    and try block as in addAppt. The logic will be very similar as well. Refer to
    the comments in addAppt for explaination if needed */

    // Removes a contact from the databse using contactID
    public void deleteContact(String contactID) throws SQLException {

        String sql =  """
                DELETE FROM contacts
                WHERE contact_id = ?
                """;

        try (
            Connection conn = DatabaseManager.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql))
            {
                pstmt.setString(1, contactID);
                pstmt.executeUpdate();
            }
    }

    // Updates a specified first name
    public void updateFirstName(String contactID, String firstName) throws SQLException {
        String sql = """
            UPDATE contacts
            SET first_name = ?
            WHERE contact_id = ?
            """;

        try (
            Connection conn = DatabaseManager.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql))
            {
                pstmt.setString(1, firstName);
                pstmt.setString(2, contactID);
                pstmt.executeUpdate();
            }
    }

    // Updates a specified last name
    public void updateLastName(String contactID, String lastName) throws SQLException {
        String sql = """
            UPDATE contacts
            SET last_name = ?
            WHERE contact_id = ?
            """;

        try (
            Connection conn = DatabaseManager.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) 
            {
                pstmt.setString(1, lastName);
                pstmt.setString(2, contactID);
                pstmt.executeUpdate();
            }
    }
    
    // Updates a specified phone number
    public void updateNumber(String contactID, String number) throws SQLException {
        String sql = """
            UPDATE contacts
            SET phone_number = ?
            WHERE contact_id = ?
            """;

        try (
            Connection conn = DatabaseManager.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) 
            {
                pstmt.setString(1, number);
                pstmt.setString(2, contactID);
                pstmt.executeUpdate();
            }
    }

    // Updates a specified addresss
    public void updateAddress(String contactID, String address) throws SQLException {
        String sql = """
            UPDATE contacts
            SET address = ?
            WHERE contact_id = ?
            """;

        try (
            Connection conn = DatabaseManager.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) 
            {
                pstmt.setString(1, address);
                pstmt.setString(2, contactID);
                pstmt.executeUpdate();
            }
    }

    // Retrieves a contact using contactID or returns null if not found
    public Contact getContact(String contactID) throws SQLException {

        String sql = """
            SELECT * FROM contacts
            WHERE contact_id = ?
            """;

        try (
            Connection conn = DatabaseManager.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql))
            {
                pstmt.setString(1, contactID);

                // Execute query and store the returned records in a ResultSet
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {

                    // Create Contact obeject from the retrieved database values
                    return new Contact(
                        rs.getString("contact_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("phone_number"),
                        rs.getString("address"));
                }
        }
        return null;
    }
}
