package max.parser;

import max.command.AddCommand;
import max.command.Command;
import max.command.DeleteCommand;
import max.command.ExitCommand;
import max.command.FindCommand;
import max.command.ListCommand;
import max.command.MarkCommand;
import max.command.ShowCommand;
import max.exception.MaxException;

/**
 * Parses user input and returns the corresponding command.
 */
public class Parser {
    /**
     * Parses the user command and returns the appropriate Command object.
     *
     * @param fullCommand The full user input.
     * @return The corresponding Command object.
     * @throws MaxException If the command is invalid.
     */
    public static Command parse(String fullCommand) throws MaxException {
        assert fullCommand != null && !fullCommand.trim().isEmpty() : "Input command should not be null or empty";
        String[] inputParts = fullCommand.split(" ", 2);
        String commandWord = inputParts[0];
        String arguments = inputParts.length > 1 ? inputParts[1] : "";

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
        case "find":
            if (arguments.trim().isEmpty()) {
                throw new MaxException("Please provide a keyword to search.");
            }
            return new FindCommand(arguments);
        case "bye":
            return new ExitCommand();
        default:
            throw new MaxException("Oh no! Unknown command! Did you mean 'todo', 'deadline', 'event', or 'find'?");
        }
    }
}
