package dev.tomdotbat.firebrick;

import dev.tomdotbat.firebrick.exceptions.InvalidDeckFileException;
import dev.tomdotbat.firebrick.prompts.Prompt;
import dev.tomdotbat.firebrick.prompts.RestrictedStringPrompt;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

public class Game {
    static {
        long seed = 0;

        File file = new File("./RandomSeed.txt"); //Load the random seed from the seed file.
        try {
            Scanner scanner = new Scanner(file);
            seed = scanner.nextLong(); //Read the random seed as a long.
            scanner.close();
        } catch (FileNotFoundException ex) {
            System.err.println("Failed to load the seed from RandomSeed.txt");
            ex.printStackTrace();
        }

        RANDOM = new Random(seed);
    }

    public static Game getInstance() { //Singleton instance getter.
        if (instance == null) {
            instance = new Game();
        }

        return instance;
    }

    public void start() {
        Prompt<String> prompt = new RestrictedStringPrompt("Which character would you like to play as?")
                .withAnswer("Sorceress")
                .withAnswer("Wizard")
                .withDefaultAnswer("Sorceress");

        String selection = prompt.execute(); //Create the player classes based on the user's preference.
        player1 = createPlayer(selection);
        player2 = createPlayer(selection.equals("Sorceress") ? "Wizard" : "Sorceress");

        prompt = new RestrictedStringPrompt("Would you like player 2 to be an AI?")
                .withAnswer("Yes")
                .withAnswer("No")
                .withDefaultAnswer("Yes");

        if (prompt.execute().equals("Yes")) { //Set player 2 to the preferred AI state.
            player2.setIsComputer(true);
        }

        final int MAX_TURNS = 30;
        isPlaying = true;

        //Make both players draw a card before the first turn.
        System.out.println();
        System.out.println("Pre-Game");
        System.out.println("--------");
        player1.drawCard();
        player2.drawCard();

        //Execute the turn procedure until the turn limit is reached or a player dies.
        for (int i = 1; isPlaying && i <= MAX_TURNS; i++) {
            executeTurn(i);
        }

        finish();
    }

    public void finish() {
        isPlaying = false;

        //Print out who won or if the turn limit was reached.
        if (player1.isDead()) {
            System.out.printf("%s has died, %s wins!\n",
                    player1.getName(), player2.getName());
        }
        else if (player2.isDead()) {
            System.out.printf("%s has died, %s wins!\n",
                    player2.getName(), player1.getName());
        }
        else {
            System.out.println("Game over, the turn limit was reached");
        }

        System.exit(0); //Exit the process.
    }

    public void executeTurn(int roundNo) {
        System.out.println();
        System.out.println("Round " + roundNo);
        System.out.println("------------------------------------------------------------------------------------");

        for (int i = 0; isPlaying && i < 2; i++) {
            currentPlayer = i == 0 ? player1 : player2; //Alternate between the user and computer's play.

            Player opponent = getCurrentOpponent();

            currentPlayer.drawCard(); //Draw a card from the deck into the player's hand.
            currentPlayer.playCard(); //Ask the player to play one of their cards.
            currentPlayer.clearDeadMinions();
            opponent.clearDeadMinions();
            System.out.println();

            currentPlayer.minionAttack(opponent); //Make the player's minions attack the opponents.
            currentPlayer.clearDeadMinions();
            opponent.clearDeadMinions();
            System.out.println();

            //Print the statistics of both players at the end of each turn.
            currentPlayer.printStats();
            System.out.println();
            opponent.printStats();
            System.out.println();
        }
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public Player getCurrentOpponent() {
        return currentPlayer == player1 ? player2 : player1;
    }

    public boolean isComputerPlaying() {
        return currentPlayer != null && currentPlayer.isComputer();
    }

    public int getRandomInt(int max) {
        return RANDOM.nextInt(max);
    }

    private Player createPlayer(String characterName) {
        try { //Creates a player with the given name and loads their deck file.
            return new Player(characterName, new DeckReader("./" + characterName + ".txt").read());
        }
        catch (FileNotFoundException ex) {
            throw new InvalidDeckFileException("Failed to load the deck file for " + characterName + ".");
        }
    }

    private Game() {} //Prevent instantiation of singleton class.

    public static final Random RANDOM;
    private static Game instance;

    private boolean isPlaying;
    private Player player1;
    private Player player2;
    private Player currentPlayer;

}