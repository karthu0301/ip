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
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.show("Bye! Will I see you again? :)");
    }

    /**
     * Indicates that this command causes the chatbot to exit.
     *
     * @return `true`, since this is an exit command.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
