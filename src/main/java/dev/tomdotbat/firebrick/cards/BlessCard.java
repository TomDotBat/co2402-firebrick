package dev.tomdotbat.firebrick.cards;

import dev.tomdotbat.firebrick.Player;
import dev.tomdotbat.firebrick.spells.Bless;

/**
 * An implementation of a card that casts the bless spell.
 */
public class BlessCard extends SpellCard {
    /**
     * Constructs a bless spell card with the given name and attack damage.
     * @param name the name of the card.
     * @param damage the amount of damage the spell deals.
     * @param healAmount the amount of damage the projectile does to a player.
     */
    public BlessCard(String name, int damage, int healAmount) {
        super(name, damage);
        this.healAmount = healAmount;
    }

    /**
     * Casts the bless spell as the given player.
     * @param player the player playing the card.
     * @param target the opponent to play the card against.
     */
    @Override
    public void play(Player player, Player target) {
        new Bless(getName(), getDamage(), healAmount)
                .cast(player, target);
    }

    private final int healAmount;
}