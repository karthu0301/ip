package max.command;

import java.util.List;
import java.util.stream.Collectors;

import max.storage.Storage;
import max.task.Task;
import max.task.TaskList;
import max.ui.Ui;



/**
 * Represents a command to find tasks containing a specific keyword.
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Creates a FindCommand with the specified keyword.
     *
     * @param keyword The keyword to search for in task descriptions.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword.trim();
    }

    /**
     * Executes the find command.
     *
     * @param tasks   The task list to search.
     * @param ui      The user interface for displaying messages.
     * @param storage The storage handler (not used here).
     * @return The list of matching tasks.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        List<Task> matchingTasks = filterMatchingTasks(tasks);

        return matchingTasks.isEmpty()
                ? "No matching tasks found for: " + keyword
                : formatMatchingTasks(matchingTasks);
    }

    /**
     * Filters tasks that contain the keyword.
     *
     * @param tasks The task list.
     * @return A list of matching tasks.
     */
    private List<Task> filterMatchingTasks(TaskList tasks) {
        return tasks.getTasks().stream()
                .filter(task -> task.getDescription().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
    }

    /**
     * Formats the matching tasks into a readable list.
     *
     * @param matchingTasks The list of tasks that match the keyword.
     * @return A formatted string of matching tasks.
     */
    private String formatMatchingTasks(List<Task> matchingTasks) {
        StringBuilder response = new StringBuilder("Here are the matching tasks:\n");
        for (int i = 0; i < matchingTasks.size(); i++) {
            response.append(i + 1).append(". ").append(matchingTasks.get(i)).append("\n");
        }
        return response.toString();
    }
}
