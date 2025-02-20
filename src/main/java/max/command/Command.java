package max.command;

import max.exception.MaxException;
import max.storage.Storage;
import max.task.TaskList;

/**
 * Represents an abstract command that can be executed in the chatbot.
 * All specific commands (e.g., AddCommand, DeleteCommand) extend this class.
 */
public abstract class Command {
    /**
     * Executes the command.
     *
     * @param tasks   The task list to modify.
     * @param storage The storage handler for saving data.
     * @throws MaxException If the command encounters an error.
     */
    public abstract String execute(TaskList tasks, Storage storage) throws MaxException;
}
