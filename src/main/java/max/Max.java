package max;

import max.exception.MaxException;
import max.parser.Parser;
import max.storage.Storage;
import max.task.TaskList;
import max.ui.Ui;


/**
 * Represents the main chatbot class that handles the initialization of
 * components such as storage, task list, and UI, and provides responses to user input.

 */
public class Max {
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    /**
     * Constructs a {@code Max} instance.
     * Initializes the {@code Storage}, {@code TaskList}, and {@code Ui} components.
     * If loading tasks from storage fails, it initializes with an empty task list
     * and displays a loading error message.
     */
    public Max() {
        this.ui = new Ui();
        ui.showWelcome();
        this.storage = new Storage("data/tasks.txt");
        try {
            this.tasks = new TaskList(storage.load());
        } catch (MaxException e) {
            ui.showLoadingError();
            this.tasks = new TaskList();
        }
    }

    /**
     * Gets the chatbot's response to user input.
     * @param input User's command.
     * @return Chatbot's response.
     */
    public String getResponse(String input) {
        String response;
        try {
            response = Parser.parse(input).execute(tasks, ui, storage);
        } catch (MaxException e) {
            response = "Error: " + e.getMessage();
        }
        assert response != null : "Chatbot response should never be null";
        return response;
    }

}
