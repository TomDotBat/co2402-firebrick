package dev.tomdotbat.firebrick.prompts;

import java.util.Scanner;

/**
 * An abstract class to prompt the user for inputs.
 * @param <T> the type of the prompt output.
 */
public abstract class Prompt<T> {
    /**
     * Constructs a prompt with the given message.
     * @param message the message to display to the user.
     */
    public Prompt(String message) {
        this.message = message;
    }

    /**
     * Gets the prompt message.
     * @return the prompt message.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Prompts the user with the given message until they provide a valid input.
     * @return the input the user provided.
     */
    public abstract T execute();

    protected static Scanner scanner = new Scanner(System.in);
    private final String message;
}