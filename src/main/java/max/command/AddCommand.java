package max.command;

import max.exception.MaxException;
import max.storage.Storage;
import max.task.Deadline;
import max.task.Event;
import max.task.Priority;
import max.task.Task;
import max.task.TaskList;
import max.task.ToDo;

/**
 * Represents a command to add a new task.
 */
public class AddCommand extends Command {
    private static final String TODO = "todo";
    private static final String DEADLINE = "deadline";
    private static final String EVENT = "event";

    private final String type;
    private final String description;
    private final Priority priority;

    /**
     * Constructs an AddCommand with the specified task type and description.
     *
     * @param type        The type of task (todo, deadline, event).
     * @param description The description of the task.
     * @param priority    The priority of the task.
     */
    public AddCommand(String type, String description, Priority priority) {
        this.type = type;
        this.description = description;
        this.priority = priority;
    }

    /**
     * Executes the add command by adding a new task to the task list.
     *
     * @param tasks   The task list to modify.
     * @param storage The storage handler for saving changes.
     * @return A message confirming the task was added.
     * @throws MaxException If the task description is invalid.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws MaxException {
        validateDescription();
        Task task = createTask();
        task.setPriority(priority);
        return addTaskToList(tasks, storage, task);
    }

    /**
     * Validates the task description to ensure it's not empty.
     *
     * @throws MaxException If the description is empty.
     */
    private void validateDescription() throws MaxException {
        if (description == null || description.trim().isEmpty()) {
            throw new MaxException("Oh dear, an error! Allow me to correct it please, the description of a " + type
                    + " cannot be empty.");
        }
    }

    /**
     * Creates a task based on the type specified.
     *
     * @return The created task.
     * @throws MaxException If the task type or format is invalid.
     */
    private Task createTask() throws MaxException {
        switch (type) {
        case TODO:
            return new ToDo(description);
        case DEADLINE:
            return createDeadline();
        case EVENT:
            return createEvent();
        default:
            throw new MaxException("Invalid task type!");
        }
    }

    /**
     * Parses the description and creates a Deadline task.
     *
     * @return The created Deadline task.
     * @throws MaxException If the deadline format is incorrect.
     */
    private Task createDeadline() throws MaxException {
        String[] parts = description.split(" /by ");
        if (parts.length < 2) {
            throw new MaxException("Why, an invalid deadline format in the flesh! Use 'yyyy-MM-dd HHmm', dear sir!");
        }
        try {
            return new Deadline(parts[0], parts[1]);
        } catch (IllegalArgumentException e) {
            throw new MaxException(e.getMessage());
        }
    }

    /**
     * Parses the description and creates an Event task.
     *
     * @return The created Event task.
     * @throws MaxException If the event format is incorrect.
     */
    private Task createEvent() throws MaxException {
        String[] parts = description.split(" /from ");
        if (parts.length < 2 || !parts[1].contains(" /to ")) {
            throw new MaxException("Oh dear, an error. Allow me to correct it. "
                    + "Use: event [description] /from [start] /to [end]");
        }
        String[] timeParts = parts[1].split(" /to ");
        try {
            return new Event(parts[0], timeParts[0], timeParts[1]);
        } catch (IllegalArgumentException e) {
            throw new MaxException(e.getMessage());
        }
    }

    /**
     * Adds a task to the list, saves it, and returns a confirmation message.
     *
     * @param tasks   The task list.
     * @param storage The storage handler.
     * @param task    The task to add.
     * @return A confirmation message.
     */
    private String addTaskToList(TaskList tasks, Storage storage, Task task) throws MaxException {
        tasks.addTask(task);
        storage.save(tasks.getTasks());
        return "Certainly, sir. I shall add that task immediately. :\n  " + task + "\nNow you have "
                + tasks.size() + " tasks in the list.";
    }
}
