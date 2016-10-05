package tiy.project;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;

public class Server {

    ArrayList<Message> myMessages = new ArrayList<>();

    public static void main(String[] args) {
        Server myServer = new Server();
        myServer.startServer();
    }

    Socket connection = null;

    public Server () {}

    public Server (Socket connection) {this.connection = connection;}

    public void startServer() {
        try {
            System.out.println("Starting Server");
            ServerSocket listener = new ServerSocket(8088);
            while (true) {
                Socket incConnection = listener.accept();
                ConnectionHandler handler = new ConnectionHandler(incConnection, this);
                Thread multiThreadServer = new Thread(handler);
                multiThreadServer.start();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
