package dev.tomdotbat.firebrick.card.abilities;

import dev.tomdotbat.firebrick.Player;
import dev.tomdotbat.firebrick.minions.Minion;
import dev.tomdotbat.firebrick.minions.TrampleMinion;

/**
 * An implementation of a card ability that gives the player a trample minion.
 */
public class GiveTrampleMinion extends MinionAbility {
    /**
     * Constructs a trample minion card ability with the given name, attack power and health.
     * @param name the name of the card ability.
     * @param attackPower the attack power of the minion.
     * @param health the starting health of the minion.
     */
    public GiveTrampleMinion(String name, int attackPower, int health) {
        super(name, attackPower, health);
    }

    /**
     * Creates a trample minion for the player.
     * @param player the owner of the minion being created.
     * @return a trample minion.
     */
    @Override
    protected Minion createMinion(Player player) {
        return new TrampleMinion(player, getName(), getAttackPower(), getHealth());
    }
}