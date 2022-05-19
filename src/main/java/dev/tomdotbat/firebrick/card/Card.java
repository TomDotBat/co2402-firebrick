package dev.tomdotbat.firebrick.card;

import dev.tomdotbat.firebrick.Player;
import dev.tomdotbat.firebrick.card.abilities.CardAbility;

/**
 * Represents a playable card.
 */
public class Card {
    /**
     * Constructs a card with the given name and ability.
     * @param name the name of the card.
     * @param ability the card's ability.
     */
    public Card(String name, CardAbility ability) {
        this.name = name;
        this.ability = ability;
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
     * @param target the player the card should target.
     */
    public void play(Player player, Player target) {
        ability.play(player, target);
    }

    private final String name;
    private final CardAbility ability;
}