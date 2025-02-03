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
    /**
     * Executes the command.
     *
     * @param tasks   The task list to modify.
     * @param ui      The user interface to display messages.
     * @param storage The storage handler for saving/loading tasks.
     * @throws MaxException If an error occurs during execution.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws MaxException;

    /**
     * Determines if this command signals the chatbot to exit.
     *
     * @return `true` if the chatbot should exit, `false` otherwise.
     */
    public boolean isExit() {
        return false;
    }
}
