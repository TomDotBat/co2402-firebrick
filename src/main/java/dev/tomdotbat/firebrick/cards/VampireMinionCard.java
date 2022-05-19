package dev.tomdotbat.firebrick.cards;

import dev.tomdotbat.firebrick.Player;
import dev.tomdotbat.firebrick.minions.Minion;
import dev.tomdotbat.firebrick.minions.VampireMinion;

/**
 * An implementation of a card that gives the player a vampire minion.
 */
public class VampireMinionCard extends MinionCard {
    /**
     * Constructs a vampire minion card with the given name, attack power, health and heal amount.
     * @param name the name of the card.
     * @param attackPower the attack power of the minion.
     * @param health the starting health of the minion.
     * @param healAmount the amount of health minion heals by after each attack.
     */
    public VampireMinionCard(String name, int attackPower, int health, int healAmount) {
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