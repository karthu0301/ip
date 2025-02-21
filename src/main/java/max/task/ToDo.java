package max.task;

/**
 * Represents a to-do task that contains only a description
 * without a specific deadline or event period.
 */
public class ToDo extends Task {

    /**
     * Constructs a ToDo task with the given description.
     *
     * @param description The description of the task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns the string representation of the ToDo task.
     *
     * @return A formatted string displaying the task type and description.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns the string representation of the ToDo task for file storage.
     *
     * @return A formatted string used for saving the task in a file.
     */
    @Override
    public String toFileString() {
        return "T | " + (isDone() ? "1" : "0") + " | " + getPriority().getLevel() + " | " + getDescription();
    }
}
