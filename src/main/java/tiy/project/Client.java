package tiy.project;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Client {

    static final String HOST_ADDRESS = "localhost";
    static final int PORT_NUMBER = 8088;
    Socket clientSocket = null;
    PrintWriter out = null;
    BufferedReader in = null;

    public String sendMessage(String message){
        String serverResponse = null;
        try {
            clientSocket = new Socket(HOST_ADDRESS, PORT_NUMBER);
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out.println(message);

            //get initial response
            serverResponse = in.readLine();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return serverResponse;
    }

    public ArrayList<Message> sendHistory() {
        String serverResponse = null;
        Message myMessage = new Message();
        ArrayList<Message> myMessages = new ArrayList<>();
        try {
            clientSocket = new Socket(HOST_ADDRESS, PORT_NUMBER);
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out.println("return:history");
            serverResponse = in.readLine();
            while (!serverResponse.equals("end:history")) {
                myMessage.setText(serverResponse);
                serverResponse = in.readLine();
                }
        } catch (Exception ex) {
        }
        return myMessages;
    }

}
