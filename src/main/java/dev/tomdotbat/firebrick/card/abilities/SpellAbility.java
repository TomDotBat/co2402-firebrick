package dev.tomdotbat.firebrick.card.abilities;

public abstract class SpellAbility extends CardAbility {
    public SpellAbility(String name, int damage) {
        super(name);
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }

    private final int damage;
}