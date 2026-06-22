/* Author: Ben Usnick
 * Date: 6/20/2026
 * Course ID: CS 499 - Capstone
 * Description: Represents a task in the scheduling system. Each 
 * task contains a unique ID, name, description, and optional
 * contactID if relating to a contact. Validation performed with calls from
 * validator.java
 */

package task;

import util.Validator;

public class Task {

	private final String taskID;
	private String name;
	private String description;
	private String contactID; // used when building relationship with contact
	
	// constructor that verifies requirements
	public Task(String name, String description, String contactID) {
		
		// use universally unique identifier (UUID) for class to generate its own ID
		this.taskID = java.util.UUID.randomUUID().toString();

		// Verify name is not equal to null or longer than 20 characters
		this.name = Validator.validateField(name, 20, "Task name");
		
		// Verify description is not equal to null or longer than 50 characters
		this.description = Validator.validateField(description, 50, "Task description");
		this.contactID = contactID;
	}

	// Second constructor when no relation to contact with contactID
	public Task(String name, String description) {
		this(name, description, null); // call main constructor
	}

	// Third constructor for loading data from SQLite using getTask method in DAO.java.
	// Here, taskID is not assigned a new UUID becuase a Task already exists
	// with a taskID in SQLite
	public Task(String taskID, String name, String description, String contactID) {
		this.taskID =  taskID;
		this.name = Validator.validateField(name, 20, "Task name");
		this.description = Validator.validateField(description, 50, "Task description");
		this.contactID = contactID; // will be null if not assigned
	}
	
	// Getter methods

	public String getTaskID() {
		return taskID;
	}
	
	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}

	// Returns ID of associated contact or null if no relationship
	public String getContactID() {
		return contactID;
	}

	// Setters wtih validation

	public void setName(String name) {
		this.name = Validator.validateField(name, 20, "Task name");
	}
	
	public void setDescription(String description) {
		this.description = Validator.validateField(description, 50, "Task description");
	}
	
	// Associate this task with a contact
	public void setContactID(String contactID) {
		this.contactID = contactID;
	}

	// Task object are considered equal if they share a UUID. This lets
	// comparisons between objects from database and memory to be made
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}

		Task other = (Task) obj;
		return taskID.equals(other.taskID);
	}

	// hashCode needs to be overridden when equals is overridden. This
	// uses taskID so the objects behave correctly
	@Override
	public int hashCode() {
		return taskID.hashCode();
	}
	
}
