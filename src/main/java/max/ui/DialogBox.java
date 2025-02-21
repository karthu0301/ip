package max.ui;

import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.shape.Circle;
import javafx.util.Duration;


/**
 * Custom dialog box for displaying chatbot messages.
 */
public class DialogBox extends HBox {
    @FXML
    private Label text;
    @FXML
    private ImageView displayPicture;

    /**
     * Constructs a DialogBox consisting of a text label and an image view.
     *
     * @param l The {@code Label} containing the text to display in the dialog box.
     * @param iv The {@code ImageView} containing the image to display.
     */
    public DialogBox(Label l, ImageView iv, boolean isUser) {
        text = l;
        displayPicture = iv;

        text.setWrapText(true);
        text.setMaxWidth(300);
        text.setMinHeight(Region.USE_PREF_SIZE);
        text.getStyleClass().add(isUser ? "user-dialog" : "max-dialog");

        displayPicture.setFitWidth(75);
        displayPicture.setFitHeight(75);
        displayPicture.setPreserveRatio(true);

        Circle clip = new Circle(25, 25, 25);
        displayPicture.setClip(clip);

        this.setSpacing(10);
        this.setPadding(new Insets(5, 10, 5, 10)); // Adds padding

        if (isUser) {
            this.setAlignment(Pos.TOP_RIGHT);
            this.getChildren().addAll(text, displayPicture);
        } else {
            this.setAlignment(Pos.TOP_LEFT);
            this.getChildren().addAll(displayPicture, text);
        }

        // Fade-in Animation
        FadeTransition fade = new FadeTransition(Duration.millis(500), this);
        fade.setFromValue(0);
        fade.setToValue(1);
        fade.play();
    }

    public static DialogBox getUserDialog(String text) {
        Label userText = new Label(text);
        ImageView userImage = new ImageView(
                new Image(DialogBox.class.getResourceAsStream("/images/User.jpeg"))
        );
        return new DialogBox(userText, userImage, true);
    }

    public static DialogBox getMaxDialog(String text) {
        Label maxText = new Label(text);
        ImageView maxImage = new ImageView(
                new Image(DialogBox.class.getResourceAsStream("/images/ButlerMax.jpeg"))
        );
        return new DialogBox(maxText, maxImage, false);
    }
}
