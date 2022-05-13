package dev.tomdotbat.firebrick.card.abilities;

import dev.tomdotbat.firebrick.Player;
import dev.tomdotbat.firebrick.minions.Minion;
import dev.tomdotbat.firebrick.prompts.NumberedStringPrompt;
import dev.tomdotbat.firebrick.prompts.Prompt;

public abstract class EquipmentAbility extends CardAbility {
    public EquipmentAbility(String name, int stat) {
        super(name);
        this.stat = stat;
    }

    public int getStat() {
        return stat;
    }

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
        }
    }

    protected abstract void giveEquipment(Minion minion);

    protected abstract void printGiveMessage(Minion minion);

    private final int stat;
}