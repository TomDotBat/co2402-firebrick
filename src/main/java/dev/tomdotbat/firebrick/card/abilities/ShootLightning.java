package dev.tomdotbat.firebrick.card.abilities;

import dev.tomdotbat.firebrick.Player;
import dev.tomdotbat.firebrick.spells.Lightning;

public class ShootLightning extends SpellAbility {
    public ShootLightning(String name, int damage) {
        super(name, damage);
    }

    @Override
    public void play(Player player, Player target) {
        new Lightning(getName(), getDamage())
                .cast(player, target);
    }
}