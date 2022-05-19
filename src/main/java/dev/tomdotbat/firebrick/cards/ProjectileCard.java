package dev.tomdotbat.firebrick.cards;

import dev.tomdotbat.firebrick.Player;
import dev.tomdotbat.firebrick.spells.Projectile;

/**
 * An implementation of a card that casts a projectile based spell.
 */
public class ProjectileCard extends SpellCard {
    /**
     * Constructs a projectile spell card with the given name and attack damage.
     * @param name the name of the card.
     * @param damage the amount of damage the spell deals.
     */
    public ProjectileCard(String name, int damage) {
        super(name, damage);
    }

    /**
     * Casts a projectile spell as the player on the given target.
     * @param player the player playing the card.
     * @param target the opponent to play the card against.
     */
    @Override
    public void play(Player player, Player target) {
        new Projectile(getName(), getDamage())
                .cast(player, target);
    }
}