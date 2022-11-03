package org.example.service;

import org.apache.log4j.Logger;
import org.example.exception.LowInitialBalanceException;
import org.example.exception.UpperBoundException;
import org.example.model.Deposit;
import org.example.model.Transaction;

import java.util.ArrayList;
import java.util.List;

public class Processor {
    private static Logger logger = Logger.getLogger(Processor.class);
    public Transaction process(Transaction transaction) throws LowInitialBalanceException, UpperBoundException {
        Reader reader = new Reader();
        logger.info("process on transaction with " + transaction.getDepositNumber() + " deposit number is started.");
        List<Deposit> depositList = reader.jsonParser("src\\main\\resources\\core.json");
        Deposit deposit1 = new Deposit();
        for (Deposit deposit : depositList) {
            if (deposit.getId().equals(transaction.getDepositNumber())) {
                deposit1=deposit;
                break;
            }
        }
        DepositAndWithdraw depositAndWithdraw = new DepositAndWithdraw();
        int newAccountBalance = 0;
        switch (transaction.getTransactionType()) {
            case "deposit":
                newAccountBalance = depositAndWithdraw.deposit(transaction.getTransactionAmount(),deposit1.getUpperBound(),deposit1.getInitialBalance());
                break;
            case "withdraw":
                newAccountBalance = depositAndWithdraw.withdrawn(transaction.getTransactionAmount(), deposit1.getUpperBound(),deposit1.getInitialBalance());
                break;
            default:
                System.out.println("error");
        }
        transaction.setAccountBalance(newAccountBalance);
        logger.info("process on transaction with " + transaction.getDepositNumber() + " deposit number is Completed.");

        return transaction;
    }
}
