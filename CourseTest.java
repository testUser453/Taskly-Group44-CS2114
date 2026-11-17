import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.LinkedList;

public class CourseTest {

    private Course course;
    private Task task1;
    private Task task2;

    @Before
    public void setUp() {
        course = new Course("CS_2114");
        task1 = new Task("Homework", 60);
        task2 = new Task("Quiz", 30);
    }

    // -------------------------
    // Constructor & getName() tests
    // -------------------------

    @Test
    public void testConstructorValid() {
        assertEquals("CS_2114", course.getName());
        assertEquals(0, course.getTaskCount());
    }

    @Test
    public void testConstructorTrimsName() {
        Course trimmed = new Course("   MATH_1225   ");

        assertEquals("MATH_1225", trimmed.getName());
    }

    @Test
    public void testConstructorNullName() {
        try {
            new Course(null);
            fail("Expected IllegalArgumentException");
        }
        catch (IllegalArgumentException e) {
            // Expected
        }
    }

    @Test
    public void testConstructorEmptyName() {
        try {
            new Course("");
            fail("Expected IllegalArgumentException");
        }
        catch (IllegalArgumentException e) {
            // Expected
        }
    }

    @Test
    public void testConstructorBlankName() {
        try {
            new Course("   ");
            fail("Expected IllegalArgumentException");
        }
        catch (IllegalArgumentException e) {
            // Expected
        }
    }

    // -------------------------
    // addTask() & getTaskCount() tests
    // -------------------------

    @Test
    public void testAddTask() {
        course.addTask(task1);

        assertEquals(1, course.getTaskCount());
        assertEquals(task1, course.peekNextTask());
    }

    @Test
    public void testAddMultipleTasks() {
        course.addTask(task1);
        course.addTask(task2);

        assertEquals(2, course.getTaskCount());
        assertEquals(task1, course.peekNextTask());
    }

    @Test
    public void testAddTaskNull() {
        try {
            course.addTask(null);
            fail("Expected IllegalArgumentException");
        }
        catch (IllegalArgumentException e) {
            // Expected
        }
    }

    // -------------------------
    // peekNextTask() tests
    // -------------------------

    @Test
    public void testPeekNextTask() {
        course.addTask(task1);
        course.addTask(task2);

        assertEquals(task1, course.peekNextTask());
        assertEquals(2, course.getTaskCount());
    }

    @Test
    public void testPeekNextTaskEmpty() {
        assertNull(course.peekNextTask());
    }

    // -------------------------
    // removeTask() tests
    // -------------------------

    @Test
    public void testRemoveTask() {
        course.addTask(task1);
        course.addTask(task2);

        assertEquals(task1, course.removeTask());
        assertEquals(1, course.getTaskCount());
        assertEquals(task2, course.removeTask());
        assertEquals(0, course.getTaskCount());
    }

    @Test
    public void testRemoveTaskEmpty() {
        assertNull(course.removeTask());
    }

    // -------------------------
    // getTasks() & getTaskList() tests
    // -------------------------

    @Test
    public void testGetTasksEmpty() {
        assertEquals("[]", course.getTasks());
    }

    @Test
    public void testGetTasks() {
        course.addTask(task1);
        course.addTask(task2);

        String tasks = course.getTasks();

        assertTrue(tasks.contains("Homework"));
        assertTrue(tasks.contains("Quiz"));
    }

    @Test
    public void testGetTaskListReturnsCopy() {
        course.addTask(task1);
        course.addTask(task2);

        LinkedList<Task> copy = course.getTaskList();
        assertEquals(2, copy.size());

        copy.clear();
        assertEquals(2, course.getTaskCount());
    }

    // -------------------------
    // completeTask() tests
    // -------------------------

    @Test
    public void testCompleteTask() {
        course.addTask(task1);

        assertTrue(course.completeTask("Homework"));
        assertTrue(task1.isCompleted());
    }

    @Test
    public void testCompleteTaskTrimsName() {
        course.addTask(task1);

        assertTrue(course.completeTask("   Homework   "));
        assertTrue(task1.isCompleted());
    }

    @Test
    public void testCompleteTaskAlreadyCompleted() {
        course.addTask(task1);
        course.completeTask("Homework");

        assertFalse(course.completeTask("Homework"));
    }

    @Test
    public void testCompleteTaskDuplicateNames() {
        Task duplicate = new Task("Homework", 30);
        course.addTask(task1);
        course.addTask(duplicate);

        assertTrue(course.completeTask("Homework"));
        assertTrue(task1.isCompleted());
        assertFalse(duplicate.isCompleted());

        assertTrue(course.completeTask("Homework"));
        assertTrue(duplicate.isCompleted());

        assertFalse(course.completeTask("Homework"));
    }

    @Test
    public void testCompleteTaskNull() {
        course.addTask(task1);

        assertFalse(course.completeTask(null));
    }

    @Test
    public void testCompleteTaskBlank() {
        course.addTask(task1);

        assertFalse(course.completeTask("   "));
    }

    @Test
    public void testCompleteTaskNonexistent() {
        course.addTask(task1);

        assertFalse(course.completeTask("Project"));
    }
}
