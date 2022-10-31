package org.example.server;

import org.example.exception.LowInitialBalanceException;
import org.example.exception.UpperBoundException;
import org.example.model.Transaction;
import org.example.service.Processor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private Socket socket;
    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;

    public ClientHandler(Socket socket) throws IOException {
        this.socket = socket;
        outputStream = new ObjectOutputStream(socket.getOutputStream());
        inputStream = new ObjectInputStream(socket.getInputStream());
    }

    @Override
    public void run() {
        while (!socket.isClosed()) {

            Transaction transaction;
            Processor processor = new Processor();
            try {
                transaction = (Transaction) inputStream.readObject();
                processor.process(transaction);
                //transaction.setAccountBalance(100);
                outputStream.writeObject(transaction);
                System.out.println(transaction);

            } catch (IOException | ClassNotFoundException | UpperBoundException | LowInitialBalanceException e) {
                throw new RuntimeException(e);
            } finally {
                try {
                    outputStream.close();
                    inputStream.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
