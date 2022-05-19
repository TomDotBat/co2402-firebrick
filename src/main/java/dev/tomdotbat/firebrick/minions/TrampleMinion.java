package dev.tomdotbat.firebrick.minions;

import dev.tomdotbat.firebrick.Player;

/**
 * An implementation of the trample minion.
 */
public class TrampleMinion extends Minion {
    /**
     * Constructs a trample minion for a player with the given name, attack power and health.
     * @param owner the owner of the minion.
     * @param name the name of the minion.
     * @param attackPower the minion's attack power.
     * @param health the minion's starting health.
     */
    public TrampleMinion(Player owner, String name, int attackPower, int health) {
        super(owner, name, attackPower, health);
    }

    /**
     * Orders the minion to attack the given player.
     * @param player the player to attack.
     */
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

        player.removeHealth(attackPower); //Attack the opponent.
        printPlayerAttackMessage(player, attackPower);
    }

    /**
     * Orders the minion to attack a player's minions.
     * @param player the owner of the minions we should attack.
     * @return the remaining damage left for the minion to use.
     */
    @Override
    public int attackPlayerMinions(Player player) {
        int attackPower = getAttackPower();

        if (player.hasMinions()) {
            //Randomly attack the player's minions until there is no attack power left, or they have no minions.
            while (attackPower > 0 && player.hasMinions()) {
                Minion randomMinion = player.getRandomMinion();
                attackPower = randomMinion.removeHealth(attackPower);
                printMinionAttackMessage(randomMinion, attackPower);
            }
        }

        return attackPower; //Return any remaining attack power after attacking minions.
    }
}