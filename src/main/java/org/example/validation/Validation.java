package org.example.validation;

import org.example.exception.LowInitialBalanceException;
import org.example.exception.UpperBoundException;

public class Validation {
    public void initialBalanceValidation(int withdrawn,int initialBalance) throws LowInitialBalanceException {
        if (withdrawn > initialBalance) {
            throw new LowInitialBalanceException(String.valueOf(initialBalance));
        }
    }

    public void upperBoundValidation(int deposit, int upperBound) throws UpperBoundException {
        if (deposit > upperBound) {
            throw new UpperBoundException(String.valueOf(upperBound));
        }
    }
}
