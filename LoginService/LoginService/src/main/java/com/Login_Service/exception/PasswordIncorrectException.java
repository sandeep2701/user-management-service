package com.Login_Service.exception;

public class PasswordIncorrectException extends RuntimeException{
    public PasswordIncorrectException() {
        super("Password doesnot match!!");
    }
    public PasswordIncorrectException(String message) {
        super(message);
    }
}
