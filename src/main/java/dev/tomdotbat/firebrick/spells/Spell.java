package dev.tomdotbat.firebrick.spells;

import dev.tomdotbat.firebrick.Player;

public abstract class Spell {
    public Spell(String name, int damage) {
        this.name = name;
        this.damage = damage;
    }

    public String getName() {
        return name;
    }

    public int getDamage() {
        return damage;
    }

    abstract void cast(Player caster, Player opponent);

    private final String name;
    private final int damage;
}