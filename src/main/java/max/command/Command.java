package max.command;

import max.exception.MaxException;
import max.storage.Storage;
import max.task.*;
import max.ui.Ui;

public abstract class Command {
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws MaxException;

    public boolean isExit() {
        return false;
    }
}
