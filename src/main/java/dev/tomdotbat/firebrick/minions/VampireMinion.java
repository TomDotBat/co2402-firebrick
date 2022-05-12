package dev.tomdotbat.firebrick.minions;

import dev.tomdotbat.firebrick.Player;

public class VampireMinion extends Minion {
    public VampireMinion(Player owner, String name, int attackPower, int health, int healAmount) {
        super(owner, name, attackPower, health);
        this.healAmount = healAmount;
    }

    @Override
    public void attackPlayer(Player player) {
        super.attackPlayer(player);
        addHealth(healAmount); //Heal by the provided amount after every attack.
    }

    @Override
    protected void printMinionAttackMessage(Minion minion, int attackPower) {
        super.printMinionAttackMessage(minion, attackPower);
        printHealMessage();
    }

    @Override
    protected void printPlayerAttackMessage(Player player, int attackPower) {
        super.printPlayerAttackMessage(player, attackPower);
        printHealMessage();
    }

    private void printHealMessage() {
        System.out.printf("The %s of %s healed by %d, leaving them with %d health.\n",
                getName(), getOwner().getName(), healAmount, getHealth());
    }

    private final int healAmount;
}