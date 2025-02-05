package max;

import max.command.Command;
import max.exception.MaxException;
import max.parser.Parser;
import max.storage.Storage;
import max.task.TaskList;
import max.ui.Ui;
/**
 * The main class for the Max chatbot application.
 * It initializes the chatbot, loads data, and processes user commands.
 */
public class Max {
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    /**
     * Initializes the Max chatbot.
     *
     * @param filePath The file path where tasks are stored.
     */
    public Max(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (MaxException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Starts the chatbot and continuously processes user input.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command command = Parser.parse(fullCommand);
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (MaxException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    /**
     * Main method to start the chatbot.
     *
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        new Max("data/tasks.txt").run();
    }
}
