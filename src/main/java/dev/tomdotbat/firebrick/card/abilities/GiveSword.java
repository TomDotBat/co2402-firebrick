package dev.tomdotbat.firebrick.card.abilities;

import dev.tomdotbat.firebrick.Player;
import dev.tomdotbat.firebrick.minions.Minion;
import dev.tomdotbat.firebrick.prompts.NumberedStringPrompt;
import dev.tomdotbat.firebrick.prompts.Prompt;

public class GiveSword extends CardAbility {
    public GiveSword(String name, int attackBonus) {
        super(name);
        this.attackBonus = attackBonus;
    }

    @Override
    public void play(Player player) {
        if (!player.hasMinions()) {
            player.addHealth(healthBonus);
            System.out.printf("%s was given a %d health bonus as they have no minions, they now have %d health.\n",
                    player.getName(), healthBonus, player.getHealth());
        }
        else {
            Prompt<Integer> prompt = new NumberedStringPrompt("Which minion would you like to give the sword to?")
                    .withAnswers(player.getMinionNames());

            Minion minion = player.getMinions().get(prompt.execute());
            minion.addAttackPower(attackBonus);

            System.out.printf("The %s of %s was given a %s attack power bonus from a sword, their attack power is now %d.\n",
                    minion.getName(), minion.getOwner().getName(), attackBonus, minion.getAttackPower());
        }
    }

    private static final int healthBonus = 2;
    private int attackBonus;
}