package max.command;

import max.storage.Storage;
import max.task.TaskList;
import max.ui.Ui;

public class ExitCommand extends Command {
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return "Bye! Will I see you again? :)";
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
