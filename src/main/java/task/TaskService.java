/* Author: Ben Usnick
 * Date: 6/20/2026
 * Course ID: CS 499 - Capstone
 * Description: This class handles service-layer operations for the Task
 * objects. Create, Delete, Update, and Retrieve can be used in coordination
 * with TaskDAO for database use
 */

package task;

import java.sql.SQLException;

public class TaskService {
	
	// DAO used for all database operations
	private final TaskDAO dao = new TaskDAO();

	// Creates a new Task and stores it in the database.
	// A Task may be created with or without an associated contact
	public Task addTask(String name, String description, String contactID) throws SQLException {
		Task task;

		// if statement for without or with associated contact
		if (contactID == null || contactID.isBlank())  {
			task = new Task(name, description);
		} else {
			task = new Task(name, description, contactID);
		}
		
		// add to database
		dao.addTask(task);
		return task;
	}
	
	// Removes a task from the database using taskID
	public void deleteTask(String taskID) throws SQLException {
		dao.deleteTask(taskID);
	}

	// updates task name
	public void updateName(String taskID, String name) throws SQLException {
		dao.updateName(taskID, name);
	}

	// updates task description
	public void updateDescription(String taskID, String description) throws SQLException {
		dao.updateDescription(taskID, description);
	}
	
	// retrieves task from the database using taskID
	public Task getTask(String taskID) throws SQLException {
		return dao.getTask(taskID);
	}

}
