import java.util.Scanner;
import java.util.ArrayList;
public class Max {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();
        System.out.println("Hello I'm Max!\n It's so nice to meet you!!\n");

        while (true) {
            try {
                if (!scanner.hasNextLine()) {
                    break;
                }
                String input = scanner.nextLine();

                String[] inputParts = input.split(" ", 2);
                if (input.equals("bye")) {
                    System.out.println("Bye! Will I see you again? :)");
                    break;
                } else if (input.equals("list")) {
                    System.out.println("Here are the tasks in your list:");
                    for (int j = 0; j < tasks.size(); j++) {
                        System.out.print(" " + (j + 1) + ". " + tasks.get(j).toString() + "\n");
                    }
                } else if (inputParts[0].equals("mark") && inputParts.length == 2) {
                    int index = Integer.parseInt(inputParts[1]) - 1;
                    tasks.get(index).markAsDone();
                } else if (inputParts[0].equals("unmark") && inputParts.length == 2) {
                    int index = Integer.parseInt(inputParts[1]) - 1;
                    tasks.get(index).markAsNotDone();
                } else if (inputParts[0].equalsIgnoreCase("todo") && inputParts.length >= 2){
                    Task task = new ToDo(inputParts[1]);
                    tasks.add(task);
                    System.out.println("Got it. I've added this task:\n  " + task.toString() + "\n" +
                            "Now you have " + tasks.size() + " tasks in the list.");
                } else if (inputParts[0].equalsIgnoreCase("deadline") && inputParts.length >= 2){
                    String[] finalInputParts = inputParts[1].split("/by", 2);
                    Task task = new Deadline(finalInputParts[0],finalInputParts[1]);
                    tasks.add(task);
                    System.out.println("Got it. I've added this task:\n  " + task.toString() + "\n" +
                            "Now you have " + tasks.size() + " tasks in the list.");
                } else if (inputParts[0].equalsIgnoreCase("event") && inputParts.length >= 2){
                    String[] newInputParts = inputParts[1].split("/from", 2);
                    String[] finalInputParts = newInputParts[1].split("/to", 2);
                    Task task = new Event(newInputParts[0], finalInputParts[0],finalInputParts[1]);
                    tasks.add(task);
                    System.out.println("Got it. I've added this task:\n  " + task.toString() + "\n" +
                            "Now you have " + tasks.size() + " tasks in the list.");
                } else if (inputParts[0].equalsIgnoreCase("delete")) {
                    if (inputParts.length < 2) {
                        throw new MaxException("Please specify the task number to delete.");
                    }
                    int index = Integer.parseInt(inputParts[1]) - 1;
                    if (index < 0 || index >= tasks.size()) {
                        throw new MaxException("Invalid task number.");
                    }
                    Task removedTask = tasks.remove(index);
                    System.out.println("Noted. I've removed this task:\n  " + removedTask + "\nNow you have " + tasks.size() + " tasks in the list.");
                } else if (input.equals("todo")){
                    throw new MaxException("The description of a todo cannot be empty.");
                } else if (input.equals("deadline")) {
                    throw new MaxException("The description and deadline of a deadline event cannot be empty.");
                } else if (input.equals("event")) {
                    throw new MaxException("The description and time of an event cannot be empty.");
                } else {
                    throw new MaxException("I don't really know what means! Did you mean " + input + " to be of a todo, deadline or event type?");
                }
            } catch (MaxException e) {
                System.out.println(" Oh no! " + e.getMessage());
            } catch (Exception e) {
                System.out.println(" Oh no! Something went wrong: " + e.getMessage());
            }

         }
    }
}
