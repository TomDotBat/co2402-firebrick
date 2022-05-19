package dev.tomdotbat.firebrick.minions;

import dev.tomdotbat.firebrick.Player;

/**
 * An implementation of the basic minion.
 */
public class BasicMinion extends Minion {
    /**
     * Constructs a basic minion for a player with the given name, attack power and health.
     * @param owner the owner of the minion.
     * @param name the name of the minion.
     * @param attackPower the minion's attack power.
     * @param health the minion's starting health.
     */
    public BasicMinion(Player owner, String name, int attackPower, int health) {
        super(owner, name, attackPower, health);
    }
}