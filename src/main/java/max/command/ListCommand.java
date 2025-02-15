package max.command;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
        if (tasks.size() == 0) {
            return "Your task list is empty!";
        return tasks.getTasks() == null ? "You have no tasks in your list!" : formatTaskList(tasks);
    }

    /**
     * Formats the task list into a numbered string.
     *
     * @param tasks The task list.
     * @return The formatted list as a string.
     */
    private String formatTaskList(TaskList tasks) {
        return IntStream.range(0, tasks.size())
                .mapToObj(i -> (i + 1) + ". " + tasks.getTask(i))
                .collect(Collectors.joining("\n", "Here are the tasks in your list:\n", ""));
    }
}
