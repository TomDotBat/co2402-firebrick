package dev.tomdotbat.firebrick.cards;

import dev.tomdotbat.firebrick.minions.Minion;

/**
 * An implementation of a card that gives a minion a sword.
 */
public class SwordCard extends EquipmentCard {
    /**
     * Constructs a sword giving card with the given name and stat.
     * @param name the name of the card.
     * @param attackBonus the attack bonus the sword provides.
     */
    public SwordCard(String name, int attackBonus) {
        super(name, attackBonus);
    }

    /**
     * Gives a sword to the given minion.
     * @param minion the minion to give the sword to.
     */
    @Override
    protected void giveEquipment(Minion minion) {
        minion.addAttackPower(getStat());
    }

    /**
     * Prints the equipment given message for the specified minion.
     * @param minion the minion which received the equipment.
     */
    @Override
    protected void printGiveMessage(Minion minion) {
        System.out.printf("The %s of %s was given a %s attack power bonus from a %s, their attack power is now %d.\n",
                minion.getName(), minion.getOwner().getName(), getStat(), getName(), minion.getAttackPower());
    }
}