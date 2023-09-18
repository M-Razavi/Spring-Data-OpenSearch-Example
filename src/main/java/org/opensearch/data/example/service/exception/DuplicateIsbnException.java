package org.opensearch.data.example.service.exception;

public class DuplicateIsbnException extends Exception {

    public DuplicateIsbnException(String message) {
        super(message);
    }
}
