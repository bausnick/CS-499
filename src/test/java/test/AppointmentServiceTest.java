/* Author: Ben Usnick
 * Date: 6/20/2026
 * Course ID: CS 499 - Capstone
 * Description: Unit testing for AppointmentService class
 */

package test;

import appointment.Appointment;
import appointment.AppointmentService;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.Date;

import org.junit.jupiter.api.Test;

public class AppointmentServiceTest extends BaseDBTest {

	// create a date from future for testing
	@SuppressWarnings("deprecation")
	Date futureDateTest = new Date(3025, 7, 30);

	@Test
	// Test for adding appointment
	void testAddAppt() throws SQLException{
		// create new AppointmentService and add Appointment
		AppointmentService service = new AppointmentService();
		Appointment appt = service.addAppt(
			futureDateTest,
			"Good Description",
			null);

		// Test futureDateTest and "Good Description" actually make up the appointment
		assertEquals(futureDateTest, appt.getApptDate());
		assertEquals("Good Description", appt.getDescription());
	}

	@Test
	void testDeleteAppt() throws SQLException{
		// create new AppointmentService and add Appointment
		AppointmentService service = new AppointmentService();
		Appointment appt = service.addAppt(
			futureDateTest,
			"Good Description",
			null);

		// test that appt exists
		assertEquals(appt, service.getAppt(appt.getApptID()));

		// delete appointment and verify 
		service.deleteAppt(appt.getApptID());
		assertNull(service.getAppt(appt.getApptID()));
	}

	@Test
	// Test for updating appointment
	void testUpdateAppt() throws SQLException {
		// create new date to use for testing
		@SuppressWarnings("deprecation")
		Date dateTest = new Date(2099, 1, 1);
		
		// create new AppointmentService and add Appointment
		AppointmentService service = new AppointmentService();
		Appointment appt = service.addAppt(
			futureDateTest,
			"Good Description",
			null);

		// update date and description using ID, then assertTrue to test
		service.updateDate(appt.getApptID(), dateTest);
		service.updateDescription(appt.getApptID(), "New Description");
		assertEquals(dateTest, service.getAppt(appt.getApptID()).getApptDate());
		assertEquals("New Description", service.getAppt(appt.getApptID()).getDescription());
	}
}
