package dev.tomdotbat.firebrick.exceptions;

/**
 * An exception thrown when a prompt is used wrongly.
 */
public class InvalidPromptException extends RuntimeException {
    /**
     * Constructs an invalid prompt exception with the given message.
     * @param message the exception message.
     */
    public InvalidPromptException(String message) {
        super(message);
    }
}