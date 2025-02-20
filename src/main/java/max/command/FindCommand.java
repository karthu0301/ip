package max.command;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import max.storage.Storage;
import max.task.Task;
import max.task.TaskList;


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
     * @param storage The storage handler (not used here).
     * @return The list of matching tasks.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        List<Task> matchingTasks = filterMatchingTasks(tasks);
        return matchingTasks.isEmpty()
                ? "Unfortunately, there were no matching tasks found for: " + keyword
                : formatTaskList(matchingTasks);
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


    private String formatTaskList(List<Task> tasks) {
        return IntStream.range(0, tasks.size())
                .mapToObj(i -> (i + 1) + ". " + tasks.get(i))
                .collect(Collectors.joining("\n", "Certainly, here are the matching tasks in your list:\n", ""));
    }
}

