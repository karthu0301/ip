import java.util.Scanner;

public class Max {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Hello I'm Max!\n What can I do for you?\n ");
        String input = scanner.nextLine();

        while (!input.equals("bye")) {
            System.out.println(input);
            input = scanner.nextLine();
        }
            System.out.println("Bye. Hope to see you again soon!");
    }
}
