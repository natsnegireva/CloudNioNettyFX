package Server;

import Server.handlers.ClientHandler;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    public Server() {
        ExecutorService runner = Executors.newFixedThreadPool(2);
        try(ServerSocket  server = new ServerSocket(8189)){
            System.out.println("Сервер запущен...");
            while (true) {
                runner.execute(new ClientHandler(server.accept()));
                System.out.println("Клиент подключился!");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Server();
    }
}
