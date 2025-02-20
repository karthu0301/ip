package max.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a event task with a start and end time.
 */
public class Event extends Task {
    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma");
    private final LocalDateTime from;
    private final LocalDateTime to;
    /**
     * Constructs an event task.
     *
     * @param description Description of the task.
     * @param from        Start date and time in the format "yyyy-MM-dd HHmm".
     * @param to          End date and time in the format "yyyy-MM-dd HHmm".
     */
    public Event(String description, String from, String to) {
        super(description);
        assert from != null && !from.trim().isEmpty() : "Event start time cannot be empty";
        assert to != null && !to.trim().isEmpty() : "Event end time cannot be empty";
        this.from = parseDateTime(from, "Start time");
        this.to = parseDateTime(to, "End time");

        if (this.from.isAfter(this.to) || this.from.isEqual(this.to)) {
            throw new IllegalArgumentException("As per common sense, start time must be before end time.");
        }
    }

    private LocalDateTime parseDateTime(String dateTime, String fieldName) {
        try {
            return LocalDateTime.parse(dateTime, INPUT_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException(fieldName + " has an invalid format! Please use 'yyyy-MM-dd HHmm'.");
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.format(OUTPUT_FORMATTER)
                + " to: " + to.format(OUTPUT_FORMATTER) + ")";
    }

    @Override
    public String toFileString() {
        return "E | " + (isDone() ? "1" : "0") + " | " + getPriority().getLevel() + " | " + getDescription() + " | "
                + from.format(INPUT_FORMATTER) + " | " + to.format(INPUT_FORMATTER);
    }


    /**
     * Checks if the task's duration is on the given date and time.
     *
     * @param dateTime Date and time in "yyyy-MM-dd T00:00" format.
     * @return True if the deadline matches the given date, false otherwise.
     */
    public boolean isOnDate(String dateTime) {
        LocalDateTime givenDateTime = LocalDateTime.parse(dateTime + "T00:00");
        return from.toLocalDate().equals(givenDateTime.toLocalDate());
    }
}
