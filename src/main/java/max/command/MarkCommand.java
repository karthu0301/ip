package max.command;

import max.exception.MaxException;
import max.storage.Storage;
import max.task.TaskList;
import max.ui.Ui;

public class MarkCommand extends Command {
    private final boolean status;
    private final int index;

    /**
     * Represents a command to mark or unmark a task as completed.
     */
    public MarkCommand(boolean status, int index) {
        this.status = status;
        this.index = index;
    }

    /**
     * Executes the mark/unmark command.
     *
     * @param tasks   The task list to modify.
     * @param ui      The user interface to display messages.
     * @param storage The storage handler for saving changes.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MaxException {
        int markIndex = index - 1;
        if (markIndex < 0 || markIndex >= tasks.size()) {
            throw new MaxException("Invalid task number!");
        }

        if (status) {
            tasks.getTask(markIndex).markAsDone();
        } else {
            tasks.getTask(markIndex).markAsNotDone();
        }

        storage.save(tasks.getTasks());
    }
}
