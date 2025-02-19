package max.parser;

import max.command.AddCommand;
import max.command.Command;
import max.command.DeleteCommand;
import max.command.ExitCommand;
import max.command.FindCommand;
import max.command.ListCommand;
import max.command.MarkCommand;
import max.command.PriorityCommand;
import max.command.ShowCommand;
import max.exception.MaxException;
import max.task.Priority;

/**
 * Parses user input and returns the corresponding command.
 */
public class Parser {
    private static final String CMD_LIST = "list";
    private static final String CMD_TODO = "todo";
    private static final String CMD_DEADLINE = "deadline";
    private static final String CMD_EVENT = "event";
    private static final String CMD_MARK = "mark";
    private static final String CMD_UNMARK = "unmark";
    private static final String CMD_DELETE = "delete";
    private static final String CMD_ON = "on";
    private static final String CMD_FIND = "find";
    private static final String CMD_BYE = "bye";
    private static final String CMD_PRIORITY = "priority";

    /**
     * Parses the user command and returns the appropriate Command object.
     *
     * @param fullCommand The full user input.
     * @return The corresponding Command object.
     * @throws MaxException If the command is invalid or missing arguments.
     */
    public static Command parse(String fullCommand) throws MaxException {
        assert fullCommand != null && !fullCommand.trim().isEmpty() : "Input command should not be null or empty";
        String[] inputParts = fullCommand.split(" ", 2);
        String commandWord = inputParts[0];
        String arguments = inputParts.length > 1 ? inputParts[1] : "";

        switch (commandWord.toLowerCase()) {
        case CMD_LIST:
            return new ListCommand();
        case CMD_TODO:
        case CMD_DEADLINE:
        case CMD_EVENT:
            return new AddCommand(commandWord, arguments, Priority.LOW);
        case CMD_MARK:
        case CMD_UNMARK:
        case CMD_DELETE:
            try {
                int index = Integer.parseInt(arguments);
                return commandWord.equals(CMD_MARK) ? new MarkCommand(true, index)
                        : commandWord.equals(CMD_UNMARK) ? new MarkCommand(false, index)
                        : new DeleteCommand(index);
            } catch (NumberFormatException e) {
                throw new MaxException("Please enter a valid task number.");
            }
        case CMD_ON:
            return new ShowCommand(arguments);
        case CMD_FIND:
            if (arguments.trim().isEmpty()) {
                throw new MaxException("Please provide a keyword to search.");
            }
            return new FindCommand(arguments);
        case CMD_BYE:
            return new ExitCommand();
        case CMD_PRIORITY:
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
