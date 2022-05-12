package dev.tomdotbat.firebrick.card.abilities;

import dev.tomdotbat.firebrick.Player;
import dev.tomdotbat.firebrick.spells.Lightning;

public class ShootLightning extends CardAbility {
    public ShootLightning(String name, int damage) {
        super(name);
        this.damage = damage;
    }

    @Override
    public void play(Player player) {
        new Lightning(getName(), damage);
    }

    private int damage;
}