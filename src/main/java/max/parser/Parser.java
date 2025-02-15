package max.parser;

import max.command.*;
import max.exception.MaxException;
import max.task.Priority;

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
        String[] inputParts = fullCommand.split(" ", 2);
        String commandWord = inputParts[0];
        String arguments = inputParts.length > 1 ? inputParts[1] : "";

        switch (commandWord.toLowerCase()) {
        case "list":
            return new ListCommand();
        case "todo":
            return new AddCommand("todo", arguments, Priority.LOW);
        case "deadline":
            return new AddCommand("deadline", arguments, Priority.LOW);
        case "event":
            return new AddCommand("event", arguments, Priority.LOW);
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
        case "priority":
            if (inputParts.length < 3) {
                throw new MaxException("Priority command format: priority [task number] [low/medium/high]");
            }
            int taskIndex = Integer.parseInt(inputParts[1]);
            Priority priority = Priority.valueOf(inputParts[2].toUpperCase()); // Convert string to enum
            return new PriorityCommand(taskIndex, priority);
        default:
            throw new MaxException("Oh no! Unknown command! Did you mean 'todo', 'deadline', 'event', or 'find'?");
        }
    }
}
