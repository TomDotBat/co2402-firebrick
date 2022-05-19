package dev.tomdotbat.firebrick.cards;

import dev.tomdotbat.firebrick.Player;
import dev.tomdotbat.firebrick.spells.Lightning;

/**
 * An implementation of a card that casts the lightning spell.
 */
public class LightningCard extends SpellCard {
    /**
     * Constructs a lightning spell card with the given name and attack damage.
     * @param name the name of the card.
     * @param damage the amount of damage the spell deals.
     */
    public LightningCard(String name, int damage) {
        super(name, damage);
    }

    /**
     * Casts the lightning spell as the given player.
     * @param player the player playing the card.
     * @param target the opponent to play the card against.
     */
    @Override
    public void play(Player player, Player target) {
        new Lightning(getName(), getDamage())
                .cast(player, target);
    }
}