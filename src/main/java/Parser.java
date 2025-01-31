public class Parser {

    public static Command parse(String fullCommand) throws MaxException {
        String[] inputParts = fullCommand.split(" ", 2);
        String commandWord = inputParts[0];
        String arguments = inputParts.length > 1 ? inputParts[1].trim() : "";

        switch (commandWord.toLowerCase()) {
            case "list":
                return new ListCommand();

            case "todo":
                if (arguments.isEmpty()) {
                    throw new MaxException("The description of a todo cannot be empty.");
                }
                return new AddCommand("todo", arguments);

            case "deadline":
                if (!arguments.contains("/by")) {
                    throw new MaxException("Invalid deadline format! Use: deadline <description> /by <date-time>");
                }
                return new AddCommand("deadline", arguments);

            case "event":
                if (!arguments.contains("/from") || !arguments.contains("/to")) {
                    throw new MaxException("Invalid event format! Use: event <description> /from <start> /to <end>");
                }
                return new AddCommand("event", arguments);

            case "mark":
                if (arguments.isEmpty()) {
                    throw new MaxException("Please specify the task number to mark.");
                }
                try {
                    int index = Integer.parseInt(arguments);
                    return new MarkCommand(true, index);
                } catch (NumberFormatException e) {
                    throw new MaxException("Invalid task number for mark command.");
                }

            case "unmark":
                if (arguments.isEmpty()) {
                    throw new MaxException("Please specify the task number to unmark.");
                }
                try {
                    int index = Integer.parseInt(arguments);
                    return new MarkCommand(false, index);
                } catch (NumberFormatException e) {
                    throw new MaxException("Invalid task number for unmark command.");
                }

            case "delete":
                if (arguments.isEmpty()) {
                    throw new MaxException("Please specify the task number to delete.");
                }
                try {
                    int index = Integer.parseInt(arguments);
                    return new DeleteCommand(index);
                } catch (NumberFormatException e) {
                    throw new MaxException("Invalid task number for delete command.");
                }

            case "on":
                if (arguments.isEmpty()) {
                    throw new MaxException("Please specify a date to filter tasks.");
                }
                return new ShowCommand(arguments);

            case "bye":
                return new ExitCommand();

            default:
                throw new MaxException("Unknown command: " + commandWord);
        }
    }
}
