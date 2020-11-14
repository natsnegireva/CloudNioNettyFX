package Client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;

public class ClientConnection implements Runnable {
    private static Socket socket;
    private static DataInputStream in;
    private static DataOutputStream out;
    private static String nick;
    private static String login;
    private static String pass;
    private Controller Controller;

    public ClientConnection(String login, String pass, Controller controller) {
        ClientConnection.login = login;
        ClientConnection.pass = pass;
        this.Controller = Controller;
    }

    private void login() {
        sendMessage("/auth " + login + " " + pass);
    }

    public static void logout() {
        nick = null;
        sendMessage("/exit");
    }

    public static void delete() {
        nick = null;
        sendMessage("/delete");
    }

    public static void sendMessage(String message) {
        if (!message.isEmpty()) {
            try {
                out.writeUTF(message);
                out.flush();
            } catch (IOException a) {
                a.printStackTrace();
            }

        }
    }

    public void run() {
        try {
            socket = new Socket("localhost", 8189);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            while(socket.isConnected()) {
                String msg = in.readUTF();
                String[] users;
                if (nick != null) {
                    if (msg.startsWith("/")) {
                        if (msg.equalsIgnoreCase("/exit")) {
                            this.Controller.showLogin();
                        } else if (msg.equalsIgnoreCase("/delete")) {
                            this.Controller.showLogin();
                        } else if (msg.startsWith("/user_list ")) {
                            users = msg.split(" ");
                            this.Controller.updateUserList((String[])Arrays.copyOfRange(users, 1, users.length));
                        }
                    } else if (!msg.isEmpty()) {
                        this.Controller.addMessage(msg);
                    }
                } else if (msg.startsWith("/authOk")) {
                    users = msg.split(" ");
                    nick = users[1];
                    this.Controller.setNickLabel(nick);
                    LoginController.getInstance().showScene();
                }
            }
        } catch (IOException c) {
            System.out.println("Попытка отправки сообщения прошла не удачно");
            c.printStackTrace();
        } finally {
            try {
                if (socket != null) {
                    socket.close();
                }
            } catch (IOException  e) {
                e.printStackTrace();
            }
        }
    }

    public void setRegister(boolean b) {
        return;
    }
}