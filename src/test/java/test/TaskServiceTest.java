/* Author: Ben Usnick
 * Date: 6/20/2026
 * Course ID: CS 499 - Capstone
 * Description: Unit testing for TaskService class. Verifies that
 * tasks can be created, retrieved, updated, and deleted through 
 * the service layer.
 */

package test;

import task.TaskService;
import task.Task;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

class TaskServiceTest {

	@Test
	void testAddTask() throws SQLException {

		TaskService service = new TaskService();
		Task task = service.addTask(
			"Test Name",
			"Test Description",
			null);

			assertEquals("Test Name", task.getName());
			assertEquals("Test Description", task.getDescription());
	}

	@Test
	void testDeleteTask() throws SQLException {

		TaskService service = new TaskService();
		Task task = service.addTask(
			"Test Name",
			"Test Description",
			null);

		// test that task exists
		assertEquals(task, service.getTask(task.getTaskID()));

		// delete task and verify 
		service.deleteTask(task.getTaskID());
		assertNull(service.getTask(task.getTaskID()));
	}

	// Test name and decription update
	@Test
	void testUpdateTask() throws SQLException {

		TaskService service = new TaskService();
		Task task = service.addTask(
			"Old Name",
			"Old Description",
			null);

		service.updateName(task.getTaskID(), "New Name");
		service.updateDescription(task.getTaskID(), "New Description");

		Task updated = service.getTask(task.getTaskID());

		assertEquals("New Name", updated.getName());
		assertEquals("New Description", updated.getDescription());
	}
}
