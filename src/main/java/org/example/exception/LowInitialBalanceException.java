package org.example.exception;

public class LowInitialBalanceException extends Exception{
    public LowInitialBalanceException(String message) {
        super(" you can not withdraw "+message);
    }
}
