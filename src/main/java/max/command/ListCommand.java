package max.command;

import max.storage.Storage;
import max.task.Task;
import max.task.TaskList;
import max.ui.Ui;

import java.util.List;
import java.util.stream.Collectors;


public class ListCommand extends Command{
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        List<Task> sortedTasks = tasks.getTasks().stream()
                .sorted((t1, t2) -> Integer.compare(t2.getPriority().getLevel(), t1.getPriority().getLevel()))
                .collect(Collectors.toList());

        StringBuilder response = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < sortedTasks.size(); i++) {
            response.append((i + 1)).append(". ").append(tasks.getTask(i)).append("\n");
        }
        return response.toString();
    }
}
