package dev.tomdotbat.firebrick.minions;

import dev.tomdotbat.firebrick.Player;

public class Wall extends Minion {
    public Wall(Player owner, String name, int health) {
        super(owner, name, 0, health);
    }

    @Override
    protected void printMinionAttackMessage(Minion minion, int attackPower) {} //The wall cannot attack so we hide attack messages.

    @Override
    protected void printPlayerAttackMessage(Player player, int attackPower) {}
}