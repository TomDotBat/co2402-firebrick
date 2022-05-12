package dev.tomdotbat.firebrick.card;

public enum CardType {
    INVALID(0),

    MINION_BASIC(1),
    PROJECTILE(2),
    LIGHTNING(3),
    BLESS(4),
    VAMPIRE(5),
    WALL(6),
    MINION_HORDE(7),
    MINION_TRAMPLE(8),
    MINION_LEECH(9),
    SWORD(10),
    ARMOUR(11);

    CardType(int typeId) {
        this.typeId = typeId;
    }

    public int getTypeId() {
        return typeId;
    }

    public static CardType getByTypeId(int typeId) { //Gets a CardType enum by its ID.
        for (CardType cardType : CardType.values()) {
            if (cardType.typeId == typeId) {
                return cardType;
            }
        }

        return CardType.INVALID;
    }

    private int typeId;
}