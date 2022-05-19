package dev.tomdotbat.firebrick.cards;

import dev.tomdotbat.firebrick.minions.BasicMinion;
import dev.tomdotbat.firebrick.Player;
import dev.tomdotbat.firebrick.minions.Minion;

/**
 * An implementation of a card that gives the player a basic minion.
 */
public class BasicMinionCard extends MinionCard {
    /**
     * Constructs a basic minion card with the given name, attack power and health.
     * @param name the name of the card.
     * @param attackPower the attack power of the minion.
     * @param health the starting health of the minion.
     */
    public BasicMinionCard(String name, int attackPower, int health) {
        super(name, attackPower, health);
    }

    /**
     * Creates a basic minion for the player.
     * @param player the owner of the minion being created.
     * @return a basic minion.
     */
    @Override
    protected Minion createMinion(Player player) {
        return new BasicMinion(player, getName(), getAttackPower(), getHealth());
    }
}