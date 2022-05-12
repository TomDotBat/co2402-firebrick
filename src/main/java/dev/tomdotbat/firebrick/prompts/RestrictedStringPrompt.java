package dev.tomdotbat.firebrick.prompts;

import dev.tomdotbat.firebrick.Game;

import java.util.ArrayList;
import java.util.List;

public class RestrictedStringPrompt extends StringPrompt {
    public RestrictedStringPrompt(String message) {
        super(message);
    }

    public RestrictedStringPrompt withAnswer(String answer) {
        acceptableAnswers.add(answer);
        return this;
    }

    @Override
    public String getMessage() { //Append a list of acceptable answers to the message.
        return super.getMessage() + " (" + String.join("/", acceptableAnswers) + ")";
    }

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