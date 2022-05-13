package dev.tomdotbat.firebrick.card.abilities;

import dev.tomdotbat.firebrick.Player;
import dev.tomdotbat.firebrick.minions.LeechMinion;
import dev.tomdotbat.firebrick.minions.Minion;

public class GiveLeechMinion extends MinionAbility {
    public GiveLeechMinion(String name, int attackPower, int health, int playerHealAmount) {
        super(name, attackPower, health);
        this.playerHealAmount = playerHealAmount;
    }

    @Override
    protected Minion createMinion(Player player) {
        return new LeechMinion(player, getName(), getAttackPower(), getHealth(), playerHealAmount);
    }

    private final int playerHealAmount;
}