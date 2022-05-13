package dev.tomdotbat.firebrick.card.abilities;

import dev.tomdotbat.firebrick.Player;
import dev.tomdotbat.firebrick.spells.Bless;

public class BlessTarget extends SpellAbility {
    public BlessTarget(String name, int damage, int healAmount) {
        super(name, damage);
        this.healAmount = healAmount;
    }

    @Override
    public void play(Player player, Player target) {
        new Bless(getName(), getDamage(), healAmount)
                .cast(player, target);
    }

    private final int healAmount;
}