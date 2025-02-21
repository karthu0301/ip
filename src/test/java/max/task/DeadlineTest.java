package max.task;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for the {@link Deadline} class.
 */
public class DeadlineTest {

    /**
     * Tests for valid constructor input.
     */
    @Test
    public void constructor_validInput_success() {
        String description = "Submit report";
        String by = "2025-03-15 1700";
        Deadline deadline = new Deadline(description, by);
        assertTrue(deadline.toFileString().contains("Submit report"));
        assertTrue(deadline.toFileString().contains("2025-03-15"));
    }

    /**
     * Tests for invalid constructor format.
     */
    @Test
    public void constructor_invalidFormat_throwsException() {
        String description = "Submit report";
        String by = "2025/03/15 17:00";
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Deadline(description, by);
        });
        assertTrue(exception.getMessage().contains("Why, an invalid deadline format in the flesh! "
                +
                "Use 'yyyy-MM-dd HHmm', dear sir!"));
    }

    /**
     * Tests for valid date.
     */
    @Test
    public void isOnDate_validDate_returnsTrue() {
        String description = "Submit report";
        String by = "2025-03-15 1700";
        Deadline deadline = new Deadline(description, by);
        assertTrue(deadline.isOnDate("2025-03-15"));
    }

    /**
     * Tests for invalid date format.
     */
    @Test
    public void isOnDate_invalidDateFormat_throwsException() {
        String description = "Submit report";
        String by = "2025-03-15 1700";
        Deadline deadline = new Deadline(description, by);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            deadline.isOnDate("03-15-2025");
        });
        assertTrue(exception.getMessage().contains("Why, an invalid deadline format in the flesh! "
                +
                "Use 'yyyy-MM-dd', dear sir!"));
    }

}
