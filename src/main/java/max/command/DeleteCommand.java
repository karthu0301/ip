package max.command;

import max.exception.MaxException;
import max.storage.Storage;
import max.task.Task;
import max.task.TaskList;

/**
 * Represents a command to delete a task.
 */
public class DeleteCommand extends Command {
    private final int taskIndex;

    /**
     * Constructs a DeleteCommand.
     *
     * @param taskIndex The 1-based index of the task to delete.
     */
    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex - 1; // Convert to 0-based index
    }

    /**
     * Executes the delete command by removing a task from the task list.
     *
     * @param tasks   The task list to modify.\
     * @param storage The storage handler for saving changes.
     * @return A message confirming task deletion.
     * @throws MaxException If the index is invalid.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws MaxException {
        validateIndex(tasks);
        return removeTask(tasks, storage);
    }

    /**
     * Validates that the task index is within the valid range.
     *
     * @param tasks The task list.
     * @throws MaxException If the index is out of bounds.
     */
    private void validateIndex(TaskList tasks) throws MaxException {
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new MaxException("Dear sir, an invalid task number!");
        }
    }

    /**
     * Removes the task, updates storage, and returns a confirmation message.
     *
     * @param tasks   The task list.
     * @param storage The storage handler.
     * @return A confirmation message.
     */
    private String removeTask(TaskList tasks, Storage storage) throws MaxException {
        if (taskIndex == Integer.parseInt(null)) {
            throw new MaxException("Though your life may be emoty, the index of the task to be deleted may not be.");
        }
        Task removedTask = tasks.deleteTask(taskIndex);
        storage.save(tasks.getTasks());
        return "As you wish. Your request has been fulfilled. I've removed this task:\n  " + removedTask
                + "\nNow you have " + tasks.size() + " tasks in the list.";
    }
}
