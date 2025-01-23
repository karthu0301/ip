import java.util.Scanner;
import java.util.Arrays;

public class Max {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] listArray = new String[100];
        int i = 1;
        System.out.println("Hello I'm Max!\n It's so nice to meet you!!\n ");
        String input = scanner.nextLine();

        while (true) {
            if (input.equals("bye")) {
                System.out.println("Bye! Will I see you again? :)");
                break;
            } else if (input.equals("list")) {
                for (String task : listArray) {
                    if (task != null) {
                        System.out.println(task);
                    }
                }
            } else {
                System.out.println("added: " + input);
                listArray[i - 1] = i + "." + input;
                i++;
            }
            input = scanner.nextLine();
        }

    }
}
