package dev.tomdotbat.firebrick.card.abilities;

import dev.tomdotbat.firebrick.Player;
import dev.tomdotbat.firebrick.spells.Bless;

/**
 * An implementation of a card ability that casts the bless spell.
 */
public class BlessTarget extends SpellAbility {
    /**
     * Constructs a bless spell card ability with the given name and attack damage.
     * @param name the name of the card ability.
     * @param damage the amount of damage the spell deals.
     * @param healAmount the amount of damage the projectile does to a player.
     */
    public BlessTarget(String name, int damage, int healAmount) {
        super(name, damage);
        this.healAmount = healAmount;
    }

    /**
     * Performs the card ability as the given player on the provided target.
     * @param player the player to perform the card ability.
     * @param target the opponent to play perform the ability on.
     */
    @Override
    public void play(Player player, Player target) {
        new Bless(getName(), getDamage(), healAmount)
                .cast(player, target);
    }

    private final int healAmount;
}