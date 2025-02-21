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
            Parent root = fxmlLoader.load();
            MainWindow controller = fxmlLoader.getController();
            controller.setMax(chatbot);

            Scene scene = new Scene(root);

            URL cssResource = getClass().getResource("/view/style.css");
            if (cssResource == null) {
                System.out.println("Error: style.css not found!");
            } else {
                scene.getStylesheets().add(cssResource.toExternalForm());
            }


            stage.setScene(scene);
            stage.setTitle("Max Chatbot");

            stage.setMinWidth(400);
            stage.setMinHeight(400);

            stage.setWidth(500);
            stage.setHeight(600);

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
