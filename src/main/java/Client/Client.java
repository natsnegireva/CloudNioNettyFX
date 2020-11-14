package Client;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class Client extends Application {
    private static Stage stage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("/LoginView.fxml"));
        Image image = new Image(getClass().getResourceAsStream("/chat.png"));
        primaryStage.setTitle("Облако");
        primaryStage.getIcons().add(image);
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root, 460, 260));
        primaryStage.setOnCloseRequest(e -> Platform.exit());
        primaryStage.show();
    }


    private static Parent loadFXML() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Client.class.getResource("View.fxml"));
        return fxmlLoader.load();
    }
}