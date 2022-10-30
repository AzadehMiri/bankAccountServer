package org.example.exception;

public class UpperBoundException extends Exception{
    public UpperBoundException(String message) {
        super(message+"خطای سقف برداشت!!!!:: ");
    }
}
