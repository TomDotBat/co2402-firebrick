package dev.tomdotbat.firebrick.minions;

import dev.tomdotbat.firebrick.Player;

/**
 * An abstract class to represent a player's minion.
 */
public abstract class Minion {
    /**
     * Constructs a minion for a player with the given name, attack power and health.
     * @param owner the owner of the minion.
     * @param name the name of the minion.
     * @param attackPower the minion's attack power.
     * @param health the minion's starting health.
     */
    public Minion(Player owner, String name, int attackPower, int health) {
        this.owner = owner;
        this.name = name;
        this.attackPower = attackPower;
        this.health = health;
    }

    /**
     * Gets the name of the minion.
     * @return the minion's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the minion's attack power.
     * @return the minion's attack power.
     */
    public int getAttackPower() {
        return attackPower;
    }

    /**
     * Adds the given amount to the minion's attack power.
     * @param amount the amount of attack power to give the minion.
     */
    public void addAttackPower(int amount) {
        attackPower += amount;
    }

    /**
     * Gets the minion's health.
     * @return the minion's health.
     */
    public int getHealth() {
        return health;
    }

    /**
     * Adds the given amount to the minion's health.
     * @param amount the amount of health to give the minion.
     */
    public void addHealth(int amount) {
        this.health += amount;
    }

    /**
     * Removes the given amount of health from the minion.
     * @param amount the amount of health to take from the minion.
     * @return the amount of damage left over if any remains.
     */
    public int removeHealth(int amount) {
        int diff = amount - health; //Get the amount of damage left.

        health -= amount - armour; //Remove the amount of health minus any armour this minion has.

        //Return any excess damage applied if any.
        return Math.max(diff, 0);
    }

    /**
     * Gets whether the minion is dead or not.
     * @return whether the minion is dead or not.
     */
    public boolean isDead() {
        return health <= 0;
    }

    /**
     * Gets the amount of armour the minion has.
     * @return the amount of armour the minion has.
     */
    public int getArmour() {
        return armour;
    }

    /**
     * Adds the given amount of armour to the minion.
     * @param amount the amount of armour to give the minion.
     */
    public void addArmour(int amount) {
        armour += amount;
    }

    /**
     * Gets the minion's owner.
     * @return the minion's owner.
     */
    public Player getOwner() {
        return owner;
    }

    /**
     * Orders the minion to attack the given player.
     * @param player the player to attack.
     */
    public void attackPlayer(Player player) {
        if (isDead()) { //Dead minions cannot attack.
            return;
        }

        if (player.hasMinions()) { //Attack the player's minions first.
            attackPlayerMinions(player);
            return;
        }

        int attackPower = getAttackPower();
        player.removeHealth(attackPower, true); //Attack the player and log it.
        printPlayerAttackMessage(player, attackPower);
    }

    /**
     * Orders the minion to attack a player's minions.
     * @param player the owner of the minions we should attack.
     * @return the remaining damage left for the minion to use.
     */
    public int attackPlayerMinions(Player player) {
        if (isDead()) { //Dead minions cannot attack.
            return 0;
        }

        int attackPower = getAttackPower();

        if (player.hasMinions()) {
            Minion randomMinion = player.getRandomMinion();
            randomMinion.removeHealth(attackPower); //Attack the minion and log it.
            printMinionAttackMessage(randomMinion, attackPower);
        }

        return 0; //Return remaining attack power.
    }

    /**
     * Prints the amount of damage done to a minion.
     * @param minion the minion being damaged.
     * @param attackPower the attack power the minion was attacked for.
     */
    protected void printMinionAttackMessage(Minion minion, int attackPower) {
        if (minion.isDead()) {
            System.out.printf("The %s of %s attacked the %s of %s for %d, killing them instantly.\n",
                    name, owner.getName(), minion.getName(), minion.getOwner().getName(), getAttackPower());
        }
        else {
            System.out.printf("The %s of %s attacked the %s of %s for %d, leaving them with %d health.\n",
                    name, owner.getName(), minion.getName(), minion.getOwner().getName(), getAttackPower(), minion.getHealth());
        }
    }

    /**
     * Prints the amount of damage done to a player.
     * @param player the player being damaged.
     * @param attackPower the attack power the minion was attacked for.
     */
    protected void printPlayerAttackMessage(Player player, int attackPower) {
        if (player.isDead()) {
            System.out.printf("The %s of %s attacked %s for %d, killing them instantly.\n",
                    name, owner.getName(), player.getName(), getAttackPower());
        }
        else {
            System.out.printf("The %s of %s attacked %s for %d, leaving them with %d health.\n",
                    name, owner.getName(), player.getName(), getAttackPower(), player.getHealth());
        }
    }

    private final String name;
    private int attackPower;
    private int health;
    private int armour = 0;
    private final Player owner;
}