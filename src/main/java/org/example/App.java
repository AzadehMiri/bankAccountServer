package org.example;

import org.example.server.RunServer;
import org.example.service.Configuration;
import org.example.service.Processor;

public class App {
    private static final int PORT = Configuration.readPort("src\\main\\resources\\core.json");
    public static void main(String[] args) {
        System.out.println(PORT);
        RunServer.runServer(PORT);

    }
}




