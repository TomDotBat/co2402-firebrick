package dev.tomdotbat.firebrick.card.abilities;

/**
 * An abstract class to represent spell card abilities.
 */
public abstract class SpellAbility extends CardAbility {
    /**
     * Constructs a spell card ability with the given name and attack damage.
     * @param name the name of the card ability.
     * @param damage the amount of damage the spell deals.
     */
    public SpellAbility(String name, int damage) {
        super(name);
        this.damage = damage;
    }

    /**
     * Gets the amount of attack damage the spell deals.
     * @return the amount of attack damage the spell deals.
     */
    public int getDamage() {
        return damage;
    }

    private final int damage;
}