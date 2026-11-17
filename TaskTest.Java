import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * Tests Task: both constructors, name / minutes / due date / completed
 * getters and setters, and toString. Follows the spec's test plan
 * (normal, bad, and edge case per method) against the current Task API.
 */
class TaskTest {

    // ---- constructors
    @Test void twoArgConstructorStoresNameAndMinutes() {
        Task t = new Task("Homework 1", 60);
        assertEquals("Homework 1", t.getName());
        assertEquals(60, t.getMinsToComplete());
        assertNull(t.getDueDate());
        assertFalse(t.isCompleted());
    }

    @Test void threeArgConstructorStoresDueDate() {
        Task t = new Task("Homework 1", 60, "11/15/2026");
        assertEquals("Homework 1", t.getName());
        assertEquals(60, t.getMinsToComplete());
        assertEquals("11/15/2026", t.getDueDate());
        assertFalse(t.isCompleted());
    }

    // ---- name
    @Test void setNameReplacesName() {
        Task t = new Task("Homework 1", 60);
        t.setName("Homework 2");
        assertEquals("Homework 2", t.getName());
    }

    @Test void setNameTrimsSurroundingSpaces() {
        Task t = new Task("Homework 1", 60);
        t.setName("   Lab 3  ");
        assertEquals("Lab 3", t.getName());
    }

    @Test void setNameRejectsNullOrBlankAndKeepsOldName() {
        Task t = new Task("Homework 1", 60);
        assertThrows(IllegalArgumentException.class, () -> t.setName(null));
        assertThrows(IllegalArgumentException.class, () -> t.setName(""));
        assertThrows(IllegalArgumentException.class, () -> t.setName("   "));
        assertEquals("Homework 1", t.getName());
    }

    // ---- minutes
    @Test void setMinsToCompleteReplacesMinutes() {
        Task t = new Task("Homework 1", 60);
        t.setMinsToComplete(1);   // smallest legal value
        assertEquals(1, t.getMinsToComplete());
    }

    @Test void setMinsToCompleteRejectsZeroOrNegativeAndKeepsOldValue() {
        Task t = new Task("Homework 1", 60);
        assertThrows(IllegalArgumentException.class, () -> t.setMinsToComplete(0));
        assertThrows(IllegalArgumentException.class, () -> t.setMinsToComplete(-5));
        assertEquals(60, t.getMinsToComplete());
    }

    // ---- due date
    @Test void setDueDateAcceptsValidDate() {
        Task t = new Task("Homework 1", 60);
        t.setDueDate("11/15/2026");
        assertEquals("11/15/2026", t.getDueDate());
    }

    @Test void setDueDateAcceptsFeb28NonLeapYearAndFeb29LeapYear() {
        Task t = new Task("Homework 1", 60);
        t.setDueDate("02/28/2027");
        assertEquals("02/28/2027", t.getDueDate());
        t.setDueDate("02/29/2028");
        assertEquals("02/29/2028", t.getDueDate());
    }

    @Test void setDueDateRejectsImpossibleOrMalformedDatesAndKeepsOldDate() {
        Task t = new Task("Homework 1", 60, "11/15/2026");
        assertThrows(IllegalArgumentException.class, () -> t.setDueDate("13/45/2026"));
        assertThrows(IllegalArgumentException.class, () -> t.setDueDate("02/29/2027"));
        assertThrows(IllegalArgumentException.class, () -> t.setDueDate("Next Friday"));
        assertThrows(IllegalArgumentException.class, () -> t.setDueDate("2026-11-15"));
        assertThrows(IllegalArgumentException.class, () -> t.setDueDate("1/5/2026"));
        assertEquals("11/15/2026", t.getDueDate());
    }

    @Test void setDueDateNullOrBlankClearsDate() {
        Task t = new Task("Homework 1", 60, "11/15/2026");
        t.setDueDate(null);
        assertNull(t.getDueDate());
        t.setDueDate("11/15/2026");
        t.setDueDate("   ");
        assertNull(t.getDueDate());
    }

    // ---- completed
    @Test void setCompletedTogglesStatus() {
        Task t = new Task("Homework 1", 60);
        t.setCompleted(true);
        assertTrue(t.isCompleted());
        t.setCompleted(false);
        assertFalse(t.isCompleted());
    }

    // ---- toString
    @Test void toStringShowsAllFields() {
        Task t = new Task("Homework 1", 60, "11/15/2026");
        assertEquals("Task Name: Homework 1 Total Mins to complete task: 60"
            + " Due Date of task: 11/15/2026 Completed: false", t.toString());
    }

    @Test void toStringShowsNotSetWhenNoDueDate() {
        Task t = new Task("Homework 1", 60);
        t.setCompleted(true);
        assertEquals("Task Name: Homework 1 Total Mins to complete task: 60"
            + " Due Date of task: Not set Completed: true", t.toString());
    }

    // ---- independence
    @Test void separateTasksKeepSeparateState() {
        Task a = new Task("Homework 1", 60, "11/15/2026");
        Task b = new Task("Lab 2", 30, "12/01/2026");
        a.setCompleted(true);
        assertEquals("Homework 1", a.getName());
        assertEquals("Lab 2", b.getName());
        assertEquals("12/01/2026", b.getDueDate());
        assertFalse(b.isCompleted());
    }
}
