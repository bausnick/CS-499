/* Author: Ben Usnick
 * Date: 6/20/2026
 * Course ID: CS 499 - Capstone
 * Description: Unit testing for the Appointment class. These tests verify
 * object creation, UUID generation, field validation, exception handling,
 * and description length requirements to ensure a valid state of Appointment
 * objects.
 */

package test;

import appointment.Appointment;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;
import org.junit.jupiter.api.Test;

public class AppointmentTest {
	
	// Future data used for appointment creation and validation testing
	@SuppressWarnings("deprecation")
	Date futureDateTest = new Date(3025, 7, 30);
	
	@Test
	// verify that a valid appointment is created
	void testCreateAppt() {
		
		// create new appointment
		Appointment appt = new Appointment(futureDateTest, "Good Description");
		
		// UUID automatically generated on appointment creation
		assertNotNull(appt.getApptID());

		// verify constuctor values are correctly assigned
		assertEquals(futureDateTest, appt.getApptDate());
		assertEquals("Good Description", appt.getDescription());
	}
	
	@Test
	// Verify an exception is thrown with a null appointment date
	void testNullDate() {

		Exception e = assertThrows(
			IllegalArgumentException.class,
			() -> new Appointment(null, "Good Description"));

		// Verify exception matches the Validator output
		assertEquals(
			"Date cannot be null.",
			e.getMessage());
	}
	
	@Test
	// Verify correct exception is thrown for date in the past
	void testPastDate() {

		// create date clearly in past
		Date past = new Date(System.currentTimeMillis() - 50000);

		Exception e = assertThrows(
			IllegalArgumentException.class,
			() -> new Appointment(past, "Good Description"));
		
		// Verify exception matches the Validator output
		assertEquals(
			"Date must be in the future",
			e.getMessage());
	}

	@Test
	// Verify exception is thrown for a null description
	void testNullDescription() {

		Exception e = assertThrows(
			IllegalArgumentException.class,
			() -> new Appointment(futureDateTest, null));
		
		// Verify exception matches the Validator output
		assertEquals(
			"Appointment description cannot be null or blank.",
			e.getMessage());
	}

	@Test
	// Testing for appropriate truncation for description over 50 characterts
	void testDescriptionLength() {

		Appointment appt = new Appointment(
			futureDateTest,
			"This Description is over 50 characters and is Too Long");

		assertTrue(appt.getDescription().length() <= 50);
	}
}
