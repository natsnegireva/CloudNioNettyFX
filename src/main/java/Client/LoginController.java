package Client;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class LoginController implements Initializable {
    @FXML
    private TextField tfLogin;
    @FXML
    private PasswordField tfPass;
    @FXML
    private Label lResponse;

    public Controller Controller;
    public Scene scene;
    private static LoginController instance;
    Network net = new Network();

    public LoginController() {
        instance = this;
    }

    public static LoginController getInstance() {
        return instance;
    }

    public void loginButtonAction() throws NullPointerException {
        if (tfLogin.getText() != null && tfPass.getText() != null) {
            try {
                String s = "/auth " + tfLogin.getText() + tfPass.getText();
                Network.sendMsg(s);
            } catch (NullPointerException e) {
                e.printStackTrace();
                tfLogin.clear();
                tfPass.clear();
            }
        } else {
            setAutorized( false );
            tfLogin.clear();
            tfPass.clear();
        }
    }

    public boolean setAutorized(boolean b) {
        return b;
    }

    public void showScene() {
        Platform.runLater(() -> {
            Stage stage = (Stage)this.tfLogin.getScene().getWindow();
            stage.setResizable(true);
            stage.setWidth(500.0D);
            stage.setHeight(400.0D);
            stage.setOnCloseRequest((e) -> {
                Platform.exit();
                System.exit(0);
            });
            stage.setScene(this.scene);
            stage.setMinWidth(300.0D);
            stage.setMinHeight(300.0D);
            stage.centerOnScreen();
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            if (Network.readMSg().equals("OK")) {
                System.out.println("Соединение востановлено");
                FXMLLoader fmxlLoader = new FXMLLoader(this.getClass().getClassLoader().getResource("./View.fxml"));
                Parent window = null;
                try {
                    window = (BorderPane) fmxlLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                this.Controller = (Controller) fmxlLoader.getController();
                assert window != null;
                this.scene = new Scene(window);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}