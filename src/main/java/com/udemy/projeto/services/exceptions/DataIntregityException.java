package com.udemy.projeto.services.exceptions;

public class DataIntregityException extends RuntimeException {

    public DataIntregityException(String msg) {
        super(msg); //passa a msg para super classe RuntimeException
    }

    public DataIntregityException(String msg, Throwable cause){
        super(msg,cause);
    }
}
