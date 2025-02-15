package max.command;

import max.exception.MaxException;
import max.storage.Storage;
import max.task.Priority;
import max.task.Task;
import max.task.TaskList;
import max.ui.Ui;

/**
 * Represents a command to update a task's priority.
 */
public class PriorityCommand extends Command {
    private final int index;
    private final Priority priority;


    /**
     * Initialises priority commmand.
     */
    public PriorityCommand(int index, Priority priority) {
        this.index = index;
        this.priority = priority;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws MaxException {
        int taskIndex = index - 1;
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new MaxException("Invalid task number!");
        }

        Task task = tasks.getTask(taskIndex);
        task.setPriority(priority);
        storage.save(tasks.getTasks());

        return "Updated priority for task:\n  " + task;
    }
}
