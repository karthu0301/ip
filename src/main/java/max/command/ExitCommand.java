package max.command;

import max.storage.Storage;
import max.task.TaskList;

/**
 * Represents a command to exit the chatbot.
 */
public class ExitCommand extends Command {
    /**
     * Executes the exit command.
     *
     * @param tasks   The task list (not used).
     * @param storage The storage handler (not used).
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        return "Good night, esteemed sir/madam. Might I suggest a moment of rest after your work?";
    }
}
