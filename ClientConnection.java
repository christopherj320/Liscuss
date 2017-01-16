package com.zerochass;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

/**
 * Created by Christopher Daniel Jativa on 1/15/2017
 */

/**
 * This class is responsible for connecting to the server and handling sending/receiving of information therein
 */
public class ClientConnection{


    private Socket socket;
    private BufferedReader received_reader;
    private BufferedReader outgoing_reader;

    private String host;
    private int port;

    public ClientConnection(String host, int port) {
        this.host = host;
        this.port = port;

        createSocketConnection();
    }

    private void createSocketConnection() {
        try {
            socket = new Socket(host, port);

            // Create the output stream to write to the server
            PrintStream os = new PrintStream(socket.getOutputStream());

            // Create the input stream to read from the server
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Read incoming data from the server until we have reached the end of the document
            String line = in.readLine();
            while(line != null) {
                System.out.println(line);
                line = in.readLine();
            }

            // Once we have reached the end of our message, we can close out streams and the socket
            in.close();
            os.close();
            socket.close();

        }   catch (IOException e) {
                // If an error occurs we would like to see what happened
                e.printStackTrace();
        }


    }

}
