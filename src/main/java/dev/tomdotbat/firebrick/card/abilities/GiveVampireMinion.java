package dev.tomdotbat.firebrick.card.abilities;

import dev.tomdotbat.firebrick.Player;
import dev.tomdotbat.firebrick.minions.VampireMinion;

public class GiveVampireMinion extends CardAbility {
    public GiveVampireMinion(String name, int attackPower, int health, int healAmount) {
        super(name);
        this.attackPower = attackPower;
        this.health = health;
        this.healAmount = healAmount;
    }

    @Override
    public void play(Player player) {
        player.giveMinion(new VampireMinion(player, getName(), attackPower, health, healAmount));
    }

    private final int attackPower;
    private final int health;
    private final int healAmount;
}