package dev.tomdotbat.firebrick.minions;

import dev.tomdotbat.firebrick.Player;

public class TrampleMinion extends Minion {
    public TrampleMinion(Player owner, String name, int attackPower, int health) {
        super(owner, name, attackPower, health);
    }

    @Override
    public void attackPlayer(Player player) {
        int attackPower;
        if (player.hasMinions()) {
            attackPower = attackPlayerMinions(player);

            if (attackPower == 0) {
                return; //No attack power left, don't bother doing anything.
            }
        }
        else {
            attackPower = getAttackPower();
        }

        int powerBefore = attackPower;
        player.removeHealth(attackPower);
        printPlayerAttackMessage(player, powerBefore);
    }

    @Override
    public int attackPlayerMinions(Player player) {
        int attackPower = getAttackPower();

        if (player.hasMinions()) {
            //Randomly attack the player's minions until there is no attack power left or they have no minions.
            while (attackPower > 0 && player.hasMinions()) {
                attackPower = player.getRandomMinion().removeHealth(attackPower);
            }
        }

        return attackPower; //Return any remaining attack power after attacking minions.
    }
}