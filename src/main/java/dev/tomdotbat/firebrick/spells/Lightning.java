package dev.tomdotbat.firebrick.spells;

import dev.tomdotbat.firebrick.Player;
import dev.tomdotbat.firebrick.minions.Minion;

/**
 * An implementation of the lightning spell.
 */
public class Lightning extends Spell {
    /**
     * Constructs a lightning spell with a name and attack damage.
     * @param name the name of the spell.
     * @param damage the amount of damage the projectile does to a player.
     */
    public Lightning(String name, int damage) {
        super(name, damage);
    }

    /**
     * Casts a lightning spell as the given player on the opponent provided.
     * @param caster the player casting the spell.
     * @param opponent the player being attacked.
     */
    @Override
    public void cast(Player caster, Player opponent) {
        int damage = getDamage();

        for (int i = 0; i < 2; i++) { //Remove the health from both players and their minions.
            Player player = i == 0 ? caster : opponent;

            player.removeHealth(damage, true);
            printPlayerDamage(player);

            for (Minion minion : player.getMinions()) {
                minion.removeHealth(damage);
                printMinionDamage(minion);
            }
        }
    }

    /**
     * Prints the amount of damage done to a player.
     * @param player the player being damaged.
     */
    private void printPlayerDamage(Player player) {
        if (player.getHealth() <= 0) {
            System.out.printf("Lightning strikes %s for %d damage, killing them instantly.\n",
                    player.getName(), getDamage());
        }
        else {
            System.out.printf("Lightning strikes %s for %d damage, leaving them with %d health.\n",
                    player.getName(), getDamage(), player.getHealth());
        }
    }

    /**
     * Prints the amount of damage done to a minion.
     * @param minion the minion being damaged.
     */
    private void printMinionDamage(Minion minion) {
        if (minion.getHealth() <= 0) {
            System.out.printf("Lightning strikes the %s of %s for %d damage, killing them instantly.\n",
                    minion.getName(), minion.getOwner().getName(), getDamage());
        }
        else {
            System.out.printf("Lightning strikes the %s of %s for %d damage, leaving them with %d health.\n",
                minion.getName(), minion.getOwner().getName(), getDamage(), minion.getHealth());
        }
    }
}