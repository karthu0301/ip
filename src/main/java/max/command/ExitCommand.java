package max.command;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.util.Duration;
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
        String farewellMessage = "Good night, esteemed sir/madam. Might I suggest a moment of rest after your work?";

        PauseTransition delay = new PauseTransition(Duration.seconds(3));
        delay.setOnFinished(event -> Platform.exit());
        delay.play();

        return farewellMessage;
    }
}
