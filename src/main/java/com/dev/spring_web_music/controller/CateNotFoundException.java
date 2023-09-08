package com.dev.spring_web_music.controller;

public class CateNotFoundException extends Throwable{
    public CateNotFoundException() {
    }

    public CateNotFoundException(String message) {
        super(message);
    }

    public CateNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public CateNotFoundException(Throwable cause) {
        super(cause);
    }

    public CateNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
