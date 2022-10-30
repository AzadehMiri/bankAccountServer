package org.example.service;

import org.example.exception.LowInitialBalanceException;
import org.example.exception.UpperBoundException;
import org.example.validation.Validation;

public class DepositAndWithdraw {
    Validation validation = new Validation();

    public synchronized int withdrawn(int withdrawn, int upperBound, int initialBalance) throws LowInitialBalanceException {
        validation.initialBalanceValidation(withdrawn, initialBalance);
        return initialBalance -= withdrawn;
    }

    public synchronized int deposit(int deposit, int upperBound, int initialBalance) throws UpperBoundException {
        validation.upperBoundValidation(deposit, upperBound);
        return initialBalance += deposit;
    }
}
