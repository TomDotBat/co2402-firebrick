package dev.tomdotbat.firebrick.card.abilities;

import dev.tomdotbat.firebrick.Player;
import dev.tomdotbat.firebrick.minions.Minion;
import dev.tomdotbat.firebrick.prompts.NumberedStringPrompt;
import dev.tomdotbat.firebrick.prompts.Prompt;

/**
 * An abstract class to represent equipment card abilities.
 */
public abstract class EquipmentAbility extends CardAbility {
    /**
     * Constructs an equipment card ability with the given name and stat.
     * @param name the name of the card ability.
     * @param stat the multiplier of the equipment.
     */
    public EquipmentAbility(String name, int stat) {
        super(name);
        this.stat = stat;
    }

    /**
     * Gets the equipment's multiplier.
     * @return the equipment multiplier.
     */
    public int getStat() {
        return stat;
    }

    /**
     * Prompts the player to give the equipment to one of their minions. A health bonus is given if they have none.
     * @param player the player to give the equipment to.
     * @param target the opponent to play perform the ability on.
     */
    @Override
    public void play(Player player, Player target) {
        if (!player.hasMinions()) {
            player.addHealth(stat);
            System.out.printf("%s was given a %d health bonus as they have no minions, they now have %d health.\n",
                    player.getName(), stat, player.getHealth());
        }
        else {
            Prompt<Integer> prompt = new NumberedStringPrompt("Which minion would you like to give the " + getName() + " to?")
                    .withAnswers(player.getMinionNames());

            Minion minion = player.getMinions().get(prompt.execute());
            giveEquipment(minion);
            printGiveMessage(minion);
        }
    }

    /**
     * Gives the equipment to a given minion.
     * @param minion the minion to give the equipment to.
     */
    protected abstract void giveEquipment(Minion minion);

    /**
     * Prints the equipment given message for the specified minion.
     * @param minion the minion which received the equipment.
     */
    protected abstract void printGiveMessage(Minion minion);

    private final int stat;
}