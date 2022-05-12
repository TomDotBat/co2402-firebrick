package dev.tomdotbat.firebrick.exceptions;

public class InvalidPromptException extends RuntimeException {
    public InvalidPromptException(String message) {
        super(message);
    }
}