package dev.tomdotbat.firebrick.cards;

import dev.tomdotbat.firebrick.Player;
import dev.tomdotbat.firebrick.minions.Wall;

/**
 * An implementation of a card that gives the player a wall minion.
 */
public class WallCard extends Card {
    /**
     * Constructs a wall minion card with the given name and health.
     * @param name the name of the card.
     * @param health the starting health of the minion.
     */
    public WallCard(String name, int health) {
        super(name);
        this.health = health;
    }

    /**
     * Gives the player a wall minion.
     * @param player the player playing the card.
     * @param target the opponent to play the card against.
     */
    @Override
    public void play(Player player, Player target) {
        player.giveMinion(new Wall(player, getName(), health));
    }

    private final int health;
}