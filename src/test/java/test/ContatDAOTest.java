/* Author: Ben Usnick
 * Date: 6/20/2026
 * Course ID: CS 499 - Capstone
 * Description: Unit testing for ContactDAO class. Verify database
 * operations including inserting, updating, and deleting records in the
 * database
 */

package test;

import contact.Contact;
import contact.ContactDAO;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class ContatDAOTest extends BaseDBTest {

    @Test
    void testAddAndGetContact() throws Exception {

        ContactDAO dao = new ContactDAO();
        Contact contact = new Contact(
            "123",
            "John",
            "Doe",
            "1234567890",
            "123 Main St"
        );

        dao.addContact(contact);

        Contact result = dao.getContact("123");

        assertNotNull(result);
        assertEquals("123", result.getContactID());
        assertEquals("John", result.getFirstName());
        assertEquals("Doe", result.getLastName());
        assertEquals("1234567890", result.getNumber());
        assertEquals("123 Main St", result.getAddress());
    }

    @Test
    void testDeleteContact() throws Exception {

        ContactDAO dao = new ContactDAO();
        Contact contact = new Contact(
            "456",
            "Jane",
            "Smith",
            "1112223333",
            "456 Oak Ave"
        );

        dao.addContact(contact);

        assertNotNull(dao.getContact("456"));

        dao.deleteContact("456");

        assertNull(dao.getContact("456"));
    }

    @Test
    void testUpdateFirstName() throws Exception {

        ContactDAO dao = new ContactDAO();
        Contact contact = new Contact(
            "789",
            "Bob",
            "Brown",
            "9998887777",
            "789 Pine Rd"
        );

        dao.addContact(contact);

        dao.updateFirstName("789", "Robert");

        Contact updated = dao.getContact("789");

        assertNotNull(updated);
        assertEquals("Robert", updated.getFirstName());
    }

    @Test
    void testUpdateLastName() throws Exception {

        ContactDAO dao = new ContactDAO();

        Contact contact = new Contact(
            "111",
            "Jim",
            "White",
            "2223334444",
            "111 Elm St"
        );

        dao.addContact(contact);

        dao.updateLastName("111", "Black");

        Contact updated = dao.getContact("111");

        assertNotNull(updated);
        assertEquals("Black", updated.getLastName());
    }

    @Test
    void testUpdateNumber() throws Exception {

        ContactDAO dao = new ContactDAO();

        Contact contact = new Contact(
            "222",
            "Gus",
            "Green",
            "5556667777",
            "222 Maple St"
        );

        dao.addContact(contact);

        dao.updateNumber("222", "0001112222");

        Contact updated = dao.getContact("222");

        assertNotNull(updated);
        assertEquals("0001112222", updated.getNumber());
    }

    @Test
    void testUpdateAddress() throws Exception {

        ContactDAO dao = new ContactDAO();

        Contact contact = new Contact(
            "333",
            "Gen",
            "Cantu",
            "8889990000",
            "Old Address"
        );

        dao.addContact(contact);

        dao.updateAddress("333", "New Address");

        Contact updated = dao.getContact("333");

        assertNotNull(updated);
        assertEquals("New Address", updated.getAddress());
    }
}
