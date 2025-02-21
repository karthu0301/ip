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

        List<Task> originalOrderTasks = tasks.getTasks();

        return formatTaskList(originalOrderTasks);
    }

    /**
     * Formats the sorted task list into a numbered string.
     *
     * @param originalOrderTasks The sorted list of tasks.
     * @return The formatted list as a string.
     */
    private String formatTaskList(List<Task> originalOrderTasks) {
        return IntStream.range(0, originalOrderTasks.size())
                .mapToObj(i -> (i + 1) + ". " + originalOrderTasks.get(i))
                .collect(Collectors.joining("\n", "Certainly, here are the tasks in your list. "
                        +
                        "They show the type, priority, description and duration of your tasks!\n", ""));
    }
}
