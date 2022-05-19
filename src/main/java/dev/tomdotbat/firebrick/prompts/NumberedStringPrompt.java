package dev.tomdotbat.firebrick.prompts;

import dev.tomdotbat.firebrick.Game;

import java.util.ArrayList;
import java.util.List;

public class NumberedStringPrompt extends Prompt<Integer> {
    public NumberedStringPrompt(String message) {
        super(message);
    }

    public NumberedStringPrompt withAnswer(String answer) {
        acceptableAnswers.add(answer);
        return this;
    }

    public NumberedStringPrompt withAnswers(List<String> answers) {
        acceptableAnswers = answers;
        return this;
    }

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

    @Override
    public Integer execute() {
        if (Game.getInstance().isComputerPlaying()) { //If the player is an AI we'll choose a random answer.
            return Game.getInstance().getRandomInt(acceptableAnswers.size());
        }

        int input = 0;

        do {
            System.out.println(getMessage());

            String inputString = scanner.nextLine(); //Read the next input and attempt to parse it as an integer.
            try {
                input = Integer.parseInt(inputString);
            } catch (NumberFormatException ignored) {}
        } while(!isValidInput(input)); //Prompt the user for their input until the response is valid.

        return input - 1;
    }

    protected boolean isValidInput(int input) { //Determine whether the user's input is valid.
        return input > 0 && input <= acceptableAnswers.size();
    }

    private List<String> acceptableAnswers = new ArrayList<>();
}