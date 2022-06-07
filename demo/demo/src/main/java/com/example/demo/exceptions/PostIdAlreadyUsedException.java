package com.example.demo.exceptions;

public class PostIdAlreadyUsedException extends Exception{
    private String message;

    public PostIdAlreadyUsedException(String message){
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
