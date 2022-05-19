package dev.tomdotbat.firebrick.minions;

import dev.tomdotbat.firebrick.Player;

/**
 * An implementation of the leech minion.
 */
public class LeechMinion extends Minion {
    /**
     * Constructs a leech minion for a player with the given name, attack power and health.
     * @param owner the owner of the minion.
     * @param name the name of the minion.
     * @param attackPower the minion's attack power.
     * @param health the minion's starting health.
     * @param playerHealAmount the amount of health the owner should be healed by after each attack.
     */
    public LeechMinion(Player owner, String name, int attackPower, int health, int playerHealAmount) {
        super(owner, name, attackPower, health);
        this.playerHealAmount = playerHealAmount;
    }

    /**
     * Orders the minion to attack the given player and heals their owner..
     * @param player the player to attack.
     */
    @Override
    public void attackPlayer(Player player) {
        super.attackPlayer(player);
        getOwner().addHealth(playerHealAmount); //Heal the owner by the provided amount after every attack.
    }

    /**
     * Prints the amount of damage done to a minion.
     * @param minion the minion being damaged.
     * @param attackPower the attack power the minion was attacked for.
     */
    @Override
    protected void printMinionAttackMessage(Minion minion, int attackPower) {
        super.printMinionAttackMessage(minion, attackPower);
        printHealMessage();
    }

    /**
     * Prints the amount of damage done to a player.
     * @param player the player being damaged.
     * @param attackPower the attack power the minion was attacked for.
     */
    @Override
    protected void printPlayerAttackMessage(Player player, int attackPower) {
        super.printPlayerAttackMessage(player, attackPower);
        printHealMessage();
    }

    /**
     * Prints the amount the minion's owner was healed for.
     */
    private void printHealMessage() {
        System.out.printf("The %s of %s healed its owner by %d, leaving them with %d health.\n",
                getName(), getOwner().getName(), playerHealAmount, getOwner().getHealth());
    }

    private final int playerHealAmount;
}