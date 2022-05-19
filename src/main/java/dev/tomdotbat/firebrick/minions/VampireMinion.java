package dev.tomdotbat.firebrick.minions;

import dev.tomdotbat.firebrick.Player;

/**
 * An implementation of the vampire minion.
 */
public class VampireMinion extends Minion {
    /**
     * Constructs a leech minion for a player with the given name, attack power and health.
     * @param owner the owner of the minion.
     * @param name the name of the minion.
     * @param attackPower the minion's attack power.
     * @param health the minion's starting health.
     * @param healAmount the amount of health minion heals by after each attack.
     */
    public VampireMinion(Player owner, String name, int attackPower, int health, int healAmount) {
        super(owner, name, attackPower, health);
        this.healAmount = healAmount;
    }

    /**
     * Orders the minion to attack the given player.
     * @param player the player to attack.
     */
    @Override
    public void attackPlayer(Player player) {
        super.attackPlayer(player);
        addHealth(healAmount); //Heal by the provided amount after every attack.
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
     * Prints the amount the minion healed for.
     */
    private void printHealMessage() {
        System.out.printf("The %s of %s healed by %d, leaving them with %d health.\n",
                getName(), getOwner().getName(), healAmount, getHealth());
    }

    private final int healAmount;
}