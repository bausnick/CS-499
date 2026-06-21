/* Author: Ben Usnick
 * Date: 6/20/2026
 * Course ID: CS 499 - Capstone
 * Description: Unit testing for the Task class. These tests verify
 * object creation, UUID generation, field validation, exception handling,
 * and description length requirements to ensure a valid state of Task objects.
 */

package test;

import task.Task;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class TaskTest {

	@Test
	// verify that a valid task is created
	void testCreateTask() {

		// create new task
		Task task = new Task(
			"Test Name",
			"Test Description");

		// UUID generated automatically upon task creation
		assertNotNull(task.getTaskID());

		// verify constuctor values are correctly assigned
		assertEquals("Test Name", task.getName());
		assertEquals("Test Description", task.getDescription());
	}
	
	@Test
	// Testing for approriate truncation for name over 20 characters
	void testNameTruncation() {
		Task task = new Task(
			"Test Name which is much too long",
			"Test Description");

		assertTrue(task.getName().length() <= 20);
	}
	
	@Test
	// Testing for approriate truncation for description over 50 characters
	void testDescriptionTruncation() {
		Task task = new Task(
			"Test Name",
			"Test Description which is longer than 50 characters and should be truncated");

		assertTrue(task.getDescription().length() <= 50);
	}

	@Test
	// Verify an exception is thrown with a null name
	void testNullName() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Task(
				null,
				"Valid Name");
		});
	}

	@Test
	// Verify an exception is thrown with a null description
	void testNullDescription() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Task(
				"Valid Description",
				null);
		});
	}

}
