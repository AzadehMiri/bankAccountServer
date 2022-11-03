package org.example.server;

import org.apache.log4j.Logger;
import org.example.App;
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
    public static Logger logger = Logger.getLogger(ClientHandler.class);

    public ClientHandler(Socket socket) throws IOException {
        this.socket = socket;
        outputStream = new ObjectOutputStream(socket.getOutputStream());
        inputStream = new ObjectInputStream(socket.getInputStream());
    }

    @Override
    public void run() {
        try {
            Processor processor = new Processor();

            while (!socket.isClosed()) {

                Transaction transaction = (Transaction) inputStream.readObject();
                logger.info("transaction with "+transaction.getDepositNumber()+" deposit Number received from client");
                processor.process(transaction);
                outputStream.writeObject(transaction);
                logger.info("transaction with "+transaction.getDepositNumber()+" deposit Number sent to client");

            }
        } catch (IOException e) {
            System.out.println("connection disconnect");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch ( LowInitialBalanceException | UpperBoundException e) {

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
