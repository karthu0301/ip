package max.task;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for the {@link Event} class.
 */
public class EventTest {

    /**
     * Tests for valid constructor input.
     */
    @Test
    public void constructor_validInput_success() {
        String description = "Conference";
        String from = "2025-04-10 0900";
        String to = "2025-04-10 1700";
        Event event = new Event(description, from, to);
        String eventString = event.toString();
        assertTrue(eventString.contains("from:"));
        assertTrue(eventString.contains("to:"));
    }

    /**
     * Tests for invalid start date.
     */
    @Test
    public void constructor_startAfterEnd_throwsException() {
        String description = "Conference";
        String from = "2025-04-10 1700";
        String to = "2025-04-10 0900";
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Event(description, from, to);
        });
        assertTrue(exception.getMessage().contains("As per common sense, start time must be before end time."));
    }

    /**
     * Tests for invalid date format.
     */
    @Test
    public void constructor_invalidDateFormat_throwsException() {
        String description = "Conference";
        String from = "2025/04/10 0900";
        String to = "2025-04-10 1700";
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Event(description, from, to);
        });
        assertTrue(exception.getMessage().contains("invalid format"));
    }
}
