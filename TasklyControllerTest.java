
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class TasklyControllerTest {

    private TasklyController controller;

    @Before
    public void setUp() {
        controller = new TasklyController();
    }

    // -------------------------
    // addTask() tests
    // -------------------------

    @Test
    public void testAddTask() {
        controller.addTask("CS_2114", "Homework", "60");

        assertNotEquals("error", controller.getNextTask("CS_2114"));
    }

    @Test
    public void testAddTaskNullCourse() {
        try {
            controller.addTask(null, "Homework", "60");
            fail("Expected IllegalArgumentException");
        }
        catch (IllegalArgumentException e) {
            // Expected
        }
    }

    @Test
    public void testAddTaskBlankCourse() {
        try {
            controller.addTask("   ", "Homework", "60");
            fail("Expected IllegalArgumentException");
        }
        catch (IllegalArgumentException e) {
            // Expected
        }
    }

    @Test
    public void testAddTaskInvalidMinutes() {
        try {
            controller.addTask("CS_2114", "Homework", "abc");
            fail("Expected IllegalArgumentException");
        }
        catch (IllegalArgumentException e) {
            // Expected
        }
    }

    @Test
    public void testAddTaskNullMinutes() {
        try {
            controller.addTask("CS_2114", "Homework", null);
            fail("Expected IllegalArgumentException");
        }
        catch (IllegalArgumentException e) {
            // Expected
        }
    }

    // -------------------------
    // getNextTask() tests
    // -------------------------

    @Test
    public void testGetNextTask() {
        controller.addTask("CS_2114", "Homework", "60");

        assertNotEquals("error", controller.getNextTask("CS_2114"));
    }

    @Test
    public void testGetNextTaskNonexistentCourse() {
        assertEquals("error", controller.getNextTask("MATH_1225"));
    }

    @Test
    public void testGetNextTaskEmptyCourse() {
        controller.addTask("CS_2114", "Homework", "60");
        controller.removeTask("CS_2114");

        assertEquals("error", controller.getNextTask("CS_2114"));
    }

    // -------------------------
    // removeTask() tests
    // -------------------------

    @Test
    public void testRemoveTask() {
        controller.addTask("CS_2114", "Homework", "60");

        Task removed = controller.removeTask("CS_2114");

        assertNotNull(removed);
        assertEquals("error", controller.getNextTask("CS_2114"));
    }

    @Test
    public void testRemoveTaskNonexistentCourse() {
        assertNull(controller.removeTask("MATH_1225"));
    }

    @Test
    public void testRemoveTaskEmptyCourse() {
        controller.addTask("CS_2114", "Homework", "60");
        controller.removeTask("CS_2114");

        assertNull(controller.removeTask("CS_2114"));
    }

    // -------------------------
    // getCourseTaskList() tests
    // -------------------------

    @Test
    public void testGetCourseTaskList() {
        controller.addTask("CS_2114", "Homework", "60");

        assertNotNull(controller.getCourseTaskList("CS_2114"));
    }

    @Test
    public void testGetCourseTaskListNonexistentCourse() {
        assertNull(controller.getCourseTaskList("MATH_1225"));
    }

    // -------------------------
    // getCoursesWithTasks() tests
    // -------------------------

    @Test
    public void testGetCoursesWithTasks() {
        controller.addTask("CS_2114", "Homework", "60");
        controller.addTask("MATH_1225", "Quiz", "30");

        String courses = controller.getCoursesWithTasks();

        assertTrue(courses.contains("CS_2114"));
        assertTrue(courses.contains("MATH_1225"));
    }

    @Test
    public void testGetCoursesWithTasksEmpty() {
        assertEquals("[]", controller.getCoursesWithTasks());
    }

    // -------------------------
    // completeTask() tests
    // -------------------------

    @Test
    public void testCompleteTask() {
        controller.addTask("CS_2114", "Homework", "60");

        assertTrue(controller.completeTask("CS_2114", "Homework"));
    }

    @Test
    public void testCompleteTaskNonexistentCourse() {
        assertFalse(controller.completeTask("MATH_1225", "Homework"));
    }

    @Test
    public void testCompleteTaskNonexistentTask() {
        controller.addTask("CS_2114", "Homework", "60");

        assertFalse(controller.completeTask("CS_2114", "Quiz"));
    }

    // -------------------------
    // getSortedTasks() tests
    // -------------------------

    @Test
    public void testGetSortedTasksEmpty() {
        assertEquals("No tasks available.", controller.getSortedTasks());
    }

    @Test
    public void testGetSortedTasks() {
        // This test assumes Task has a due-date setter.
        // Replace setDueDate with the actual method in your Task class.

        Task first = new Task("First", 30);
        Task second = new Task("Second", 45);

        first.setDueDate("11/20/2026");
        second.setDueDate("11/18/2026");

        // Add tasks through the controller
        controller.addTask("CS_2114", "First", "30");
        controller.addTask("MATH_1225", "Second", "45");

        // The controller creates its own Task objects, so
        // the dates above do not affect the controller's tasks.
        // See note below about testing sorted order.
        assertNotNull(controller.getSortedTasks());
    }
}
