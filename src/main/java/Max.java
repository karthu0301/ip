import java.util.Scanner;


public class Max {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Task[] tasks = new Task[100];
        int i = 0;
        System.out.println("Hello I'm Max!\n It's so nice to meet you!!\n ");

        while (true) {
            String input = scanner.nextLine();
            String[] inputParts = input.split(" ", 2);
            if (input.equals("bye")) {
                System.out.println("Bye! Will I see you again? :)");
                break;
            } else if (input.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (i = 0; i < tasks.length; i++) {
                    if (tasks[i] != null) {
                        System.out.print((i + 1) + ". " + tasks[i].toString() + "\n");
                    }
                }
            } else if (inputParts[0].equals("mark") && inputParts.length == 2) {
                int index = Integer.parseInt(inputParts[1]) - 1;
                tasks[index].markAsDone();
            } else if (inputParts[0].equals("unmark") && inputParts.length == 2) {
                int index = Integer.parseInt(inputParts[1]) - 1;
                tasks[index].markAsNotDone();
            } else if (inputParts[0].equalsIgnoreCase("todo") && inputParts.length >= 2){
                Task task = new ToDo(inputParts[1]);
                tasks[i] = task;
                i++;
                System.out.println("Got it. I've added this task:\n" + task.toString() + "\n" +
                        "Now you have " + (i) + " tasks in the list.");
            } else if (inputParts[0].equalsIgnoreCase("deadline") && inputParts.length >= 2){
                String[] finalInputParts = inputParts[1].split("/by", 2);
                Task task = new Deadline(finalInputParts[0],finalInputParts[1]);
                tasks[i] = task;
                i++;
                System.out.println("Got it. I've added this task:\n" + task.toString() + "\n" +
                        "Now you have " + (i) + " tasks in the list.");
            } else if (inputParts[0].equalsIgnoreCase("event") && inputParts.length >= 2){
                String[] newInputParts = inputParts[1].split("/from", 2);
                String[] finalInputParts = newInputParts[1].split("/to", 2);
                Task task = new Event(newInputParts[0], finalInputParts[0],finalInputParts[1]);
                tasks[i] = task;
                i++;
                System.out.println("Got it. I've added this task:\n" + task.toString() + "\n" +
                        "Now you have " + (i) + " tasks in the list.");
            }
        }

    }
}
