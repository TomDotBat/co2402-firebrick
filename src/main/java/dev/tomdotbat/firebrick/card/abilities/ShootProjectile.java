package dev.tomdotbat.firebrick.card.abilities;

import dev.tomdotbat.firebrick.Player;
import dev.tomdotbat.firebrick.spells.Projectile;

public class ShootProjectile extends CardAbility {
    public ShootProjectile(String name, int damage) {
        super(name);
        this.damage = damage;
    }

    @Override
    public void play(Player player) {
        new Projectile(getName(), damage);
    }

    private final int damage;
}