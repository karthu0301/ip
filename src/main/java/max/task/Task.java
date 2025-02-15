package max.task;

/**
 * Represents an abstract task with a description and completion status.
 * This serves as a base class for different task types (ToDo, Deadline, Event).
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected Priority priority;
    private static final String DONE_ICON = "X";
    private static final String NOT_DONE_ICON = " ";

    /**
     * Constructs a Task with the given description.
     *
     * @param description The task description.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.priority = Priority.LOW;
    }

    /**
     * Returns whether the task is marked as done.
     *
     * @return True if the task is done, false otherwise.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Returns the task description.
     *
     * @return The task description.
     */
    public String getDescription() {
        return description;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone(){
        this.isDone = true;
        System.out.println("Nice! I've marked this task as done:\n [X] " + this.description);
    }

    /**
     * Marks the task as not done.
     */
    public void markAsNotDone(){
        this.isDone = false;
        System.out.println("OK, I've marked this task as not done yet:\n [] " + this.description);
    }

    /**
     * Returns the status icon for the task.
     *
     * @return "X" if the task is done, otherwise " ".
     */
    public String getStatusIcon() {
        return isDone ? DONE_ICON : NOT_DONE_ICON;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Returns a string representation of the task for file storage.
     *
     * @return A formatted string used for saving the task in a file.
     */
    public abstract String toFileString();

    /**
     * Parses a task from a file string representation.
     *
     * @param line The task string in file format.
     * @return The corresponding Task object.
     * @throws IllegalArgumentException If the task format is invalid.
     */
    public static Task fromFileString(String line) {
        String[] parts = line.split(" \\| ");

        if (parts.length < 3) {
            throw new IllegalArgumentException("Invalid task format: " + line);
        }

        String type = parts[0];
        boolean isDone = parts[1].equals("1");

        Task task;
        switch (type) {
        case "T":
            task = new ToDo(parts[2]);
            break;
        case "D":
            if (parts.length < 4) { throw new IllegalArgumentException("Invalid Deadline format: " + line); }
            task = new Deadline(parts[2], parts[3]);
            break;
        case "E":
            if (parts.length < 5) { throw new IllegalArgumentException("Invalid Event format: " + line); }
            task = new Event(parts[2], parts[3], parts[4]);
            break;
        default:
            throw new IllegalArgumentException("Unknown task type: " + type);
        }

        if (isDone) { task.markAsDone(); }
        return task;
    }
}
