package Client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Controller {

    @FXML
    private TextArea taChat;
    @FXML
    private TextArea taUserList;
    @FXML
    private TextField tfMessage;

    public void updateUserList(String[] copyOfRange) {
    }

    public void addMessage(String msg) {
    }

    public void setNickLabel(String nick) {
    }

    public void showLogin() {
    }

    public void sendButtonAction() {
        ClientConnection.sendMessage(this.tfMessage.getText());
        this.tfMessage.clear();
    }
}
