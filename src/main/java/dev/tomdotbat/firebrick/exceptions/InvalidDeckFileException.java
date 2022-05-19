package dev.tomdotbat.firebrick.exceptions;

/**
 * An exception thrown when an invalid card type is read form the deck file.
 */
public class InvalidDeckFileException extends RuntimeException {
    /**
     * Constructs an invalid deck file exception with the given message.
     * @param message the exception message.
     */
    public InvalidDeckFileException(String message) {
        super(message);
    }
}