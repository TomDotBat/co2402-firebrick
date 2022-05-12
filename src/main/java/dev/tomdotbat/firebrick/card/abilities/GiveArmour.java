package dev.tomdotbat.firebrick.card.abilities;

import dev.tomdotbat.firebrick.Player;
import dev.tomdotbat.firebrick.minions.Minion;
import dev.tomdotbat.firebrick.prompts.NumberedStringPrompt;
import dev.tomdotbat.firebrick.prompts.Prompt;

public class GiveArmour extends CardAbility {
    public GiveArmour(String name, int protection) {
        super(name);
        this.protection = protection;
    }

    @Override
    public void play(Player player) {
        if (!player.hasMinions()) {
            player.addHealth(healthBonus);
            System.out.printf("%s was given a %d health bonus as they have no minions, they now have %d health.\n",
                    player.getName(), healthBonus, player.getHealth());
        }
        else {
            Prompt<Integer> prompt = new NumberedStringPrompt("Which minion would you like to give the armour to?")
                    .withAnswers(player.getMinionNames());

            Minion minion = player.getMinions().get(prompt.execute());
            minion.addArmour(protection);

            System.out.printf("The %s of %s was given %s armour, they now have %d armour.\n",
                    minion.getName(), minion.getOwner().getName(), protection, minion.getArmour());
        }
    }

    private static final int healthBonus = 2;
    private int protection;
}