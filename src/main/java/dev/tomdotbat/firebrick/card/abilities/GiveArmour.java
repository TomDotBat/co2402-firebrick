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

    @Override
    protected void printGiveMessage(Minion minion) {
        System.out.printf("The %s of %s was given %d %s, they now have %d armour.\n",
                minion.getName(), minion.getOwner().getName(), getStat(), getName(), minion.getArmour());
    }
}