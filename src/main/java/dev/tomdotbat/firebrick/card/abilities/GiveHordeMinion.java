package dev.tomdotbat.firebrick.card.abilities;

import dev.tomdotbat.firebrick.Player;
import dev.tomdotbat.firebrick.minions.HordeMinion;

public class GiveHordeMinion extends CardAbility {
    public GiveHordeMinion(String name, int attackPower, int health, int attackIncrement) {
        super(name);
        this.attackPower = attackPower;
        this.health = health;
        this.attackIncrement = attackIncrement;
    }

    @Override
    public void play(Player player) {
        player.giveMinion(new HordeMinion(player, getName(), attackPower, health, attackIncrement));
    }

    private final int attackPower;
    private final int health;
    private final int attackIncrement;
}