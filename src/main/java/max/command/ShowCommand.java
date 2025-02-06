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
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        StringBuilder response = new StringBuilder("Here are the tasks on " + date + ":\n");
        for (Task task : tasks.getTasks()) {
            if (task instanceof Deadline && ((Deadline) task).isOnDate(date)) {
                response.append("  ").append(task).append("\n");
            } else if (task instanceof Event && ((Event) task).isOnDate(date)) {
                response.append("  ").append(task).append("\n");
            }
        }
        return response.toString();
    }
}
