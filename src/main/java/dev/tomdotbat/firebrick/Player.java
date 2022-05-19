package dev.tomdotbat.firebrick;

import dev.tomdotbat.firebrick.card.Card;
import dev.tomdotbat.firebrick.minions.Minion;
import dev.tomdotbat.firebrick.minions.Wall;
import dev.tomdotbat.firebrick.prompts.NumberedStringPrompt;
import dev.tomdotbat.firebrick.prompts.Prompt;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * The class to represent players of the game.
 */
public class Player {
    /**
     * Constructs a player with a name and card deck.
     * @param name the name of the player, eg: Wizard or Sorceress.
     * @param deck the deck of cards to give the player.
     */
    public Player(String name, Stack<Card> deck) {
        this.name = name;
        this.deck = deck;
    }

    /**
     * Gets the player's current health.
     * @return the player's health.
     */
    public int getHealth() {
        return health;
    }

    /**
     * Gets the player's name.
     * @return the player's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Adds the given amount of health to the player.
     * @param amount the amount of health to give the player.
     */
    public void addHealth(int amount) {
        health += amount;
    }

    /**
     * Removes the given amount of health from the player. When the player has at least one minion, the health will
     * be taken from a random minion.
     * @param amount the amount of health to take from the player.
     * @param playerOnly whether we should directly attack the player and avoid their minions.
     */
    public void removeHealth(int amount, boolean playerOnly) {
        if (playerOnly || !hasMinions()) {
            health -= amount;

            if (health <= 0) { //We're dead, finish the game.
                Game.getInstance().finish();
            }
        }
        else {
            getRandomMinion().removeHealth(health);
        }
    }

    /**
     * Removes health from the player. When the player has at least one minion, the health will
     * be taken from a random minion.
     * @param amount the amount of health to take from the player.
     */
    public void removeHealth(int amount) {
        removeHealth(amount, false);
    }

    /**
     * Gets whether the player is dead or not.
     * @return whether the player is dead or not.
     */
    public boolean isDead() {
        return health <= 0;
    }

    /**
     * Gets the AI state of the player.
     * @return the AI state of the player.
     */
    public boolean isComputer() {
        return isComputer;
    }

    /**
     * Sets the AI state of the player.
     * @param computer the preferred AI state.
     */
    public void setIsComputer(boolean computer) {
        isComputer = computer;
    }

    /**
     * Gets the player's minions.
     * @return the player's minions.
     */
    public List<Minion> getMinions() {
        return minions;
    }

    /**
     * Gives the player a minion.
     * @param minion the minion to give.
     */
    public void giveMinion(Minion minion) {
        minions.add(minion);
    }

    /**
     * Checks the player's minions and clears out any dead ones.
     */
    public void clearDeadMinions() { //Removes any dead minions from the player's minion list.
        List<Minion> deadMinions = new ArrayList<>();

        for (Minion minion : minions) {
            if (minion.isDead()) {
                deadMinions.add(minion);
            }
        }

        minions.removeAll(deadMinions);
    }

    /**
     * Gets one of the player's minions at random.
     * @return a random minion.
     */
    public Minion getRandomMinion() {
        //Get a random minion from the minions list.
        Minion randomMinion = minions.get(Game.getInstance().getRandomInt(minions.size()));

        if (randomMinion instanceof Wall) { //If the minion is a wall ignore it and get a new random minion.
            return getRandomMinion();
        }

        return randomMinion;
    }

    /**
     * Orders the player's minions to attack.
     * @param target the player to attack.
     */
    public void minionAttack(Player target) {
        for (Minion minion : minions) {
            minion.attackPlayer(target);
        }
    }

    /**
     * Gets the player's first wall minion if they have one.
     * @return the player's wall minion.
     */
    public Wall getWall() {
        for (Minion minion : minions) {
            //Search for a wall in the player's minions and return it if it exists.
            if (minion instanceof Wall) {
                return (Wall) minion;
            }
        }

        return null;
    }

    /**
     * Gets whether the player has a wall minion or not.
     * @return whether the player has a wall or not.
     */
    public boolean hasWall() {
        return getWall() != null; //If the player's wall is null they don't have one.
    }

    /**
     * Gets whether the player has any minions or not.
     * @return whether the palyer has minions or not.
     */
    public boolean hasMinions() {
        for (Minion minion : minions) {
            //The player must own at least one minion that is alive and isn't a wall
            //to be considered as having minions.
            if (!(minion.isDead() || minion instanceof Wall)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Gets a list of the player's minion names.
     * @return the player's minion names.
     */
    public List<String> getMinionNames() {
        List<String> minionNames = new ArrayList<>();

        for (Minion minion : minions) { //Loop through our minions and add their names to the list.
            minionNames.add(minion.getName());
        }

        return minionNames;
    }

    /**
     * Gets a list of the player's card names.
     * @return the player's card names.
     */
    public List<String> getCardNames() {
        List<String> cardNames = new ArrayList<>();

        for (Card card : hand) { //Loop through our hand and add the names of our cards to the list.
            cardNames.add(card.getName());
        }

        return cardNames;
    }

    /**
     * Draws a card from the player's deck and puts it in their hand.
     */
    public void drawCard() {
        Card card = deck.pop(); //Get the next card from the deck and add it to our hand.
        hand.add(card);
        System.out.println(name + " draws " + card.getName());
    }

    /**
     * Prompts the player to play one of the cards in their hand.
     */
    public void playCard() {
        //Prompt the user to play one of their cards.
        Prompt<Integer> prompt = new NumberedStringPrompt("Which card would you like to play?")
            .withAnswers(getCardNames());

        int cardNo = prompt.execute();

        Card card = hand.get(cardNo);

        System.out.println(name + " plays " + card.getName());
        card.play(this, Game.getInstance().getCurrentOpponent()); //Play the card and remove it from the player's hand.

        hand.remove(cardNo);
        clearDeadMinions();
    }

    /**
     * Prints the player's health and any cards they have on the table.
     */
    public void printStats() {
        System.out.println(name + " Statistics");
        System.out.println("----------------------");
        System.out.println("Health: " + health);
        System.out.println("Cards on table: " + String.join(", ", getMinionNames()));
    }

    private final String name;
    private int health = 30;
    private final List<Card> hand = new ArrayList<>();
    private final Stack<Card> deck;
    private final List<Minion> minions = new ArrayList<>();
    private boolean isComputer;
}