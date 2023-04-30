package com.unesp.soo.src.exceptions;

public class APIException extends Exception{

    public int statusCode;

    public APIException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }
}