package dev.tomdotbat.firebrick.minions;

import dev.tomdotbat.firebrick.Player;

public class HordeMinion extends Minion {
    public HordeMinion(Player owner, String name, int attackPower, int health, int attackIncrement) {
        super(owner, name, attackPower, health);
        this.attackIncrement = attackIncrement;
    }

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

    private int attackIncrement;
}