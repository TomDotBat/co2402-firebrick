package dev.tomdotbat.firebrick.cards;

/**
 * An abstract class to represent spell cards.
 */
public abstract class SpellCard extends Card {
    /**
     * Constructs a spell card with the given name and attack damage.
     * @param name the name of the card.
     * @param damage the amount of damage the spell deals.
     */
    public SpellCard(String name, int damage) {
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