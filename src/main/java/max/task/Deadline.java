package max.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {
    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma");

    private final LocalDateTime by;

    /**
     * Constructs a Deadline task.
     *
     * @param description Description of the task.
     * @param by         Due date and time in the format "yyyy-MM-dd HHmm".
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = parseDateTime(by);
    }

    /**
     * Parses the provided date-time string into a LocalDateTime object.
     *
     * @param dateTime Date-time string to be parsed.
     * @return LocalDateTime object representing the deadline.
     * @throws IllegalArgumentException If the date-time format is invalid.
     */
    private LocalDateTime parseDateTime(String dateTime) {
        try {
            return LocalDateTime.parse(dateTime, INPUT_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date/time format! Please use 'yyyy-MM-dd HHmm'.");
        }
    }

    /**
     * Returns a formatted string representation of the task.
     *
     * @return Formatted task string.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(OUTPUT_FORMATTER) + ")";
    }

    /**
     * Returns the string representation of the task formatted for file storage.
     *
     * @return String representation for file storage.
     */
    @Override
    public String toFileString() {
        return "D | " + (isDone() ? "1" : "0") + " | " + getDescription() + " | " + by.format(INPUT_FORMATTER);
    }

    /**
     * Checks if the task's deadline is on the given date.
     *
     * @param date Date in "yyyy-MM-dd" format.
     * @return True if the deadline matches the given date, false otherwise.
     */
    public boolean isOnDate(String date) {
        LocalDate givenDate = LocalDate.parse(date);
        return by.toLocalDate().equals(givenDate);
    }
}
