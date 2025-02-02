package max.parser;

import max.command.*;
import max.exception.MaxException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {

    @Test
    public void parse_success() throws Exception {
        Command command = Parser.parse("todo read book");
        assertTrue(command instanceof AddCommand);

        command = Parser.parse("list");
        assertTrue(command instanceof ListCommand);

        command = Parser.parse("bye");
        assertTrue(command instanceof ExitCommand);
    }

    @Test
    public void parse_exceptionThrown() {
        Exception exception = assertThrows(MaxException.class, () -> {
            Parser.parse("invalidCommand");
        });

        assertEquals("Unknown command: invalidCommand", exception.getMessage());
    }
}
