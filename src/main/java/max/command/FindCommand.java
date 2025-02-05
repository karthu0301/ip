package max.command;

import max.storage.Storage;
import max.task.Task;
import max.task.TaskList;
import max.ui.Ui;

/**
 * Represents a command to find tasks that contain a specific keyword.
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Constructs a FindCommand with the specified keyword.
     *
     * @param keyword The keyword to search for in task descriptions.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword.toLowerCase(); // Ensure case-insensitive search
    }

    /**
     * Executes the find command, searching for tasks containing the keyword.
     *
     * @param tasks   The task list to search.
     * @param ui      The user interface to display messages.
     * @param storage The storage handler (not used in this command).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.show("Here are the matching tasks in your list:");
        int matchCount = 0;

        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.getTask(i);
            if (task.getDescription().toLowerCase().contains(keyword)) {
                ui.show((matchCount + 1) + ". " + task);
                matchCount++;
            }
        }

        if (matchCount == 0) {
            ui.show("No matching tasks found.");
        }
    }
}
