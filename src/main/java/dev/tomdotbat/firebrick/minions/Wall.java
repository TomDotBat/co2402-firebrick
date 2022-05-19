package dev.tomdotbat.firebrick.minions;

import dev.tomdotbat.firebrick.Player;

/**
 * An implementation of the wall minion.
 */
public class Wall extends Minion {
    /**
     * Constructs a wall minion for a player with the given name, attack power and health.
     * @param owner the owner of the minion.
     * @param name the name of the minion.
     * @param health the minion's starting health.
     */
    public Wall(Player owner, String name, int health) {
        super(owner, name, 0, health);
    }

    /**
     * Prints the amount of damage done to a minion.
     * @param minion the minion being damaged.
     * @param attackPower the attack power the minion was attacked for.
     */
    @Override
    protected void printMinionAttackMessage(Minion minion, int attackPower) {} //The wall cannot attack so we hide attack messages.

    /**
     * Prints the amount of damage done to a player.
     * @param player the player being damaged.
     * @param attackPower the attack power the minion was attacked for.
     */
    @Override
    protected void printPlayerAttackMessage(Player player, int attackPower) {}
}