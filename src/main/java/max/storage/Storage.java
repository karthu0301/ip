package max.storage;

import max.exception.MaxException;
import max.task.Task;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles file storage operations for saving and loading tasks.
 */
public class Storage {
    private final String filePath;

    /**
     * Constructs a Storage object with the given file path.
     *
     * @param filePath The path to the file where tasks are stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the file.
     *
     * @return A list of tasks loaded from the file.
     * @throws MaxException If there is an error reading the file.
     */
    public List<Task> load() throws MaxException {
        List<Task> tasks = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                Files.createDirectories(Path.of(file.getParent()));
                file.createNewFile();
            } catch (IOException e) {
                throw new MaxException("Error creating file: " + filePath);
            }
        }

        try {
            List<String> lines = Files.readAllLines(Path.of(filePath));
            for (String line : lines) {
                tasks.add(Task.fromFileString(line));
            }
        } catch (IOException e) {
            throw new MaxException("Error reading file: " + filePath);
        }
        return tasks;
    }

    /**
     * Saves the current list of tasks to the file.
     *
     * @param tasks The list of tasks to be saved.
     * @throws MaxException If there is an error writing to the file.
     */
    public void save(List<Task> tasks) throws MaxException {
        try (FileWriter writer = new FileWriter(filePath)) {
            for (Task task : tasks) {
                writer.write(task.toFileString() + "\n");
            }
        } catch (IOException e) {
            throw new MaxException("Error saving tasks to file.");
        }
    }
}
