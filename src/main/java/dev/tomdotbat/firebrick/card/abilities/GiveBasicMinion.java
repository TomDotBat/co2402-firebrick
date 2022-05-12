package dev.tomdotbat.firebrick.card.abilities;

import dev.tomdotbat.firebrick.minions.BasicMinion;
import dev.tomdotbat.firebrick.Player;

public class GiveBasicMinion extends CardAbility {
    public GiveBasicMinion(String name, int attackPower, int health) {
        super(name);
        this.attackPower = attackPower;
        this.health = health;
    }

    @Override
    public void play(Player player) {
        player.giveMinion(new BasicMinion(player, getName(), attackPower, health));
    }

    private final int attackPower;
    private final int health;
}