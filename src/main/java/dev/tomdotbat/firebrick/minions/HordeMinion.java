package dev.tomdotbat.firebrick.minions;

import dev.tomdotbat.firebrick.Player;

/**
 * An implementation of the horde minion.
 */
public class HordeMinion extends Minion {
    /**
     * Constructs a horde minion for a player with the given name, attack power and health.
     * @param owner the owner of the minion.
     * @param name the name of the minion.
     * @param attackPower the minion's attack power.
     * @param health the minion's starting health.
     * @param attackIncrement the attack power bonus for each horde minion.
     */
    public HordeMinion(Player owner, String name, int attackPower, int health, int attackIncrement) {
        super(owner, name, attackPower, health);
        this.attackIncrement = attackIncrement;
    }

    /**
     * Gets the minion's attack power.
     * @return the minion's attack power.
     */
    @Override
    public int getAttackPower() {
        int attackPower = 0;

        for (Minion minion : getOwner().getMinions()) {
            if (minion instanceof HordeMinion) {
                //Increase the attack power by 1 for every horde minion the player owns.
                attackPower += attackIncrement;
            }
        }

        return attackPower;
    }

    private final int attackIncrement;
}