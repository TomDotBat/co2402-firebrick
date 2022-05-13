package dev.tomdotbat.firebrick.card.abilities;

import dev.tomdotbat.firebrick.minions.Minion;

public class GiveArmour extends EquipmentAbility {
    public GiveArmour(String name, int protection) {
        super(name, protection);
    }

    @Override
    protected void giveEquipment(Minion minion) {
        minion.addArmour(getStat());
    }
}