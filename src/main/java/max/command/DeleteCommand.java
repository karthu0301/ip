package max.command;

import max.exception.MaxException;
import max.storage.Storage;
import max.task.Task;
import max.task.TaskList;
import max.ui.Ui;

/**
 * Represents a command to delete a task.
 */
public class DeleteCommand extends Command {
    private final int index;

    /**
     * Constructs a DeleteCommand to remove a task.
     *
     * @param index The task index (1-based).
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the delete command.
     *
     * @param tasks   The task list to modify.
     * @param ui      The user interface to display messages.
     * @param storage The storage handler for saving changes.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MaxException {
        int deleteIndex = index - 1;
        if (deleteIndex < 0 || deleteIndex >= tasks.size()) {
            throw new MaxException("Invalid task number!");
        }

        Task removedTask = tasks.deleteTask(deleteIndex);
        storage.save(tasks.getTasks());
        ui.show("Noted. I've removed this task:\n  " + removedTask + "\nNow you have " + tasks.size() +
                " tasks in the list.");
    }
}
