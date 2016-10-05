package tiy.project;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.Connection;
import java.util.ArrayList;

public class ConnectionHandler implements Runnable {

    Socket connection = null;
    Server myServer = null;

    public ConnectionHandler (Socket incomingConnection, Server myServer) {
        this.connection = incomingConnection;
        this.myServer = myServer;
//        this.messageHistory = messageHistory;
    }

    public ConnectionHandler () {
    }

    public void run() {
        try {
            handleIncomingConnection(connection);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void handleIncomingConnection (Socket clientSocket) throws Exception {
        Message mySalt = new Message();
        System.out.println("Connection accepted");

        // this is how we read from the client
        BufferedReader inputFromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        // this is how we write back to the client
        PrintWriter outputToClient = new PrintWriter(clientSocket.getOutputStream(), true);

        String inputLine = inputFromClient.readLine();
        if (!inputLine.equals("return:history")) {
            System.out.println("just received: " + inputLine);
            mySalt.setText(inputLine);
            myServer.myMessages.add(mySalt);
            outputToClient.println(inputLine);
        } else {
            for (Message currentSalt : myServer.myMessages) {
                outputToClient.println(currentSalt.text);
            }
            outputToClient.println("end:history");
        }
    }
}
