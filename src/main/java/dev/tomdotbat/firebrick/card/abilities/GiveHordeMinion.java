package dev.tomdotbat.firebrick.card.abilities;

import dev.tomdotbat.firebrick.Player;
import dev.tomdotbat.firebrick.minions.HordeMinion;
import dev.tomdotbat.firebrick.minions.Minion;

/**
 * An implementation of a card ability that gives the player a horde minion.
 */
public class GiveHordeMinion extends MinionAbility {
    /**
     * Constructs a horde minion card ability with the given name, attack power, health and attack bonus.
     * @param name the name of the card ability.
     * @param attackPower the attack power of the minion.
     * @param health the starting health of the minion.
     * @param attackIncrement the attack power bonus for each horde minion.
     */
    public GiveHordeMinion(String name, int attackPower, int health, int attackIncrement) {
        super(name, attackPower, health);
        this.attackIncrement = attackIncrement;
    }

    /**
     * Creates a horde minion for the player.
     * @param player the owner of the minion being created.
     * @return a horde minion.
     */
    @Override
    protected Minion createMinion(Player player) {
        return new HordeMinion(player, getName(), getAttackPower(), getHealth(), attackIncrement);
    }

    private final int attackIncrement;
}