package max.command;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import max.storage.Storage;
import max.task.Task;
import max.task.TaskList;

/**
 * Represents a command to list all tasks.
 */
public class ListCommand extends Command {

    /**
     * Executes the list command by displaying all tasks sorted by priority.
     *
     * @param tasks   The task list to display.
     * @param storage The storage handler (not used here).
     * @return A formatted list of tasks.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        if (tasks.size() == 0) {
            return "Unfortunately sir/madam, just like your wallet, your task list is empty!";
        }

        List<Task> sortedTasks = tasks.getTasks().stream()
                .sorted((t1, t2) -> Integer.compare(t2.getPriority().getLevel(), t1.getPriority().getLevel()))
                .collect(Collectors.toList());

        return formatTaskList(sortedTasks);
    }

    /**
     * Formats the sorted task list into a numbered string.
     *
     * @param sortedTasks The sorted list of tasks.
     * @return The formatted list as a string.
     */
    private String formatTaskList(List<Task> sortedTasks) {
        return IntStream.range(0, sortedTasks.size())
                .mapToObj(i -> (i + 1) + ". " + sortedTasks.get(i))
                .collect(Collectors.joining("\n", "Certainly, here are the tasks in your list:\n", ""));
    }
}
