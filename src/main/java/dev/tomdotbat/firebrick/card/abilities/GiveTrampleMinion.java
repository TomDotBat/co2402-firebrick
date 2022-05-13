package dev.tomdotbat.firebrick.card.abilities;

import dev.tomdotbat.firebrick.Player;
import dev.tomdotbat.firebrick.minions.Minion;
import dev.tomdotbat.firebrick.minions.TrampleMinion;

public class GiveTrampleMinion extends MinionAbility {
    public GiveTrampleMinion(String name, int attackPower, int health) {
        super(name, attackPower, health);
    }

    @Override
    protected Minion createMinion(Player player) {
        return new TrampleMinion(player, getName(), getAttackPower(), getHealth());
    }
}