package max.command;

import max.exception.MaxException;
import max.storage.Storage;
import max.task.Deadline;
import max.task.Event;
import max.task.Task;
import max.task.TaskList;
import max.task.ToDo;
import max.ui.Ui;

/**
 * Represents a command to add a new task (ToDo, Deadline, or Event).
 */
public class AddCommand extends Command {
    private final String type;
    private final String description;

    /**
     * Constructs an AddCommand with the specified task type and description.
     *
     * @param type        The type of task (todo, deadline, event).
     * @param description The description of the task.
     */
    public AddCommand(String type, String description) {
        this.type = type;
        this.description = description;
    }

    /**
     * Executes the add command, adding a new task to the task list.
     *
     * @param tasks   The task list to modify.
     * @param ui      The user interface to display messages.
     * @param storage The storage handler for saving the new task.
     * @throws MaxException If the task description is invalid.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws MaxException {
        if (description == null || description.trim().isEmpty()) {
            throw new MaxException("The description of a " + type + " cannot be empty.");
        }

        Task task;
        if (type.equals("todo")) {
            task = new ToDo(description);
        } else if (type.equals("deadline")) {
            String[] parts = description.split(" /by ");
            if (parts.length < 2) {
                throw new MaxException("Invalid deadline format. Use: deadline [description] /by [time]");
            }
            task = new Deadline(parts[0], parts[1]);
        } else if (type.equals("event")) {
            String[] parts = description.split(" /from ");
            if (parts.length < 2 || !parts[1].contains(" /to ")) {
                throw new MaxException("Invalid event format. Use: event [description] /from [start] /to [end]");
            }
            String[] timeParts = parts[1].split(" /to ");
            task = new Event(parts[0], timeParts[0], timeParts[1]);
        } else {
            throw new MaxException("Invalid task type!");
        }

        tasks.addTask(task);
        storage.save(tasks.getTasks());
        return "Got it. I've added this task:\n  " + task + "\nNow you have " + tasks.size() + " tasks in the list.";
    }
}
