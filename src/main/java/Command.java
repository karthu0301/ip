public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws MaxException;
    public boolean isExit() {
        return false;
    }
}

class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.show("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            ui.show((i + 1) + ". " + tasks.getTask(i));
        }
    }
}

class AddCommand extends Command {
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

class MarkCommand extends Command {
    private final boolean status;
    private final int index;

    public MarkCommand(boolean status, int index) {
        this.status = status;
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MaxException {
        int markIndex = index - 1;
        if (markIndex < 0 || markIndex >= tasks.size()) {
            throw new MaxException("Invalid task number!");
        }

        if (status) {
            tasks.getTask(markIndex).markAsDone();
        } else {
            tasks.getTask(markIndex).markAsNotDone();
        }

        storage.save(tasks.getTasks());
    }
}


class DeleteCommand extends Command {
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
        ui.show("Noted. I've removed this task:\n  " + removedTask + "\nNow you have " + tasks.size() + " tasks in the list.");
    }
}


class ShowCommand extends Command {
    private final String date;

    public ShowCommand(String date) {
        this.date = date;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.show("Here are the tasks on " + date + ":");
        for (Task task : tasks.getTasks()) {
            if (task instanceof Deadline && ((Deadline) task).isOnDate(date)) {
                ui.show("  " + task);
            } else if (task instanceof Event && ((Event) task).isOnDate(date)) {
                ui.show("  " + task);
            }
        }
    }
}


class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.show("Bye! Will I see you again? :)");
    }

    @Override
    public boolean isExit() {
        return true;
    }
}


