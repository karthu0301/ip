package max.command;

import max.exception.MaxException;
import max.storage.Storage;
import max.task.TaskList;
import max.ui.Ui;

/**
 * Represents an abstract command that can be executed in the chatbot.
 * All specific commands (e.g., AddCommand, DeleteCommand) extend this class.
 */
public abstract class Command {
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws MaxException;


    public boolean isExit() {
        return false;
    }
}
