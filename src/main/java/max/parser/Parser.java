package max.parser;

import max.command.*;
import max.exception.MaxException;

/**
 * Parses user input into commands that can be executed by the chatbot.
 */
public class Parser {

    /**
     * Parses user input and returns the corresponding Command object.
     *
     * @param fullCommand The full command entered by the user.
     * @return The corresponding Command object.
     * @throws MaxException If the command is invalid.
     */
    public static Command parse(String fullCommand) throws MaxException {
        String[] inputParts = fullCommand.split(" ", 2);
        String commandWord = inputParts[0];
        String arguments = inputParts.length > 1 ? inputParts[1].trim() : "";

        switch (commandWord.toLowerCase()) {
            case "list":
                return new ListCommand();
            case "todo":
                return new AddCommand("todo", arguments);
            case "deadline":
                return new AddCommand("deadline", arguments);
            case "event":
                return new AddCommand("event", arguments);
            case "mark":
                return new MarkCommand(true, Integer.parseInt(arguments));
            case "unmark":
                return new MarkCommand(false, Integer.parseInt(arguments));
            case "delete":
                return new DeleteCommand(Integer.parseInt(arguments));
            case "on":
                return new ShowCommand(arguments);
            case "bye":
                return new ExitCommand();
            default:
                throw new MaxException("Unknown command: " + commandWord);
        }
    }
}
