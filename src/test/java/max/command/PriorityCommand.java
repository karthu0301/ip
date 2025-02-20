package max.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import max.exception.MaxException;
import max.storage.Storage;
import max.task.Priority;
import max.task.TaskList;
import max.task.ToDo;
import max.ui.Ui;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
/**
 * Tests for PriorityCommand.
 */
public class PriorityCommandTest {
    private TaskList taskList;
    private Ui ui;
    private Storage storage;
    private File testFile;

    @BeforeEach
    public void setUp() throws IOException {
        taskList = new TaskList();
        taskList.addTask(new ToDo("Finish homework"));
        taskList.addTask(new ToDo("Buy groceries"));

        ui = new Ui();

        testFile = File.createTempFile("test", ".txt");
        storage = new Storage(testFile.getAbsolutePath());
    }

    @Test
    public void execute_validIndex_updatesPriority() throws MaxException {
        PriorityCommand command = new PriorityCommand(1, Priority.HIGH);
        String response = command.execute(taskList, ui, storage);

        // Ensure the priority is updated
        assertEquals(Priority.HIGH, taskList.getTask(0).getPriority());
        assertTrue(response.contains("Updated priority for task"));

        // Ensure the task list is saved properly
        assertTrue(testFile.exists() && testFile.length() > 0);
    }

    @Test
    public void execute_invalidIndex_throwsException() {
        PriorityCommand command = new PriorityCommand(5, Priority.MEDIUM);
        Exception exception = assertThrows(MaxException.class, () -> command.execute(taskList, ui, storage));

        assertEquals("Invalid task number!", exception.getMessage());
    }

    @Test
    public void execute_negativeIndex_throwsException() {
        PriorityCommand command = new PriorityCommand(-1, Priority.LOW);
        Exception exception = assertThrows(MaxException.class, () -> command.execute(taskList, ui, storage));

        assertEquals("Invalid task number!", exception.getMessage());
    }
}
