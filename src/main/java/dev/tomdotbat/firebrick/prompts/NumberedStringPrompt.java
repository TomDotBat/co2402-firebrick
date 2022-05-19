package dev.tomdotbat.firebrick.prompts;

import dev.tomdotbat.firebrick.Game;

import java.util.ArrayList;
import java.util.List;

/**
 * An implementation of the prompt for numbered inputs based on a list of strings.
 */
public class NumberedStringPrompt extends Prompt<Integer> {
    /**
     * Constructs a prompt with the given message.
     * @param message the message to display to the user.
     */
    public NumberedStringPrompt(String message) {
        super(message);
    }

    /**
     * Adds an answer to the list of acceptable answers.
     * @param answer the answer to make acceptable.
     * @return the restricted string prompt.
     */
    public NumberedStringPrompt withAnswer(String answer) {
        acceptableAnswers.add(answer);
        return this;
    }

    /**
     * Sets the acceptable answers list to the given answers.
     * @param answers the answers to make acceptable.
     * @return the numbered string prompt.
     */
    public NumberedStringPrompt withAnswers(List<String> answers) {
        acceptableAnswers = answers;
        return this;
    }

    /**
     * Gets the prompt message with the acceptable answers in an ordered list.
     * @return the prompt message.
     */
    @Override
    public String getMessage() {
        StringBuilder answerList = new StringBuilder();

        for (int i = 0; i < acceptableAnswers.size(); i++) { //Put all the acceptable answers in an ordered list.
            answerList.append(i + 1)
                .append(". ")
                .append(acceptableAnswers.get(i))
                .append("\n");
        }

        return super.getMessage() + "\n" + answerList;
    }

    /**
     * Prompts the user with the given message until they provide a valid input.
     * @return the input the user provided.
     */
    @Override
    public Integer execute() {
        if (Game.getInstance().isComputerPlaying()) { //If the player is an AI we'll choose a random answer.
            return Game.getInstance().getRandomInt(acceptableAnswers.size());
        }

        int input = 0;

        do {
            System.out.println(getMessage());

            String inputString = SCANNER.nextLine(); //Read the next input and attempt to parse it as an integer.
            try {
                input = Integer.parseInt(inputString);
            } catch (NumberFormatException ignored) {}
        } while(!isValidInput(input)); //Prompt the user for their input until the response is valid.

        return input - 1;
    }

    /**
     * Determines whether the user's input is valid or not.
     * @param input the user's input.
     * @return whether the input is valid or not.
     */
    protected boolean isValidInput(int input) { //Determine whether the user's input is valid.
        return input > 0 && input <= acceptableAnswers.size();
    }

    private List<String> acceptableAnswers = new ArrayList<>();
}