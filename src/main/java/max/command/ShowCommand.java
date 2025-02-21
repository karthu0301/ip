package max.command;

import max.storage.Storage;
import max.task.Deadline;
import max.task.Event;
import max.task.Task;
import max.task.TaskList;

/**
 * Represents a command to display tasks occurring on a specific date.
 */
public class ShowCommand extends Command {
    private final String date;

    /**
     * Constructs a ShowCommand to filter and display tasks on a given date.
     *
     * @param date The date for which tasks should be displayed.
     */
    public ShowCommand(String date) {
        this.date = date;
    }

    /**
     * Executes the show command, displaying all tasks that occur on the specified date.
     *
     * @param tasks   The task list containing all tasks.
     * @param storage The storage handler (not used in this command).
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        StringBuilder response = new StringBuilder("Here are the tasks on " + date + ":\n");
        boolean found = false;

        for (Task task : tasks.getTasks()) {
            if (task instanceof Deadline && ((Deadline) task).isOnDate(date)) {
                response.append("  ").append(task).append("\n");
                found = true;
            } else if (task instanceof Event && ((Event) task).isOnDate(date)) {
                response.append("  ").append(task).append("\n");
                found = true;
            }
        }

        if (!found) {
            response.append("Alas, there are no tasks scheduled on this particular date. "
                    +
                    "What a splendid day for a bit of leisure!");
        }

        return response.toString();
    }

}
