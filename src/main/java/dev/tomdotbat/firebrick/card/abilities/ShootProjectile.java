package dev.tomdotbat.firebrick.card.abilities;

import dev.tomdotbat.firebrick.Player;
import dev.tomdotbat.firebrick.spells.Projectile;

/**
 * An implementation of a card ability that casts the projectile based spell.
 */
public class ShootProjectile extends SpellAbility {
    /**
     * Constructs a projectile spell card ability with the given name and attack damage.
     * @param name the name of the card ability.
     * @param damage the amount of damage the spell deals.
     */
    public ShootProjectile(String name, int damage) {
        super(name, damage);
    }

    /**
     * Performs the card ability as the given player on the provided target.
     * @param player the player to perform the card ability.
     * @param target the opponent to play perform the ability on.
     */
    @Override
    public void play(Player player, Player target) {
        new Projectile(getName(), getDamage())
                .cast(player, target);
    }
}