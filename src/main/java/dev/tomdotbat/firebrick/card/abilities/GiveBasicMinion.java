package dev.tomdotbat.firebrick.card.abilities;

import dev.tomdotbat.firebrick.minions.BasicMinion;
import dev.tomdotbat.firebrick.Player;
import dev.tomdotbat.firebrick.minions.Minion;

public class GiveBasicMinion extends MinionAbility {
    public GiveBasicMinion(String name, int attackPower, int health) {
        super(name, attackPower, health);
    }

    @Override
    protected Minion createMinion(Player player) {
        return new BasicMinion(player, getName(), getAttackPower(), getHealth());
    }
}