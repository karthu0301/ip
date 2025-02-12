package max.command;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
        this.keyword = keyword;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        List<Task> matchingTasks = tasks.getTasks().stream()
                .filter(task -> task.getDescription().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());

        if (matchingTasks.isEmpty()) {
            return "No matching tasks found for: " + keyword;
        }

        return formatTaskList(matchingTasks);
    }

    private String formatTaskList(List<Task> tasks) {
        return IntStream.range(0, tasks.size())
                .mapToObj(i -> (i + 1) + ". " + tasks.get(i))
                .collect(Collectors.joining("\n", "Here are the matching tasks in your list:\n", ""));
    }
}

