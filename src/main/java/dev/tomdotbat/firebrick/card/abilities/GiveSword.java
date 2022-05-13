package dev.tomdotbat.firebrick.card.abilities;

import dev.tomdotbat.firebrick.minions.Minion;

public class GiveSword extends EquipmentAbility {
    public GiveSword(String name, int attackBonus) {
        super(name, attackBonus);
    }

    @Override
    protected void giveEquipment(Minion minion) {
        minion.addAttackPower(getStat());
    }
}