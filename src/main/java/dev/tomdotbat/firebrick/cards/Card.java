package dev.tomdotbat.firebrick.cards;

import dev.tomdotbat.firebrick.Player;

/**
 * Represents a playable card.
 */
public abstract class Card {
    /**
     * Constructs a card with the given name.
     * @param name the name of the card.
     */
    public Card(String name) {
        this.name = name;
    }

    /**
     * Gets the name of the card.
     * @return the name of the card.
     */
    public String getName() {
        return name;
    }

    /**
     * Plays the card as the given player on the provided target.
     * @param player the player playing the card.
     * @param target the opponent to play the card against.
     */
    public abstract void play(Player player, Player target);

    private final String name;
}