package dev.tomdotbat.firebrick.card.abilities;

import dev.tomdotbat.firebrick.Player;
import dev.tomdotbat.firebrick.minions.TrampleMinion;

public class GiveTrampleMinion extends CardAbility {
    public GiveTrampleMinion(String name, int attackPower, int health) {
        super(name);
        this.attackPower = attackPower;
        this.health = health;
    }

    @Override
    public void play(Player player) {
        player.giveMinion(new TrampleMinion(player, getName(), attackPower, health));
    }

    private final int attackPower;
    private final int health;
}