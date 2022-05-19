package dev.tomdotbat.firebrick.card.abilities;

import dev.tomdotbat.firebrick.Player;
import dev.tomdotbat.firebrick.minions.Minion;
import dev.tomdotbat.firebrick.minions.VampireMinion;

/**
 * An implementation of a card ability that gives the player a vampire minion.
 */
public class GiveVampireMinion extends MinionAbility {
    /**
     * Constructs a vampire minion card ability with the given name, attack power, health and heal amount.
     * @param name the name of the card ability.
     * @param attackPower the attack power of the minion.
     * @param health the starting health of the minion.
     * @param healAmount the amount of health minion heals by after each attack.
     */
    public GiveVampireMinion(String name, int attackPower, int health, int healAmount) {
        super(name, attackPower, health);
        this.healAmount = healAmount;
    }

    /**
     * Creates a vampire minion for the player.
     * @param player the owner of the minion being created.
     * @return a vampire minion.
     */
    @Override
    protected Minion createMinion(Player player) {
        return new VampireMinion(player, getName(), getAttackPower(), getHealth(), healAmount);
    }

    private final int healAmount;
}