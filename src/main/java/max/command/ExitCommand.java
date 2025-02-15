package max.command;

import max.storage.Storage;
import max.task.TaskList;
import max.ui.Ui;

/**
 * Represents a command to exit the chatbot.
 */
public class ExitCommand extends Command {
    /**
     * Executes the exit command.
     *
     * @param tasks   The task list (not used).
     * @param ui      The user interface to display messages.
     * @param storage The storage handler (not used).
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return "Bye! Will I see you again? :)";
    }
}
