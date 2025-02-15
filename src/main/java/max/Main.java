package max;

import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import max.ui.MainWindow;



/**
 * The main entry point for the JavaFX application.
 */
public class Main extends Application {
    private final Max chatbot = new Max();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/MainWindow.fxml"));
            Parent root = fxmlLoader.load(); // Load UI before setting controller
            MainWindow controller = fxmlLoader.getController();
            controller.setMax(chatbot); // Pass chatbot instance

            Scene scene = new Scene(root);

            URL cssResource = getClass().getResource("/view/style.css");
            if (cssResource == null) {
                System.out.println("Error: style.css not found!");
            } else {
                scene.getStylesheets().add(cssResource.toExternalForm());
            }


            stage.setScene(scene);
            stage.setTitle("Max Chatbot");

            // Set minimum size to prevent UI breakage
            stage.setMinWidth(600);
            stage.setMinHeight(400);

            // Ensure it starts at a reasonable size
            stage.setWidth(900);
            stage.setHeight(650);

            // Allow resizing to fit user preferences
            stage.setResizable(true);
            stage.show();
        } catch (Exception e) {
            System.err.println("Error initializing UI: " + e.getMessage());
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
