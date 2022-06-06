package com.example.demo.exceptions;

public class UserIdNotFoundException extends Exception{
    private String message;

    public UserIdNotFoundException(String message){
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
