package dev.tomdotbat.firebrick.card.abilities;

import dev.tomdotbat.firebrick.minions.BasicMinion;
import dev.tomdotbat.firebrick.Player;
import dev.tomdotbat.firebrick.minions.Minion;

/**
 * An implementation of a card ability that gives the player a basic minion.
 */
public class GiveBasicMinion extends MinionAbility {
    /**
     * Constructs a basic minion card ability with the given name, attack power and health.
     * @param name the name of the card ability.
     * @param attackPower the attack power of the minion.
     * @param health the starting health of the minion.
     */
    public GiveBasicMinion(String name, int attackPower, int health) {
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