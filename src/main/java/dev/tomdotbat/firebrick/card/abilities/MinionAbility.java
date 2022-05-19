package dev.tomdotbat.firebrick.card.abilities;

import dev.tomdotbat.firebrick.Player;
import dev.tomdotbat.firebrick.minions.Minion;

/**
 * An abstract class to represent minion card abilities.
 */
public abstract class MinionAbility extends CardAbility {
    /**
     * Constructs a minion card ability with the given name, attack power and health.
     * @param name the name of the card ability.
     * @param attackPower the attack power of the minion.
     * @param health the starting health of the minion.
     */
    public MinionAbility(String name, int attackPower, int health) {
        super(name);
        this.attackPower = attackPower;
        this.health = health;
    }

    /**
     * Gets the minion's attack power.
     * @return the attack power of the minion.
     */
    public int getAttackPower() {
        return attackPower;
    }

    /**
     * Gets the minion's starting health.
     * @return the starting health of the minion.
     */
    public int getHealth() {
        return health;
    }

    /**
     * Gives the player the minion.
     * @param player the player to perform the card ability.
     * @param target the opponent to play perform the ability on.
     */
    @Override
    public void play(Player player, Player target) {
        player.giveMinion(createMinion(player));
    }

    /**
     * Creates a minion for the player.
     * @param player the owner of the minion being created.
     * @return a minion.
     */
    protected abstract Minion createMinion(Player player);

    private final int attackPower;
    private final int health;
}