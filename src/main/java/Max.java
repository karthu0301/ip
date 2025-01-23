import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;


public class Max {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Task> taskList = new ArrayList<>();
        System.out.println("Hello I'm Max!\n It's so nice to meet you!!\n ");

        while (true) {
            String input = scanner.nextLine();
            String[] inputParts = input.split(" ");
            if (input.equals("bye")) {
                System.out.println("Bye! Will I see you again? :)");
                break;
            } else if (input.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                int i = 1;
                for (Task task : taskList) {
                    System.out.print(i + ". ");
                    task.displayTask();
                    i++;
                }
            } else if (inputParts[0].equals("mark") && inputParts.length == 2) {
                Task task = taskList.get(Integer.parseInt(inputParts[1]) - 1);
                task.markAsDone();
            } else if (inputParts[0].equals("unmark") && inputParts.length == 2) {
                Task task = taskList.get(Integer.parseInt(inputParts[1]) - 1);
                task.markAsNotDone();
            } else {
                System.out.println("added: " + input);
                taskList.add(new Task(input));
            }
        }

    }
}
