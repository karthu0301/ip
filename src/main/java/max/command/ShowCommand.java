package max.command;

import max.storage.Storage;
import max.task.Deadline;
import max.task.Event;
import max.task.Task;
import max.task.TaskList;
import max.ui.Ui;

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
     * @param ui      The user interface to display messages.
     * @param storage The storage handler (not used in this command).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.show("Here are the tasks on " + date + ":");
        for (Task task : tasks.getTasks()) {
            if (task instanceof Deadline && ((Deadline) task).isOnDate(date)) {
                ui.show("  " + task);
            } else if (task instanceof Event && ((Event) task).isOnDate(date)) {
                ui.show("  " + task);
            }
        }
    }
}
