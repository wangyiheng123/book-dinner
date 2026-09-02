package com.example.bookdinner.controller.exception;

public class SendMessageException extends RuntimeException{

    public SendMessageException(){};

    public SendMessageException(String message){
        super(message);
    }

}
