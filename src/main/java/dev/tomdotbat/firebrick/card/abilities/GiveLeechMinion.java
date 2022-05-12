package dev.tomdotbat.firebrick.card.abilities;

import dev.tomdotbat.firebrick.Player;
import dev.tomdotbat.firebrick.minions.LeechMinion;

public class GiveLeechMinion extends CardAbility {
    public GiveLeechMinion(String name, int attackPower, int health, int playerHealAmount) {
        super(name);
        this.attackPower = attackPower;
        this.health = health;
        this.playerHealAmount = playerHealAmount;
    }

    @Override
    public void play(Player player) {
        player.giveMinion(new LeechMinion(player, getName(), attackPower, health, playerHealAmount));
    }

    private final int attackPower;
    private final int health;
    private final int playerHealAmount;
}