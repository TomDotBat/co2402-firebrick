package dev.tomdotbat.firebrick.minions;

import dev.tomdotbat.firebrick.Player;

public abstract class Minion {
    public Minion(Player owner, String name, int attackPower, int health) {
        this.owner = owner;
        this.name = name;
        this.attackPower = attackPower;
        this.health = health;
    }

    public String getName() {
        return name;
    }

    public int getAttackPower() {
        return attackPower;
    }

    public void addAttackPower(int amount) {
        attackPower += amount;
    }

    public int getHealth() {
        return health;
    }

    public void addHealth(int amount) {
        this.health += amount;
    }

    public int removeHealth(int amount) {
        int diff = amount - health; //Get the amount of damage left.

        health -= amount - armour; //Remove the amount of health minus any armour this minion has.

        //Return any excess damage applied if any.
        return Math.max(diff, 0);
    }

    public boolean isDead() {
        return health <= 0;
    }

    public int getArmour() {
        return armour;
    }

    public void addArmour(int amount) {
        armour += amount;
    }

    public Player getOwner() {
        return owner;
    }

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