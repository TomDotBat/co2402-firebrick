package dev.tomdotbat.firebrick.card.abilities;

import dev.tomdotbat.firebrick.Player;
import dev.tomdotbat.firebrick.minions.Wall;

public class GiveWall extends CardAbility {
    public GiveWall(String name, int health) {
        super(name);
        this.health = health;
    }

    @Override
    public void play(Player player) {
        player.giveMinion(new Wall(player, getName(), health));
    }

    private final int health;
}