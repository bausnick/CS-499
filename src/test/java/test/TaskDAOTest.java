package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import task.Task;
import task.TaskDAO;

public class TaskDAOTest extends BaseDBTest {
    @Test
    void testAddAndGetTask() throws Exception {

        TaskDAO dao = new TaskDAO();
        Task task = new Task(
            "123",
            "Task1",
            "DAO Test",
            null
        );

        dao.addTask(task);

        Task result = dao.getTask("123");

        assertNotNull(result);
        assertEquals("123", result.getTaskID());
        assertEquals("task1", result.getName());
        assertEquals("DAO Test", result.getDescription());
    }

    @Test
    void testDeleteTask() throws Exception {

        TaskDAO dao = new TaskDAO();
        Task task = new Task(
            "456",
            "Task2",
            "To Be Deleted",
            null
        );

        dao.addTask(task);

        assertNotNull(dao.getTask("456"));

        dao.deleteTask("456");

        assertNull(dao.getTask("456"));
    }

    @Test
    void testUpdateName() throws Exception {

        TaskDAO dao = new TaskDAO();
        Task task = new Task(
            "789",
            "To Be Updated",
            "Update Name Test",
            null
        );

        dao.addTask(task);

        dao.updateName("789", "Updated Name");

        Task updated = dao.getTask("789");

        assertNotNull(updated);
        assertEquals("Updated Name", updated.getName());
    }

    @Test
    void testUpdateDescription() throws Exception {

        TaskDAO dao = new TaskDAO();
        Task task = new Task(
            "111",
            "Task3",
            "Old Desc",
            null
        );

        dao.addTask(task);

        dao.updateDescription("111", "New Desc");

        Task updated = dao.getTask("111");

        assertNotNull(updated);
        assertEquals("New Desc", updated.getDescription());
    }
}
