package com.lcwd.electronic.store.exceptions;

public class BadApiRequestException extends RuntimeException{
    public BadApiRequestException(String message){
        super(message);
    }
    BadApiRequestException(){
        super("Bad Request");
    }
}
