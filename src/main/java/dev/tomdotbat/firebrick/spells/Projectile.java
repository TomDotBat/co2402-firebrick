package dev.tomdotbat.firebrick.spells;

import dev.tomdotbat.firebrick.Player;
import dev.tomdotbat.firebrick.minions.Minion;
import dev.tomdotbat.firebrick.minions.Wall;
import dev.tomdotbat.firebrick.prompts.RestrictedStringPrompt;

/**
 * An implementation projectile based spells.
 */
public class Projectile extends Spell {
    /**
     * Constructs a projectile spell with a name and attack damage.
     * @param name the name of the projectile.
     * @param damage the amount of damage the projectile does to a player.
     */
    public Projectile(String name, int damage) {
        super(name, damage);
    }

    /**
     * Casts a projectile spell as the given player on the opponent provided.
     * @param caster the player casting the spell.
     * @param opponent the player being attacked.
     */
    @Override
    public void cast(Player caster, Player opponent) {
        //Ask the user who they want to attack.
        RestrictedStringPrompt prompt = new RestrictedStringPrompt("Would you like to attack the opponent or their minions?")
            .withAnswer("Opponent");

        if (opponent.hasMinions()) { //Only allow the user to attack the opponent's minions if they have any.
            prompt.withAnswer("Minions");
        }

        if (opponent.hasWall()) { //If the opponent has a wall provide the option to attack it.
            prompt.withAnswer("Wall");
        }

        switch (prompt.execute()) {
            case "Opponent":
                opponent.removeHealth(getDamage(), true); //Apply the damage directly to the enemy.
                printPlayerDamage(opponent);
                break;
            case "Minions":
                Minion randomMinion = opponent.getRandomMinion();
                randomMinion.removeHealth(getDamage()); //Apply the damage to a random minion.
                printMinionDamage(randomMinion);
                break;
            case "Wall":
                Wall wall = opponent.getWall();
                wall.removeHealth(getDamage()); //Apply the damage the player's wall.
                printMinionDamage(wall);
                break;
        }
    }

    /**
     * Prints the amount of damage done to a player.
     * @param player the player being damaged.
     */
    private void printPlayerDamage(Player player) {
        if (player.getHealth() <= 0) {
            System.out.printf("A %s hits %s for %d damage, killing them instantly.\n",
                    getName(), player.getName(), getDamage());
        }
        else {
            System.out.printf("A %s hits %s for %d damage, leaving them with %d health.\n",
                    getName(), player.getName(), getDamage(), player.getHealth());
        }
    }

    /**
     * Prints the amount of damage done to a minion.
     * @param minion the minion being damaged.
     */
    private void printMinionDamage(Minion minion) {
        if (minion.getHealth() <= 0) {
            System.out.printf("A %s hits the %s of %s for %d damage, killing them instantly.\n",
                    getName(), minion.getName(), minion.getOwner().getName(), getDamage());
        }
        else {
            System.out.printf("A %s hits the %s of %s for %d damage, leaving them with %d health.\n",
                    getName(), minion.getName(), minion.getOwner().getName(), getDamage(), minion.getHealth());
        }
    }
}