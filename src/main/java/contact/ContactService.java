/* Author: Ben Usnick
 * Date: 6/20/2025
 * CourseID: CS 499 - Capstone
 * Description: This class handles service-layer operations for the Contact
 * objects. Create, Delete, Update, and Retrieve can be used in coordination
 * with ContactDAO for database use
 */

package contact;

import java.sql.SQLException;

public class ContactService {

	// DAO used for all database operations
	private final ContactDAO dao = new ContactDAO();
	
	// Creates a Contact and stores it in the database
	public Contact addContact(String firstName, String lastName, String number, String address) throws SQLException {
		
		Contact contact = new Contact(firstName, lastName, number, address);
		
		dao.addContact(contact);
		return contact;
	}

	// Removes a contact from the database using ContactID
	public void deleteContact(String contactID) throws SQLException {
		dao.deleteContact(contactID);
	}
	
	// Updates first name
	public void updateFirstName(String contactID, String firstName) throws SQLException {
		dao.updateFirstName(contactID, firstName);
	}
	
	// updates last name
	public void updateLastName(String contactID, String lastName) throws SQLException {
		dao.updateLastName(contactID, lastName);
	}
	
	// updates phone number
	public void updateNumber(String contactID, String number) throws SQLException {
		dao.updateNumber(contactID, number);
	}
	
	// updates address
	public void updateAddress(String contactID, String address) throws SQLException {
		dao.updateAddress(contactID, address);
	}
	
	// retrieves contact from the database using contactID
	public Contact getContact(String contactID) throws SQLException{
		return dao.getContact(contactID);
	}
}
