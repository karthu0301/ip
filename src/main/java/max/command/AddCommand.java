package max.command;

import max.exception.MaxException;
import max.storage.Storage;
import max.task.*;
import max.ui.Ui;

public class AddCommand extends Command {
    private final String type;
    private final String description;

    public AddCommand(String type, String description) {
        this.type = type;
        this.description = description;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MaxException {
        Task task;
        if (type.equals("todo")) {
            task = new ToDo(description);
        } else if (type.equals("deadline")) {
            String[] parts = description.split(" /by ");
            task = new Deadline(parts[0], parts[1]);
        } else if (type.equals("event")) {
            String[] parts = description.split(" /from ");
            String[] timeParts = parts[1].split(" /to ");
            task = new Event(parts[0], timeParts[0], timeParts[1]);
        } else {
            throw new MaxException("Invalid task type!");
        }
        tasks.addTask(task);
        storage.save(tasks.getTasks());
        ui.show("Got it. I've added this task:\n  " + task + "\nNow you have " + tasks.size() + " tasks in the list.");
    }
}
