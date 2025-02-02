package max.command;

import max.exception.MaxException;
import max.storage.Storage;
import max.task.TaskList;
import max.ui.Ui;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AddCommandTest {
    private TaskList tasks;
    private Ui ui;
    private Storage storage;

    @BeforeEach
    void setUp() {
        tasks = new TaskList();
        ui = new Ui();
        storage = new Storage("test.txt");  // Use a test file to avoid modifying real data
    }

    @Test
    public void execute_success() throws Exception {
        AddCommand command = new AddCommand("todo", "read book");
        command.execute(tasks, ui, storage);
        assertEquals(1, tasks.size());  // Ensure task is added
        assertEquals("[T][ ] read book", tasks.getTask(0).toString());
    }

    @Test
    public void execute_exceptionThrown() {
        Exception exception = assertThrows(MaxException.class, () -> {
            AddCommand command = new AddCommand("todo", ""); // Empty description should fail
            command.execute(tasks, ui, storage);
        });

        assertEquals("The description of a todo cannot be empty.", exception.getMessage());
    }
}
