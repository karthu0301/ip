import java.util.Scanner;

public class Ui {

    private final Scanner scanner = new Scanner(System.in);

    public void showWelcome() {
        System.out.println("Hello I'm Max!\nIt's so nice to meet you!!\n");
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showError(String message) {
        System.out.println("Oh no! " + message);
    }

    public void showLoadingError() {
        System.out.println("Error loading tasks from file: this file does not exist!\n");
    }

    public void show(String message) {
        System.out.println(message);
    }
}
