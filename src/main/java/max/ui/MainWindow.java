package max.ui;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import max.Max;


/**
 * Controller for MainWindow. Provides layout for the chatbot.
 */
public class MainWindow {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;

    private Max max;

    /**
     * Initializes main window.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

        // Show the welcome message
        showWelcomeMessage();
    }

    private void showWelcomeMessage() {
        dialogContainer.getChildren().add(
                DialogBox.getMaxDialog("Ah, welcome. I am Max, your ever-diligent butler. How may I be of service?")
        );
    }

    public void setMax(Max max) {
        this.max = max;
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();

        if (input.trim().isEmpty()) {
            return; // Ignore empty messages
        }

        // Show user message
        DialogBox userDialog = DialogBox.getUserDialog(input);
        dialogContainer.getChildren().add(userDialog);

        // Show "Max is typing..." message temporarily
        Label typingLabel = new Label("Max is thinking...");
        typingLabel.getStyleClass().add("typing-indicator");
        DialogBox typingDialog = new DialogBox(typingLabel, new ImageView(), false);
        dialogContainer.getChildren().add(typingDialog);

        userInput.clear();

        // Simulate a delay before showing Max's response
        PauseTransition pause = new PauseTransition(Duration.seconds(1.5));
        pause.setOnFinished(event -> {
            dialogContainer.getChildren().remove(typingDialog); // Remove typing indicator
            String response = max.getResponse(input); // Get actual response
            dialogContainer.getChildren().add(DialogBox.getMaxDialog(response)); // Show Max's response
        });
        pause.play();
    }

}
