package com.asher.urlshorter.exception;

public class UrlNotFoundException extends UrlShortenerException{
    public UrlNotFoundException(String message) {
        super(message);
    }

    public UrlNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
