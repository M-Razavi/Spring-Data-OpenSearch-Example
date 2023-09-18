package org.opensearch.data.example.service.exception;

public class BookNotFoundException extends Exception {

    public BookNotFoundException(String message) {
        super(message);
    }
}
