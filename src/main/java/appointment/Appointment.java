/* Author: Ben Usnick
 * Date: 6/20/2026
 * Course ID: CS 499 - Capstone
 * Description: Represents an appointment in the scheduling system. Each 
 * appointment contains a unique ID, date,  description, and optional
 * contactID if relating to a contact. Validation performed with calls from
 * validator.java
 */

package appointment;

import java.util.Date;

import util.Validator;

public class Appointment {

	private final String apptID;
	private Date apptDate;
	private String description;
	private String contactID; // used when building relationship with contact

	// constructor that verifies requirements
	public Appointment(Date apptDate, String description, String contactID) {

		// use universally unique identifier (UUID) for class to generate its own ID
		this.apptID = java.util.UUID.randomUUID().toString();
		
		// Verify apptDate is not null or in the past
		this.apptDate = Validator.validateDate(apptDate);
		
		// Verify description is not equal to null or longer than 50 characters
		this.description = Validator.validateField(description, 50, "Appointment description");
		this.contactID = contactID;
	}

	// Second constructor when no relation to contact with contactID
	public Appointment(Date apptDate, String description) {
		this(apptDate, description, null); // call main constructor
	}

	// Third constructor for loading data from SQLite using getAppt method in DAO.java.
	// Here, apptID is not assigned a new UUID becuase an Appointment already exists
	// with an apptID in SQLite
	public Appointment(String apptID, Date appDate, String description, String contactID) {
		this.apptID =  apptID;
		this.apptDate = Validator.validateDate(appDate);
		this.description = Validator.validateField(description, 50, "Appointment description");
		this.contactID = contactID; // will be null if not assigned
	}

	// Getter methods
	public String getApptID() {
		return apptID;
	}

	public Date getApptDate() {
		return apptDate;
	}

	public String getDescription() {
		return description;
	}

	// Returns ID of associated contact or null if no relationship
	public String getContactID()  {
		return contactID;
	}

	// Setters with validation
	
	public void setDate(Date apptDate) {
		this.apptDate = Validator.validateDate(apptDate);
	}

	public void setDescription(String description) {
		this.description = Validator.validateField(description, 50, "Appointment description");
	}

	// Associate this appointment with a contact
	public void setContactID(String  contactID) {
		this.contactID = contactID;
	}

	// Appointment objects are considered equal if they share a UUID. This lets
	// comparisons between objects from database and memory to be made
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}

		Appointment other = (Appointment) obj;
		return apptID.equals(other.apptID);
	}

	// hashCode needs to be overridden when equals is overridden. This
	// uses apptID so the objects behave correctly
	@Override
	public int hashCode() {
		return apptID.hashCode();
	}
}
