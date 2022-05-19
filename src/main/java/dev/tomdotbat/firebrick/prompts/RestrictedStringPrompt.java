package dev.tomdotbat.firebrick.prompts;

import dev.tomdotbat.firebrick.Game;

import java.util.ArrayList;
import java.util.List;

/**
 * An implementation of the prompt for string inputs restricted to a provided set of answers.
 */
public class RestrictedStringPrompt extends StringPrompt {
    /**
     * Constructs a prompt with the given message.
     * @param message the message to display to the user.
     */
    public RestrictedStringPrompt(String message) {
        super(message);
    }

    /**
     * Adds an answer to the list of acceptable answers.
     * @param answer the answer to make acceptable.
     * @return the restricted string prompt.
     */
    public RestrictedStringPrompt withAnswer(String answer) {
        acceptableAnswers.add(answer);
        return this;
    }

    /**
     * Gets the prompt message with the acceptable answers appended to the end.
     * @return the prompt message.
     */
    @Override
    public String getMessage() { //Append a list of acceptable answers to the message.
        return super.getMessage() + " (" + String.join("/", acceptableAnswers) + ")";
    }

    /**
     * Prompts the user with the given message until they provide a valid input.
     * @return the input the user provided.
     */
    @Override
    public String execute() {
        if (Game.getInstance().isComputerPlaying()) { //If the player is an AI we'll choose a random answer.
            int max = acceptableAnswers.size();
            return acceptableAnswers.get(Game.getInstance().getRandomInt(max));
        }

        String input = super.execute();

        for (String answer : acceptableAnswers) {
            if (input.equalsIgnoreCase(answer)) { //Return the answer in the expected casing.
                return answer;
            }
        }

        return "";
    }

    /**
     * Determines whether the user's input is valid or not.
     * @param input the user's input.
     * @return whether the input is valid or not.
     */
    @Override
    protected boolean isValidInput(String input) {
        if (!super.isValidInput(input)) { //Ensure the super class validation is satisfied.
            return false;
        }

        for (String answer : acceptableAnswers) {
            if (input.equalsIgnoreCase(answer)) { //Determine whether the input is an acceptable answer.
                return true;
            }
        }

        return false;
    }

    private final List<String> acceptableAnswers = new ArrayList<>();
}