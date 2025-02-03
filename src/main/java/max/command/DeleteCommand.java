package max.command;

import max.exception.MaxException;
import max.storage.Storage;
import max.task.Task;
import max.task.TaskList;
import max.ui.Ui;

public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MaxException {
        int deleteIndex = index - 1;
        if (deleteIndex < 0 || deleteIndex >= tasks.size()) {
            throw new MaxException("Invalid task number!");
        }

        Task removedTask = tasks.deleteTask(deleteIndex);
        storage.save(tasks.getTasks());
        ui.show("Noted. I've removed this task:\n  " + removedTask + "\nNow you have " + tasks.size() +
                " tasks in the list.");
    }
}
