package dev.tomdotbat.firebrick.spells;

import dev.tomdotbat.firebrick.Game;
import dev.tomdotbat.firebrick.Player;
import dev.tomdotbat.firebrick.minions.Minion;

public class Bless extends Spell {
    public Bless(String name, int damage, int healAmount) {
        super(name, damage);
        this.healAmount = healAmount;
    }

    @Override
    public void cast(Player caster, Player opponent) {
        Game game = Game.getInstance();

        boolean targetingOpponent = game.getRandomInt(2) == 1; //Randomly select a target.
        Player targetPlayer = targetingOpponent ? opponent : caster;

        //Select a random target.
        int max = targetPlayer.getMinions().size() + 1;
        int random = game.getRandomInt(max);

        //If the max is 1 the player has no minions, so we target them directly.
        //If the random is equal to the max value we're attacking the player.
        if (max == 1 || random == max - 1) {
            if (targetingOpponent) {
                targetPlayer.removeHealth(getDamage());
                printPlayerDamage(targetPlayer);
            }
            else {
                targetPlayer.addHealth(healAmount);
                printPlayerHeal(targetPlayer);
            }
        }
        else { //Attack the randomly selected minion.
            Minion randomMinion = targetPlayer.getMinions().get(random);
            if (targetingOpponent) {
                randomMinion.removeHealth(getDamage());
                printMinionDamage(randomMinion);
            }
            else {
                randomMinion.addHealth(healAmount);
                printMinionHeal(randomMinion);
            }
        }
    }

    private void printPlayerHeal(Player player) {
        System.out.printf("%s was healed for %d from the %s spell, leaving them with %d health.\n",
                player.getName(), healAmount, getName(), player.getHealth());
    }

    private void printMinionHeal(Minion minion) {
        System.out.printf("The %s of %s was healed for %d from the %s spell, leaving them with %d health.\n",
                minion.getName(), minion.getOwner().getName(), healAmount, getName(), minion.getHealth());
    }

    private void printPlayerDamage(Player player) {
        if (player.getHealth() <= 0) {
            System.out.printf("%s was used on %s for %d damage, killing them instantly.\n",
                    getName(), player.getName(), getDamage());
        }
        else {
            System.out.printf("%s was used on %s for %d damage, leaving them with %d health.\n",
                    getName(), player.getName(), getDamage(), player.getHealth());
        }
    }

    private void printMinionDamage(Minion minion) {
        if (minion.getHealth() <= 0) {
            System.out.printf("%s was used on the %s of %s for %d damage, killing them instantly.\n",
                    getName(), minion.getName(), minion.getOwner().getName(), getDamage());
        }
        else {
            System.out.printf("%s was used on the %s of %s for %d damage, leaving them with %d health.\n",
                    getName(), minion.getName(), minion.getOwner().getName(), getDamage(), minion.getHealth());
        }
    }

    private final int healAmount;
}