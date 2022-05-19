package dev.tomdotbat.firebrick.spells;

import dev.tomdotbat.firebrick.Player;

/**
 * An abstract class to represent spells.
 */
public abstract class Spell {
    /**
     * Constructs a spell with a name and attack damage.
     * @param name the name of the spell.
     * @param damage the amount of damage the spell does to a player.
     */
    public Spell(String name, int damage) {
        this.name = name;
        this.damage = damage;
    }

    /**
     * Gets the name of the spell.
     * @return the name of the spell.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the attack damage of the spell.
     * @return the attack damage of the spell.
     */
    public int getDamage() {
        return damage;
    }

    /**
     * Casts the spell as the given player on the opponent provided.
     * @param caster the player casting the spell.
     * @param opponent the player being attacked.
     */
    public abstract void cast(Player caster, Player opponent);

    private final String name;
    private final int damage;
}