/* Author: Ben Usnick
 * Date: 8/5/2025
 * Course ID: CS 320
 * Description: Tests for TaskService.java to ensure appropriate task manipulation
 */

package test;

import task.TaskService;
import task.Task;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

class TaskServiceTest {

	// ENHANCMMENT Category Two
	// Remove need for test ordering.
	// Refactor tests by making addTask in TaskService.Java return a Task
	// object. This allows for use of getters and removes reliance on hardcoded tests.

	// ENHANCEMENT Category Two
	// refactor test for adding a task
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
