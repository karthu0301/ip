package max.command;

import max.storage.Storage;
import max.task.Deadline;
import max.task.Event;
import max.task.Task;
import max.task.TaskList;
import max.ui.Ui;

public class ShowCommand extends Command {
    private final String date;

    public ShowCommand(String date) {
        this.date = date;
    }

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
