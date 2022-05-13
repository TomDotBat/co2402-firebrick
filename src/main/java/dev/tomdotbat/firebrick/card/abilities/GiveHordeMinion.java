package dev.tomdotbat.firebrick.card.abilities;

import dev.tomdotbat.firebrick.Player;
import dev.tomdotbat.firebrick.minions.HordeMinion;
import dev.tomdotbat.firebrick.minions.Minion;

public class GiveHordeMinion extends MinionAbility {
    public GiveHordeMinion(String name, int attackPower, int health, int attackIncrement) {
        super(name, attackPower, health);
        this.attackIncrement = attackIncrement;
    }

    @Override
    protected Minion createMinion(Player player) {
        return new HordeMinion(player, getName(), getAttackPower(), getHealth(), attackIncrement);
    }

    private final int attackIncrement;
}