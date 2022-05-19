package dev.tomdotbat.firebrick.card.abilities;

import dev.tomdotbat.firebrick.Player;

/**
 * An abstract class to represent card abilities.
 */
public abstract class CardAbility {
    /**
     * Constructs a card ability with the given name.
     * @param name the name of the card ability.
     */
    public CardAbility(String name) {
        this.name = name;
    }

    /**
     * Gets the name of the card ability.
     * @return the name of the card ability.
     */
    public String getName() {
        return name;
    }

    /**
     * Performs the card ability as the given player on the provided target.
     * @param player the player to perform the card ability.
     * @param target the opponent to play perform the ability on.
     */
    public abstract void play(Player player, Player target);

    private final String name;
}