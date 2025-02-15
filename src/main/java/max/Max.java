package max;

import max.exception.MaxException;
import max.parser.Parser;
import max.storage.Storage;
import max.task.TaskList;
import max.ui.Ui;


/**
 * Constructs a {@code Max} instance and initializes required components.
 */
public class Max {
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    /**
     * Constructs a {@code Max} instance.
     * Initializes the {@code Storage}, {@code TaskList}, and {@code Ui} components.
     */
    public Max() {
        this.ui = new Ui();
        this.storage = new Storage("data/tasks.txt");
        this.tasks = loadTasks();
    }

    /**
     * Loads tasks from storage. If loading fails, initializes an empty task list
     * and displays an error message.
     */
    private TaskList loadTasks() {
        try {
            return new TaskList(storage.load());
        } catch (MaxException e) {
            ui.showLoadingError();
            return new TaskList();
        }
    }

    /**
     * Gets the chatbot's response to user input.
     * @param input User's command.
     * @return Chatbot's response.
     */
    public String getResponse(String input) {
        assert input != null : "User input should not be null";
        String response;
        try {
            response = Parser.parse(input).execute(tasks, ui, storage);
            assert response != null : "Response should not be null";
        } catch (MaxException e) {
            return "Oops! Something went wrong: " + e.getMessage();
        }
        assert response != null : "Chatbot response should never be null";
        return response;
    }

}
