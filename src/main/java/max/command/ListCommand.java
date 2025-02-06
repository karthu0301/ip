package max.command;

import max.storage.Storage;
import max.task.TaskList;
import max.ui.Ui;


public class ListCommand extends Command{
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        StringBuilder response = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            response.append((i + 1)).append(". ").append(tasks.getTask(i)).append("\n");
        }
        return response.toString();
    }
}
