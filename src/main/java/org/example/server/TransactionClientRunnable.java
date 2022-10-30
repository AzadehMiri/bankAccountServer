package org.example.server;

import org.example.exception.LowInitialBalanceException;
import org.example.exception.UpperBoundException;
import org.example.model.Transaction;
import org.example.service.Processor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class TransactionClientRunnable implements Runnable {
    Socket socket;

    public TransactionClientRunnable(Socket socket) throws IOException {
        this.socket = socket;
    }

    @Override
    public void run() {
        while (!socket.isClosed()) {
            Processor processor = new Processor();
            Transaction transaction = new Transaction();


            try {
                ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
                inputStream.readObject();
                Transaction outputTransaction = processor.process(transaction);
                outputStream.writeObject(outputTransaction);
            } catch (LowInitialBalanceException | UpperBoundException | IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
