package max.ui;

/**
 * Handles interactions with the user by displaying messages to the console.
 */
public class Ui {
    public void showWelcome() {
        System.out.println("Hello I'm max.Max!\nIt's so nice to meet you!!\n");
    }
    public void showLoadingError() {
        System.out.println("Error loading tasks from file: this file does not exist!\n");
    }
}
