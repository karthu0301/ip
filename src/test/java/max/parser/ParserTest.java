package max.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import max.command.AddCommand;
import max.command.Command;
import max.command.ExitCommand;
import max.command.ListCommand;
import max.exception.MaxException;

/**
 * Unit tests for the {@link Parser} class.
 */
public class ParserTest {

    /**
     * Tests that valid commands are correctly parsed into the corresponding {@code Command} objects.
     *
     * @throws Exception if an unexpected error occurs during parsing.
     */
    @Test
    public void parse_success() throws Exception {
        Command command = Parser.parse("todo read book");
        assertTrue(command instanceof AddCommand, "Expected command to be an instance of AddCommand.");

        command = Parser.parse("list");
        assertTrue(command instanceof ListCommand, "Expected command to be an instance of ListCommand.");

        command = Parser.parse("bye");
        assertTrue(command instanceof ExitCommand, "Expected command to be an instance of ExitCommand.");
    }

    /**
     * Tests that an invalid command throws a {@code MaxException} with the appropriate error message.
     */
    @Test
    public void parse_exceptionThrown() {
        Exception exception = assertThrows(MaxException.class, () -> {
            Parser.parse("invalidCommand");
        });
        assertEquals("What a strange command! Did you mean 'todo', 'deadline', 'event', or 'find'?",

                exception.getMessage(),
                "Exception message should guide the user toward valid commands.");
    }
}
