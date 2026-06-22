/* Author: Ben Usnick
 * Date: 6/20/2026
 * CourseID: CS 499 - Capstone
 * Description: Represents a contact in the scheduling system. Each 
 * contact contains a unique ID, first name, last name, phone number,
 * and address. Validation performed with calls from validator.java
 */

package contact;

import task.Task;
import util.Validator;

public class Contact {
	private final String contactID;
	private String firstName;
	private String lastName;
	private String number;
	private String address;

	// CONSTRUCTOR
	/*
	 * The constructor takes first name, last name, phone number, and address as
	 * parameters. The first thing it does is generates a new ID for the contactID
	 * field.
	 *
	 * First name and last name are checked for null condition or blank fields. 
	 * In both cases, if the first or last name is greater than 10
	 * characters, truncate it so that only the first 10 characters are used.
	 *
	 * Address is like first and last names.If it is more than 30 characters, 
	 * truncate to the first 30 characters.
	 */
	public Contact(String firstName, String lastName, String number, String address) {

		// use universally unique identifier (UUID) for class to generate its own ID
		this.contactID = java.util.UUID.randomUUID().toString();

		// Verify first name is not equal to null or longer than 10 characters
		this.firstName = Validator.validateField(firstName, 10, "First name");
		
		// Verify description is not equal to null or longer than 10 characters
		this.lastName = Validator.validateField(lastName, 10, "Last name");
		
		// Verify phone number has ten digits
		this.number = Validator.validatePhoneNumber(number);
		
		// Verify address is not equal to null or longer than 30 characters
		this.address = Validator.validateField(address, 30, "Address");
	}

	// Second constructor for loading data from SQLite using getContact method in DAO.java.
	// Here, contactID is not assigned a new UUID becuase a Contact already exists
	// with a contactID in SQLite
	public Contact(String contactID, String firstName, String lastName, String number, String address) {
		
		this.contactID =  contactID;
		this.firstName = Validator.validateField(firstName, 10, "First name");
		this.lastName = Validator.validateField(lastName, 10, "Last name");
		this.number = Validator.validatePhoneNumber(number);
		this.address = Validator.validateField(address, 30, "Address");
	}

	// GETTERS
	public String getContactID() {
		return contactID;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public String getNumber() {
		return number;
	}
	
	public String getAddress() {
		return address;
	}
	
	// Setters with validation
	
	public void setFirstName(String firstName) {
		this.firstName = Validator.validateField(firstName, 10, "First name");
	}
	
	public void setLastName(String lastName) {
		this.lastName = Validator.validateField(lastName, 10, "Last Name");
	}
	
	public void setNumber(String number) {
		this.number = Validator.validatePhoneNumber(number);
	}
	
	public void setAddress(String address) {
		this.address = Validator.validateField(address, 30, "Address");
	}

	// Contact objects are considered equal if they share a UUID. This lets
	// comparisons between objects from database and memory to be made
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}

		Contact other = (Contact) obj;
		return contactID.equals(other.contactID);
	}

	// hashCode needs to be overridden when equals is overridden. This
	// uses contactID so the objects behave correctly
	@Override
	public int hashCode() {
		return contactID.hashCode();
	}
}
