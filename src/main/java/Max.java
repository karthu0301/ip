import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Max {
    private static final String FILE_PATH = "src/main/java/max.txt";
    enum Command {
        LIST, TODO, DEADLINE, EVENT, MARK, UNMARK, DELETE, BYE, UNKNOWN
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> tasks = loadTasksFromFile();

        System.out.println("Hello I'm Max!\n It's so nice to meet you!!\n");

        while (true) {
            try {
                if (!scanner.hasNextLine()) {
                    break;
                }
                String input = scanner.nextLine();
                String[] inputParts = input.split(" ", 2);

                Command command;
                try {
                    command = Command.valueOf(inputParts[0].toUpperCase());
                } catch (IllegalArgumentException e) {
                    command = Command.UNKNOWN;
                }

                switch (command) {
                    case LIST:
                        System.out.println("Here are the tasks in your list:");
                        for (int j = 0; j < tasks.size(); j++) {
                            System.out.print(" " + (j + 1) + ". " + tasks.get(j).toString() + "\n");
                        }
                        break;

                    case TODO:
                        if (inputParts.length < 2 || inputParts[1].trim().isEmpty()) {
                            throw new MaxException("The description of a todo cannot be empty.");
                        }
                        Task todoTask = new ToDo(inputParts[1]);
                        tasks.add(todoTask);
                        saveTasksToFile(tasks);
                        System.out.println("Got it. I've added this task:\n  " + todoTask + "\n" +
                                "Now you have " + tasks.size() + " tasks in the list.");
                        break;

                    case DEADLINE:
                        if (inputParts.length < 2 || !inputParts[1].contains("/by")) {
                            throw new MaxException("Invalid deadline format. Please use: deadline [description] /by [time]");
                        }
                        String[] deadlineParts = inputParts[1].split("/by", 2);
                        Task deadlineTask = new Deadline(deadlineParts[0].trim(), deadlineParts[1].trim());
                        tasks.add(deadlineTask);
                        saveTasksToFile(tasks);
                        System.out.println("Got it. I've added this task:\n  " + deadlineTask + "\n" +
                                "Now you have " + tasks.size() + " tasks in the list.");
                        break;

                    case EVENT:
                        if (inputParts.length < 2 || !inputParts[1].contains("/from") || !inputParts[1].contains("/to")) {
                            throw new MaxException("Invalid event format. Please use: event [description] /from [start] /to [end]");
                        }
                        String[] eventParts = inputParts[1].split("/from", 2);
                        String[] eventTimes = eventParts[1].split("/to", 2);
                        Task eventTask = new Event(eventParts[0].trim(), eventTimes[0].trim(), eventTimes[1].trim());
                        tasks.add(eventTask);
                        saveTasksToFile(tasks);
                        System.out.println("Got it. I've added this task:\n  " + eventTask + "\n" +
                                "Now you have " + tasks.size() + " tasks in the list.");
                        break;

                    case MARK:
                        if (inputParts.length < 2) {
                            throw new MaxException("Please specify the task number to mark as done.");
                        }
                        int markIndex = Integer.parseInt(inputParts[1]) - 1;
                        if (markIndex < 0 || markIndex >= tasks.size()) {
                            throw new MaxException("Invalid task number.");
                        }
                        tasks.get(markIndex).markAsDone();
                        saveTasksToFile(tasks);
                        System.out.println("Nice! I've marked this task as done:\n  " + tasks.get(markIndex));
                        break;

                    case UNMARK:
                        if (inputParts.length < 2) {
                            throw new MaxException("Please specify the task number to unmark.");
                        }
                        int unmarkIndex = Integer.parseInt(inputParts[1]) - 1;
                        if (unmarkIndex < 0 || unmarkIndex >= tasks.size()) {
                            throw new MaxException("Invalid task number.");
                        }
                        tasks.get(unmarkIndex).markAsNotDone();
                        saveTasksToFile(tasks);
                        System.out.println("OK, I've unmarked this task:\n  " + tasks.get(unmarkIndex));
                        break;

                    case DELETE:
                        if (inputParts.length < 2) {
                            throw new MaxException("Please specify the task number to delete.");
                        }
                        int deleteIndex = Integer.parseInt(inputParts[1]) - 1;
                        if (deleteIndex < 0 || deleteIndex >= tasks.size()) {
                            throw new MaxException("Invalid task number.");
                        }
                        Task removedTask = tasks.remove(deleteIndex);
                        saveTasksToFile(tasks);
                        System.out.println("Noted. I've removed this task:\n  " + removedTask + "\nNow you have " + tasks.size() + " tasks in the list.");
                        break;

                    case BYE:
                        System.out.println("Bye! Will I see you again? :)");
                        return;

                    default:
                        throw new MaxException("I don't really know what that means! Did you mean " + input + " to be a todo, deadline, or event type?");
                }
            } catch (MaxException e) {
                System.out.println(" Oh no! " + e.getMessage());
            } catch (Exception e) {
                System.out.println(" Oh no! Something went wrong: " + e.getMessage());
            }
        }
    }

    private static void saveTasksToFile(ArrayList<Task> tasks) {
        try {
            Path path = Paths.get(FILE_PATH);
            Files.createDirectories(path.getParent());

            FileWriter writer = new FileWriter(FILE_PATH);
            for (Task task : tasks) {
                writer.write(task.toFileString() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving tasks to file: " + e.getMessage());
        }
    }

    private static ArrayList<Task> loadTasksFromFile() {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(FILE_PATH);

        if (!file.exists()) {
            return tasks;
        }

        try {
            List<String> lines = Files.readAllLines(Paths.get(FILE_PATH));
            for (String line : lines) {
                Task task = Task.fromFileString(line);
                if (task != null) {
                    tasks.add(task);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading tasks: this file does not exist! " + e.getMessage());
        }
        return tasks;
    }
}


