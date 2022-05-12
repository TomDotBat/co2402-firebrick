package dev.tomdotbat.firebrick;

import dev.tomdotbat.firebrick.card.Card;
import dev.tomdotbat.firebrick.exceptions.EmptyDeckException;
import dev.tomdotbat.firebrick.minions.Minion;
import dev.tomdotbat.firebrick.minions.Wall;
import dev.tomdotbat.firebrick.prompts.NumberedStringPrompt;
import dev.tomdotbat.firebrick.prompts.Prompt;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Player {
    public Player(String name, Stack<Card> deck) {
        this.name = name;
        this.deck = deck;
    }

    public int getHealth() {
        return health;
    }

    public String getName() {
        return name;
    }

    public void addHealth(int amount) {
        health += amount;
    }

    public void removeHealth(int amount, boolean playerOnly) {
        if (playerOnly || minions.size() == 0) {
            health -= amount;

            if (health <= 0) { //We're dead, finish the game.
                Game.getInstance().finish();
            }
        } else {
            getRandomMinion().removeHealth(health);
        }
    }

    public void removeHealth(int amount) {
        removeHealth(amount, false);
    }

    public boolean isDead() {
        return health <= 0;
    }

    public void setIsComputer(boolean computer) {
        isComputer = computer;
    }

    public List<Minion> getMinions() {
        return minions;
    }

    public void giveMinion(Minion minion) {
        minions.add(minion);
    }

    public void killMinion(Minion minion) {
        minions.remove(minion);
    }

    public Minion getRandomMinion() {
        //Get a random minion from the minions list.
        Minion randomMinion = minions.get(Game.getInstance().getRandomInt(minions.size()));

        if (randomMinion instanceof Wall) { //If the minion is a wall ignore it and get a new random minion.
            return getRandomMinion();
        }

        return randomMinion;
    }

    public void minionAttack(Player target) {
        for (Minion minion : minions) {
            minion.attackPlayer(target);
        }
    }

    public Wall getWall() {
        for (Minion minion : minions) {
            //Search for a wall in the player's minions and return it if it exists.
            if (minion instanceof Wall) {
                return (Wall) minion;
            }
        }

        return null;
    }

    public boolean hasWall() {
        return getWall() != null; //If the player's wall is null they don't have one.
    }

    public boolean hasMinions() {
        for (Minion minion : minions) {
            //The player must own at least one minion that isn't a wall to be
            //considered as having minions.
            if (!(minion instanceof Wall)) {
                return true;
            }
        }

        return false;
    }

    public List<String> getMinionNames() {
        List<String> minionNames = new ArrayList<>();

        for (Minion minion : minions) { //Loop through our minions and add their names to the list.
            minionNames.add(minion.getName());
        }

        return minionNames;
    }

    public List<String> getCardNames() {
        List<String> cardNames = new ArrayList<>();

        for (Card card : hand) { //Loop through our hand and add the names of our cards to the list.
            cardNames.add(card.getName());
        }

        return cardNames;
    }

    public void drawCard() {
        Card card = deck.pop(); //Get the next card from the deck and add it to our hand.

        if (card == null) {
            throw new EmptyDeckException(name + " attempted to draw a card from their deck but it is empty.");
        }

        hand.add(card);

        System.out.println(name + " draws " + card.getName());
    }

    public void playCard() {
        //Prompt the user to play one of their cards.
        Prompt<Integer> prompt = new NumberedStringPrompt("Which card would you like to play?")
            .withAnswers(getCardNames());

        int cardNo = prompt.execute();

        Card card = hand.get(cardNo);

        System.out.println(name + " plays " + card.getName());
        card.play(this); //Play the card and remove it from the player's hand.

        hand.remove(cardNo);
    }

    public void printStats() {
        System.out.println(name + " Statistics");
        System.out.println("----------------------");
        System.out.println("Health: " + health);
        System.out.println("Cards on table: " + String.join(", ", getCardNames()));
    }

    private final String name;
    private int health = 30;
    private List<Card> hand = new ArrayList<>();
    private Stack<Card> deck;
    private List<Minion> minions = new ArrayList<>();
    private boolean isComputer;
}