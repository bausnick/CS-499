/* Author: Ben Usnick
 * Date: 8/5/2025
 * CourseID: CS 230
 * Description: This is the unit tests for the contact service
 */

package test;

import contact.Contact;
import contact.ContactService;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

public class ContactServiceTest {

	// ENHANCMMENT Category Two
	// Remove need for test ordering.
	// Refactor tests by making addContact in ContactService.Java return a Contact
	// object. This allows for use of getters and removes reliance on hardcoded tests.

	@Test
	void testAddContact() throws SQLException {

		ContactService service = new ContactService();
		Contact contact = service.addContact(
			"Ben",
			"Usnick",
			"1234567890",
			"1000 First Street");

		assertNotNull(contact);
		assertEquals("Ben", contact.getFirstName());
		assertEquals("Usnick", contact.getLastName());
		assertEquals("1234567890", contact.getNumber());
		assertEquals("1000 First Street", contact.getAddress());
	}

	// ENHANCEMENT Category Two
	// Test getContact method
	@Test
	void testGetContact() throws SQLException {

		ContactService service = new ContactService();
		Contact contact = service.addContact(
			"Jon",
			"Doe",
			"1112223333",
			"2000 Second Street");

		// c.getContactID() to get the newly added contact. Then s.getContact performs
		// the actual getContact being tested and stores it in variable "added". Then 
		// assert that c, the created contact, is the same as the "added" contact which
		// used getContact to assign values.
		Contact added = service.getContact(contact.getContactID());
		assertEquals(contact, added);
	}

	@Test
	void testUpdateFirstName() throws SQLException {

		ContactService service = new ContactService();
		Contact contact = service.addContact(
			"Ben",
			"Usnick",
			"1234567890",
			"1000 First Street");

		service.updateFirstName(contact.getContactID(), "New Name");
		assertEquals("New Name", service.getContact(contact.getContactID()).getFirstName());
	}
	
	@Test
	void testUpdateLastName() throws SQLException {

		ContactService service = new ContactService();
		Contact contact = service.addContact(
			"Ben",
			"Usnick",
			"1234567890",
			"1000 First Street");

		service.updateLastName(contact.getContactID(), "New Name");
		assertEquals("New Name", service.getContact(contact.getContactID()).getLastName());
	}

	@Test
	void testUpdateNumber() throws SQLException {

		ContactService service = new ContactService();
		Contact contact = service.addContact(
			"Ben",
			"Usnick",
			"1234567890",
			"1000 First Street");

		service.updateNumber(contact.getContactID(), "1112223333");
		assertEquals("1112223333", service.getContact(contact.getContactID()).getNumber());
	}
	
	@Test
	void testUpdateAddress() throws SQLException {
		
		ContactService service = new ContactService();
		Contact contact = service.addContact(
			"Ben",
			"Usnick",
			"1234567890",
			"1000 First Street");

		service.updateAddress(contact.getContactID(), "2000 Second Street");
		assertEquals("2000 Second Street", service.getContact(contact.getContactID()).getAddress());
	}
	
	@Test
	void testDeleteContact() throws SQLException {
		
		ContactService service = new ContactService();
		Contact contact = service.addContact(
			"Ben",
			"Usnick",
			"1234567890",
			"1000 First Street");

		// test that contact exists
		assertEquals(contact, service.getContact(contact.getContactID()));

		// delete contact and verify 
		service.deleteContact(contact.getContactID());
		assertNull(service.getContact(contact.getContactID()));
	}
}
	
