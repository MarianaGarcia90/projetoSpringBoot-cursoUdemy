package com.udemy.projeto.services.exceptions;

public class ObjectNotFoundException extends RuntimeException {

    public ObjectNotFoundException (String msg) {
        super(msg); //passa a msg para super classe RuntimeException
    }

    public ObjectNotFoundException(String msg, Throwable cause){
        super(msg,cause);
    }
}
