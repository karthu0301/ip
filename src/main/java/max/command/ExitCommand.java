package max.command;

import max.storage.Storage;
import max.task.TaskList;
import max.ui.Ui;

public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.show("Bye! Will I see you again? :)");
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
