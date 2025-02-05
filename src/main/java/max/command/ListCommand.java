package max.command;

import max.storage.Storage;
import max.task.TaskList;
import max.ui.Ui;

/**
 * Represents a command to list all tasks.
 */
public class ListCommand extends Command {
    /**
     * Executes the list command, displaying all tasks in the task list.
     *
     * @param tasks   The task list containing all tasks.
     * @param ui      The user interface to display messages.
     * @param storage The storage handler (not used in this command).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.show("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            ui.show((i + 1) + ". " + tasks.getTask(i));
        }
    }
}
