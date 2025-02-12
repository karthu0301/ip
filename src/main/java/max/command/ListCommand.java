package max.command;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

import max.storage.Storage;
import max.task.TaskList;
import max.ui.Ui;




public class ListCommand extends Command {
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.size() == 0) {
            return "Your task list is empty!";
        }

        return IntStream.range(0, tasks.size())
                .mapToObj(i -> (i + 1) + ". " + tasks.getTask(i))
                .collect(Collectors.joining("\n", "Here are the tasks in your list:\n", ""));
    }
}
