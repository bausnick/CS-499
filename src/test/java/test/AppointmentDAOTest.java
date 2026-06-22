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
            futureDate(50000), // date that is always in the future
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
            futureDate(50000), // date that is always in the future
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
            futureDate(50000), // date that is always in the future
            "Update Date Test",
            null
        );

        dao.addAppt(appt);

        // new date is further in the future
        Date newDate = new Date(System.currentTimeMillis() + 100000);

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
            futureDate(50000), // date that is always in the future
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
