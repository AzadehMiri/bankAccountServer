package org.example.server;

import org.apache.log4j.Logger;
import org.example.App;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RunServer {
    private static ExecutorService pool = Executors.newFixedThreadPool(10);
    private static List<ClientHandler> clients = new ArrayList<>();
    public static Logger logger = Logger.getLogger(RunServer.class);

    public static void runServer(int port) {
        try {
            ServerSocket listener = new ServerSocket(port);

            while (true) {
                Socket client = listener.accept();
                logger.info("new connection started");
                ClientHandler clientThread = new ClientHandler(client);
                clients.add(clientThread);
                pool.execute(clientThread);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
