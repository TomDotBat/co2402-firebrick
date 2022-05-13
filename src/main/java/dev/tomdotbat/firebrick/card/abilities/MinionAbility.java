package dev.tomdotbat.firebrick.card.abilities;

import dev.tomdotbat.firebrick.Player;
import dev.tomdotbat.firebrick.minions.Minion;

public abstract class MinionAbility extends CardAbility {
    public MinionAbility(String name, int attackPower, int health) {
        super(name);
        this.attackPower = attackPower;
        this.health = health;
    }

    public int getAttackPower() {
        return attackPower;
    }

    public int getHealth() {
        return health;
    }

    @Override
    public void play(Player player, Player target) {
        player.giveMinion(createMinion(player));
    }

    protected abstract Minion createMinion(Player player);

    private final int attackPower;
    private final int health;
}