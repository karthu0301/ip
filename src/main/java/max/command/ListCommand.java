package max.command;

import max.storage.Storage;
import max.task.TaskList;
import max.ui.Ui;

public class ListCommand extends Command{
        @Override
        public void execute(TaskList tasks, Ui ui, Storage storage) {
            ui.show("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                ui.show((i + 1) + ". " + tasks.getTask(i));
            }
        }
}
