package dev.tomdotbat.firebrick.card.abilities;

import dev.tomdotbat.firebrick.minions.Minion;

/**
 * An implementation of a card ability that gives a minion armour.
 */
public class GiveArmour extends EquipmentAbility {
    /**
     * Constructs an armour giving ability with the given name and protection level.
     * @param name the name of the ability.
     * @param protection the protection level of the armour.
     */
    public GiveArmour(String name, int protection) {
        super(name, protection);
    }

    /**
     * Gives the equipment to the given minion.
     * @param minion the minion to give the equipment to.
     */
    @Override
    protected void giveEquipment(Minion minion) {
        minion.addArmour(getStat());
    }

    /**
     * Prints the equipment given message for the specified minion.
     * @param minion the minion which received the equipment.
     */
    @Override
    protected void printGiveMessage(Minion minion) {
        System.out.printf("The %s of %s was given %d %s, they now have %d armour.\n",
                minion.getName(), minion.getOwner().getName(), getStat(), getName(), minion.getArmour());
    }
}