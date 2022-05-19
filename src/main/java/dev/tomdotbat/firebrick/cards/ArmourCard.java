package dev.tomdotbat.firebrick.cards;

import dev.tomdotbat.firebrick.minions.Minion;

/**
 * An implementation of a card that gives a minion armour.
 */
public class ArmourCard extends EquipmentCard {
    /**
     * Constructs an armour giving card with the given name and protection level.
     * @param name the name of the card.
     * @param protection the protection level of the armour.
     */
    public ArmourCard(String name, int protection) {
        super(name, protection);
    }

    /**
     * Gives armour to the given minion.
     * @param minion the minion to give the armour to.
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