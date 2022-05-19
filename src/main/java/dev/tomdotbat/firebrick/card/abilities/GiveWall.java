package dev.tomdotbat.firebrick.card.abilities;

import dev.tomdotbat.firebrick.Player;
import dev.tomdotbat.firebrick.minions.Wall;

/**
 * An implementation of a card ability that gives the player a wall minion.
 */
public class GiveWall extends CardAbility {
    /**
     * Constructs a wall minion card ability with the given name and health.
     * @param name the name of the card ability.
     * @param health the starting health of the minion.
     */
    public GiveWall(String name, int health) {
        super(name);
        this.health = health;
    }

    /**
     * Gives the player a wall minion.
     * @param player the player to perform the card ability.
     * @param target the opponent to play perform the ability on.
     */
    @Override
    public void play(Player player, Player target) {
        player.giveMinion(new Wall(player, getName(), health));
    }

    private final int health;
}