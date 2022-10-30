package org.example.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSideTransaction {
    ServerSocket serverSocket;

    public void createServerSocket(int portNumber) {
        try {
            serverSocket = new ServerSocket(portNumber);
            acceptClient();
        } catch (IOException e) {
            System.err.println("Could not Listen to port");
            e.printStackTrace();
        }

    }

    public void acceptClient() {
        while (true) {
            try {
                System.out.println("New Connection1");
                Socket socket=serverSocket.accept();
                System.out.println("New Connection2");
                Thread thread = new Thread( new TransactionClientRunnable(socket));
                thread.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
