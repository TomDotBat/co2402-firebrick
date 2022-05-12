package dev.tomdotbat.firebrick.exceptions;

public class InvalidDeckFileException extends RuntimeException {
    public InvalidDeckFileException(String message) {
        super(message);
    }
}