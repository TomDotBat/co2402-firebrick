package dev.tomdotbat.firebrick.card.abilities;

import dev.tomdotbat.firebrick.Player;
import dev.tomdotbat.firebrick.minions.Minion;
import dev.tomdotbat.firebrick.minions.VampireMinion;

public class GiveVampireMinion extends MinionAbility {
    public GiveVampireMinion(String name, int attackPower, int health, int healAmount) {
        super(name, attackPower, health);
        this.healAmount = healAmount;
    }

    @Override
    protected Minion createMinion(Player player) {
        return new VampireMinion(player, getName(), getAttackPower(), getHealth(), healAmount);
    }

    private final int healAmount;
}