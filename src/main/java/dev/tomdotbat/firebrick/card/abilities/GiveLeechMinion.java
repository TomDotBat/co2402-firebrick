package dev.tomdotbat.firebrick.card.abilities;

import dev.tomdotbat.firebrick.Player;
import dev.tomdotbat.firebrick.minions.LeechMinion;
import dev.tomdotbat.firebrick.minions.Minion;

/**
 * An implementation of a card ability that gives the player a leech minion.
 */
public class GiveLeechMinion extends MinionAbility {
    /**
     * Constructs a leech minion card ability with the given name, attack power, health and heal amount.
     * @param name the name of the card ability.
     * @param attackPower the attack power of the minion.
     * @param health the starting health of the minion.
     * @param playerHealAmount the amount of health the owner should be healed by after each attack.
     */
    public GiveLeechMinion(String name, int attackPower, int health, int playerHealAmount) {
        super(name, attackPower, health);
        this.playerHealAmount = playerHealAmount;
    }

    /**
     * Creates a leech minion for the player.
     * @param player the owner of the minion being created.
     * @return a leech minion.
     */
    @Override
    protected Minion createMinion(Player player) {
        return new LeechMinion(player, getName(), getAttackPower(), getHealth(), playerHealAmount);
    }

    private final int playerHealAmount;
}