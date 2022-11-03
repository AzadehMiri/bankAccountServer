package org.example;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.example.server.RunServer;
import org.example.service.Configuration;

public class App {
    private static final int PORT = Configuration.readPort("src\\main\\resources\\core.json");

    public static void main(String[] args) {
        BasicConfigurator.configure();
        RunServer.runServer(PORT);


    }
}




