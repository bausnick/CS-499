/* Author: Ben Usnick
 * Date: 6/20/2026
 * Course ID: CS 499 - Capstone
 * Description: Unit testing for AppointmentDAO class. Verify database
 * operations including inserting, updating, and deleting records in the
 * database
 */

package test;

import appointment.Appointment;
import appointment.AppointmentDAO;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

// Extend BaseDBTest to ensure database is reset before each test
public class AppointmentDAOTest extends BaseDBTest{

    @Test
    // Insert appointment into databse and retrieve it using apptID
    void testAddAndGetAppointment() throws Exception {

        AppointmentDAO dao = new AppointmentDAO();
        Appointment appt = new Appointment(
            "123",
            new Date(100000),
            "DAO Test",
            null
        );

        dao.addAppt(appt);

        Appointment result = dao.getAppt("123");

        assertNotNull(result);
        assertEquals("123", result.getApptID());
        assertEquals("DAO Test", result.getDescription());
    }

    @Test
    void testDeleteAppointment() throws Exception {

        AppointmentDAO dao = new AppointmentDAO();
        Appointment appt = new Appointment(
            "456",
            new Date(100000),
            "To Be Deleted",
            null
        );

        dao.addAppt(appt);

        assertNotNull(dao.getAppt("456"));

        dao.deleteAppt("456");

        assertNull(dao.getAppt("456"));
    }

    @Test
    void testUpdateDate() throws Exception {

        AppointmentDAO dao = new AppointmentDAO();
        Appointment appt = new Appointment(
            "789",
            new Date(100000),
            "Update Date Test",
            null
        );

        dao.addAppt(appt);

        Date newDate = new Date(999999999);

        dao.updateDate("789", newDate);

        Appointment updated = dao.getAppt("789");

        assertNotNull(updated);

        // compare time values since Date objects are stored as timestamps
        assertEquals(newDate.getTime(), updated.getApptDate().getTime());
    }

    @Test
    void testUpdateDescription() throws Exception {

        AppointmentDAO dao = new AppointmentDAO();
        Appointment appt = new Appointment(
            "111",
            new Date(100000),
            "Old Desc",
            null
        );

        dao.addAppt(appt);

        dao.updateDescription("111", "New Desc");

        Appointment updated = dao.getAppt("111");

        assertNotNull(updated);
        assertEquals("New Desc", updated.getDescription());
    }
}
