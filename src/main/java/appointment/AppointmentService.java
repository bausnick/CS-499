/* Author: Ben Usnick
 * Date: 6/20/2025
 * Course ID: CS 499 - Capstone
 * Description: This class handles service-layer operations for the Appointment
 * objects. Create, Delete, Update, and Retrieve can be used in coordination
 * with AppointmentDAO for  database use
 */

package appointment;

import java.sql.SQLException;
import java.util.Date;

public class AppointmentService {

	// DAO used for all database operations
	private final AppointmentDAO dao = new AppointmentDAO();

	// Creates a new Appointment and stores it in the database.
	// An Appointment may be created with or without an associated contact
	public Appointment addAppt(Date apptDate, String description, String contactID) throws SQLException {
		Appointment appt;

		// if statement for without or with associated contact
		if (contactID == null || contactID.isBlank())  {
			appt = new Appointment(apptDate, description);
		} else {
			appt = new Appointment(apptDate, description, contactID);
		}
		
		// add to database
		dao.addAppt(appt);
		return appt;
	}

	// Removes an appointment from the database using apptID
	public void deleteAppt(String apptID) throws SQLException {
		dao.deleteAppt(apptID);
	}

	// Update appointment date
	public void updateDate(String apptID, Date apptDate) throws SQLException {
		dao.updateDate(apptID, apptDate);
	}

	// Updates appointment description
	public void updateDescription(String apptID, String description) throws SQLException {
		dao.updateDescription(apptID, description);
	}
	
	// Retrieves appointment from the database using apptID
	public Appointment getAppt(String apptID) throws SQLException {
		return dao.getAppt(apptID);
	}

}
