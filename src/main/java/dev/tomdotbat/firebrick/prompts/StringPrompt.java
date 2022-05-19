package dev.tomdotbat.firebrick.prompts;

import dev.tomdotbat.firebrick.Game;
import dev.tomdotbat.firebrick.exceptions.InvalidPromptException;

/**
 * An implementation of the prompt for string inputs.
 */
public class StringPrompt extends Prompt<String> {
    /**
     * Constructs a prompt with the given message.
     * @param message the message to display to the user.
     */
    public StringPrompt(String message) {
        super(message);
    }

    /**
     * Set the default answer to use if the player doesn't provide any input.
     * @param defaultAnswer the default answer.
     * @return the string prompt.
     */
    public StringPrompt withDefaultAnswer(String defaultAnswer) {
        this.defaultAnswer = defaultAnswer;
        return this;
    }

    /**
     * Prompts the user with the given message until they provide a valid input.
     * @return the input the user provided.
     */
    @Override
    public String execute() {
        if (Game.getInstance().isComputerPlaying()) { //The computer cannot produce random strings so we error out here.
            throw new InvalidPromptException("Attempted to prompt the computer for an open-ended string.");
        }

        String input;

        do {
            System.out.println(getMessage());
            input = scanner.nextLine();

            //Use the default answer if one is set and no input was given.
            if (defaultAnswer != null && input.isEmpty()) {
                input = defaultAnswer;
                System.out.println("Using default response: " + defaultAnswer);
            }
        } while(!isValidInput(input)); //Prompt the user for their input until the response is valid.

        return input;
    }

    /**
     * Determines whether the user's input is valid or not.
     * @param input the user's input.
     * @return whether the input is valid or not.
     */
    protected boolean isValidInput(String input) { //Validates the user's input.
        return input != null && !input.isEmpty();
    }

    private String defaultAnswer;
}