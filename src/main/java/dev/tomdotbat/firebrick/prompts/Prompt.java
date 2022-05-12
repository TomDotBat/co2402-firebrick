package dev.tomdotbat.firebrick.prompts;

import java.util.Scanner;

public abstract class Prompt<T> {
    public Prompt(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public abstract T execute();

    protected static Scanner scanner = new Scanner(System.in);
    private final String message;
}