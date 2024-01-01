package com.Login_Service.exception;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(){
        super("Record Not Found On Server");
    }
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
