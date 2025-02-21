package max.command;

import max.exception.MaxException;
import max.storage.Storage;
import max.task.Task;
import max.task.TaskList;

/**
 * Represents a command to mark or unmark a task as done.
 */
public class MarkCommand extends Command {
    private final boolean isMarking;
    private final int taskIndex;

    /**
     * Creates a MarkCommand to either mark or unmark a task.
     *
     * @param isMarking Whether to mark (true) or unmark (false) the task.
     * @param index     The 1-based index of the task.
     */
    public MarkCommand(boolean isMarking, int index) {
        this.isMarking = isMarking;
        this.taskIndex = index - 1; // Convert to 0-based index
    }

    /**
     * Executes the mark/unmark command.
     *
     * @param tasks   The task list.
     * @param storage The storage handler.
     * @return A message confirming the action.
     * @throws MaxException If the index is invalid.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws MaxException {
        validateIndex(tasks);
        return toggleTaskCompletion(tasks, storage);
    }

    /**
     * Validates that the task index is within a valid range.
     *
     * @param tasks The task list.
     * @throws MaxException If the index is out of bounds.
     */
    private void validateIndex(TaskList tasks) throws MaxException {
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            throw new MaxException("Invalid task number does not a good task make!");
        }
    }

    /**
     * Marks or unmarks a task and returns a confirmation message.
     *
     * @param tasks   The task list.
     * @param storage The storage handler.
     * @return A confirmation message.
     */
    private String toggleTaskCompletion(TaskList tasks, Storage storage) throws MaxException {
        Task task = tasks.getTask(taskIndex);
        if (isMarking) {
            task.markAsDone();
        } else {
            task.markAsNotDone();
        }

        storage.save(tasks.getTasks());
        return (isMarking
                ? "Beautiful! I always knew you could do this!:\n  "
                : "Very good, sir! Remember, it's never too late to carry out your duties!:\n  ") + task;
    }
}
