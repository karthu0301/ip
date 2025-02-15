package max.command;

import max.storage.Storage;
import max.task.TaskList;
import max.ui.Ui;

/**
 * Represents a command to list all tasks.
 */
public class ListCommand extends Command {

    /**
     * Executes the list command by displaying all tasks.
     *
     * @param tasks   The task list to display.
     * @param ui      The user interface for displaying messages.
     * @param storage The storage handler (not used here).
     * @return A formatted list of tasks.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return tasks.getTasks() == null ? "You have no tasks in your list!" : formatTaskList(tasks);
    }

    /**
     * Formats the task list into a numbered string.
     *
     * @param tasks The task list.
     * @return The formatted list as a string.
     */
    private String formatTaskList(TaskList tasks) {
        StringBuilder response = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            response.append(i + 1).append(". ").append(tasks.getTask(i)).append("\n");
        }
        return response.toString();
    }
}
