package dev.tomdotbat.firebrick.prompts;

import dev.tomdotbat.firebrick.Game;
import dev.tomdotbat.firebrick.exceptions.InvalidPromptException;

public class StringPrompt extends Prompt<String> {
    public StringPrompt(String message) {
        super(message);
    }

    public StringPrompt withDefaultAnswer(String defaultAnswer) {
        this.defaultAnswer = defaultAnswer;
        return this;
    }

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

    protected boolean isValidInput(String input) { //Validates the user's input.
        return input != null && !input.isEmpty();
    }

    private String defaultAnswer;
}