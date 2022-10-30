package org.example;

import org.example.server.ServerSideTransaction;
import org.example.service.Configuration;

public class App {
    public static void main(String[] args) {
       int portNumber= Configuration.readPort("src\\main\\resources\\core.json");
        System.out.println(portNumber);
        ServerSideTransaction serverSideTransaction=new ServerSideTransaction();
        serverSideTransaction.createServerSocket(portNumber);
    }
}




