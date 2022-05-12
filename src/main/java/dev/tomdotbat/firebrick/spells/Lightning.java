package dev.tomdotbat.firebrick.spells;

import dev.tomdotbat.firebrick.Player;
import dev.tomdotbat.firebrick.minions.Minion;

public class Lightning extends Spell {
    public Lightning(String name, int damage) {
        super(name, damage);
    }

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