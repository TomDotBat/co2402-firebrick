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

    @Override
    protected void printGiveMessage(Minion minion) {
        System.out.printf("The %s of %s was given a %s attack power bonus from a %s, their attack power is now %d.\n",
                minion.getName(), minion.getOwner().getName(), getStat(), getName(), minion.getAttackPower());
    }
}