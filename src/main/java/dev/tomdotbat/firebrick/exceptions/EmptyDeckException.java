package dev.tomdotbat.firebrick.exceptions;

public class EmptyDeckException extends RuntimeException {
    public EmptyDeckException(String message) {
        super(message);
    }
}