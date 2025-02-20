package max.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import max.exception.MaxException;
import max.storage.Storage;
import max.task.Priority;
import max.task.TaskList;
import max.ui.Ui;

/**
 * Unit tests for the {@link AddCommand} class.
 */
public class AddCommandTest {
    private TaskList tasks;
    private Ui ui;
    private Storage storage;

    /**
     * Initializes a new task list, UI, and storage before each test case.
     */
    @BeforeEach
    void setUp() {
        tasks = new TaskList();
        ui = new Ui();
        storage = new Storage("test.txt");
    }

    /**
     * Tests that executing an {@code AddCommand} successfully adds a task.
     *
     * @throws Exception if an unexpected error occurs during execution.
     */
    @Test
    public void execute_success() throws Exception {
        AddCommand command = new AddCommand("todo", "read book", Priority.LOW);
        command.execute(tasks, ui, storage);
        assertEquals(1, tasks.size(), "Task list size should be 1 after adding a task.");
        assertEquals("[T][ ] read book", tasks.getTask(0).toString(), "Task description should match expected format.");
    }

    /**
     * Tests that executing an {@code AddCommand} with an empty description throws a {@code MaxException}.
     */
    @Test
    public void execute_exceptionThrown() {
        Exception exception = assertThrows(MaxException.class, () -> {
            AddCommand command = new AddCommand("todo", "", Priority.LOW); // Empty description should fail
            command.execute(tasks, ui, storage);
        });

        assertEquals("The description of a todo cannot be empty.", exception.getMessage(),
                "Exception message should indicate that a description is required.");
    }
}
