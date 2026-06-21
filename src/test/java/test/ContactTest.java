/* Author: Ben Usnick
 * Date: 6/20/2026
 * Course ID: CS 499 - Capstone
 * Description: Unit testing for the Contact class. These tests verify
 * object creation, UUID generation, field validation, exception handling,
 * and description length requirements to ensure a valid state of Contact
 * objects.
 */

package test;

import contact.Contact;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ContactTest {
	
	@Test
	void testCreateContact() {

		Contact contact = new Contact(
			"Ben",
			"Usnick",
			"1234567890",
			"1000 First Street");

		// UUID generated automatically upon creation in Contact.java
		assertNotNull(contact.getContactID());

		assertEquals("Ben", contact.getFirstName());
		assertEquals("Usnick", contact.getLastName());
		assertEquals("1234567890", contact.getNumber());
		assertEquals("1000 First Street", contact.getAddress());
	}

	@Test
	// only the first 10 characters of a name longer than ten should be stored as firstName
	void testFirstNameTruncation() {

		Contact c = new Contact(
			"Benjamin Too Long",
			"Usnick",
			"1234567890",
			"1000 First Street");

		assertTrue(c.getFirstName().length() <= 10);
	}

	@Test
	// only the first 10 characters of a name longer than ten should be stored as lastName
	void testLastNameTruncation() {

		Contact c = new Contact(
			"Ben",
			"Usnick Too Long",
			"1234567890",
			"1000 First Street");

		assertTrue(c.getLastName().length() <= 10);
	}
	
	@Test
	// Only phone numbers with 10 digits are accepted
	void testPhoneNumberLength() {

		assertThrows(IllegalArgumentException.class, () -> {
			new Contact(
				"Ben",
				"Usnick",
				"12345678901234567890",
				"1000 First Street");
		}); 
	}
	
	@Test
	// only the first 30 characters of an address will be stored into Address
	void testAddressTruncation() {

		Contact c = new Contact(
			"Ben",
			"Usnick",
			"1234567890",
			"1000 First Street with additional characters greater than 30");

		assertTrue(c.getAddress().length() <= 30);
	}
	
	@Test
	void testFirstNameNull() {

		assertThrows(IllegalArgumentException.class, () -> {
			new Contact(
				null,
				"Usnick",
				"1234567890",
				"1000 First Street");
		}); 
	}
	
	@Test
	void testLastNameNull() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Contact(
				"Ben",
				null,
				"1234567890",
				"1000 First Street");
		}); 
	}
	
	@Test
	void testNumberNull() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Contact(
				"Ben",
				"Usnick",
				null,
				"1000 First Street");
		}); 
	}
	
	@Test
	void testAddressNull() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Contact(
				"Ben",
				"Usnick",
				"1234567890",
				null);
		}); 
	}
	
}
