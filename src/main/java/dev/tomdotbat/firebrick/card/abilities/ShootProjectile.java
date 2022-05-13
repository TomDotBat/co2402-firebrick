package dev.tomdotbat.firebrick.card.abilities;

import dev.tomdotbat.firebrick.Player;
import dev.tomdotbat.firebrick.spells.Projectile;

public class ShootProjectile extends SpellAbility {
    public ShootProjectile(String name, int damage) {
        super(name, damage);
    }

    @Override
    public void play(Player player, Player target) {
        new Projectile(getName(), getDamage())
                .cast(player, target);
    }
}