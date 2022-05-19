package dev.tomdotbat.firebrick.card.abilities;

import dev.tomdotbat.firebrick.minions.Minion;

/**
 * An implementation of a card ability that gives a minion a sword.
 */
public class GiveSword extends EquipmentAbility {
    /**
     * Constructs a sword giving card ability with the given name and stat.
     * @param name the name of the card ability.
     * @param attackBonus the attack bonus the sword provides.
     */
    public GiveSword(String name, int attackBonus) {
        super(name, attackBonus);
    }

    /**
     * Gives the equipment to the given minion.
     * @param minion the minion to give the equipment to.
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