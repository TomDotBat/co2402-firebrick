package dev.tomdotbat.firebrick.card;

import dev.tomdotbat.firebrick.Player;
import dev.tomdotbat.firebrick.card.abilities.CardAbility;

public class Card {
    public Card(String name, CardAbility ability) {
        this.name = name;
        this.ability = ability;
    }

    public String getName() {
        return name;
    }

    public void play(Player player) {
        ability.play(player);
    }

    private final String name;
    private final CardAbility ability;
}