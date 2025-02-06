package max.command;

import max.exception.MaxException;
import max.storage.Storage;
import max.task.TaskList;
import max.ui.Ui;

public class MarkCommand extends Command {
    private final boolean status;
    private final int index;

    public MarkCommand(boolean status, int index) {
        this.status = status;
        this.index = index;
    }


    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws MaxException {
        int markIndex = index - 1;
        if (markIndex < 0 || markIndex >= tasks.size()) {
            throw new MaxException("Invalid task number!");
        }
        tasks.getTask(markIndex).markAsDone();
        storage.save(tasks.getTasks());
        return "Nice! I've marked this task as done:\n  " + tasks.getTask(markIndex);
    }
}
