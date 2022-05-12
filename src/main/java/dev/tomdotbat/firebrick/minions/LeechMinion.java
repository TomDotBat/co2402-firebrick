package dev.tomdotbat.firebrick.minions;

import dev.tomdotbat.firebrick.Player;

public class LeechMinion extends Minion {
    public LeechMinion(Player owner, String name, int attackPower, int health, int playerHealAmount) {
        super(owner, name, attackPower, health);
        this.playerHealAmount = playerHealAmount;
    }

    @Override
    public void attackPlayer(Player player) {
        super.attackPlayer(player);
        getOwner().addHealth(playerHealAmount); //Heal the owner by the provided amount after every attack.
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
        System.out.printf("The %s of %s healed its owner by %d, leaving them with %d health.\n",
                getName(), getOwner().getName(), playerHealAmount, getOwner().getHealth());
    }

    private final int playerHealAmount;
}