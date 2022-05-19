package dev.tomdotbat.firebrick;

import dev.tomdotbat.firebrick.exceptions.InvalidDeckFileException;
import dev.tomdotbat.firebrick.prompts.Prompt;
import dev.tomdotbat.firebrick.prompts.RestrictedStringPrompt;
import dev.tomdotbat.firebrick.prompts.StringPrompt;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

/**
 * Singleton class to handle the game creation, completion and turns.
 */
public class Game {
    /*
     * Loads the random seed and instantiates a random number generator.
     */
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

    /**
     * Gets the singleton instance of the class.
     * @return the singleton instance.
     */
    public static Game getInstance() { //Singleton instance getter.
        if (instance == null) {
            instance = new Game();
        }

        return instance;
    }

    /**
     * Starts the game and prompts the user for their preferred character and
     * opponent AI state.
     */
    public void start() {
        String characterSelection = new RestrictedStringPrompt("Which character would you like to play as?")
                .withAnswer("Sorceress")
                .withAnswer("Wizard")
                .withDefaultAnswer("Sorceress")
                .execute();

        boolean shouldShuffle = new RestrictedStringPrompt("Would you like the decks to be shuffled?")
                .withAnswer("Yes")
                .withAnswer("No")
                .withDefaultAnswer("Yes")
                .execute().equals("Yes");

        //Create the players based on the user's preference.
        player1 = createPlayer(characterSelection, shouldShuffle);
        player2 = createPlayer(characterSelection.equals("Sorceress") ? "Wizard" : "Sorceress", shouldShuffle);

        StringPrompt prompt = new RestrictedStringPrompt("Would you like player 2 to be an AI?")
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

    /**
     * Prints the game outcome and finishes the program.
     */
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

    /**
     * Performs the logic for each turn in the game.
     * @param roundNo the current round number.
     */
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

    /**
     * Gets the opponent for the current player.
     * @return the opponent.
     */
    public Player getCurrentOpponent() {
        return currentPlayer == player1 ? player2 : player1;
    }

    /**
     * Gets whether the AI is currently playing their turn.
     * @return whether the AI is currently playing their turn.
     */
    public boolean isComputerPlaying() {
        return currentPlayer != null && currentPlayer.isComputer();
    }

    /**
     * Gets a random integer using the random seed.
     * @param max the maximum value.
     * @return a random integer.
     */
    public int getRandomInt(int max) {
        return RANDOM.nextInt(max);
    }

    /**
     * Creates an instance of the player class for the given character name.
     * @param characterName the name of the player's character.
     * @param shouldShuffle whether the deck should be shuffled or not.
     * @return a player.
     */
    private Player createPlayer(String characterName, boolean shouldShuffle) {
        try { //Creates a player with the given name and loads their deck file.
            DeckReader deckReader = new DeckReader("./" + characterName + ".txt");
            deckReader.setShouldShuffle(shouldShuffle);
            return new Player(characterName, deckReader.read());
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