package dev.tomdotbat.firebrick.card.abilities;

import dev.tomdotbat.firebrick.Player;
import dev.tomdotbat.firebrick.spells.Bless;

public class BlessTarget extends CardAbility {
    public BlessTarget(String name, int damage, int healAmount) {
        super(name);
        this.damage = damage;
        this.healAmount = healAmount;
    }

    @Override
    public void play(Player player) {
        new Bless(getName(), damage, healAmount);
    }

    private final int damage;
    private final int healAmount;
}