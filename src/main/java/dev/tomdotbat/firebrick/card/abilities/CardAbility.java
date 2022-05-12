package dev.tomdotbat.firebrick.card.abilities;

import dev.tomdotbat.firebrick.Player;

public abstract class CardAbility {
    public CardAbility(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract void play(Player player);

    private final String name;
}