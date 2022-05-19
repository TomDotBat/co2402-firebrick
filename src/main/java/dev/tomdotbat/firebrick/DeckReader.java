package dev.tomdotbat.firebrick;

import dev.tomdotbat.firebrick.card.Card;
import dev.tomdotbat.firebrick.card.CardType;
import dev.tomdotbat.firebrick.card.abilities.*;
import dev.tomdotbat.firebrick.exceptions.InvalidDeckFileException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.Scanner;
import java.util.Stack;

/**
 * Loads a card deck into the game for a given file location.
 */
public class DeckReader {
    /**
     * Constructs a deck reader for the given file.
     * @param filePath the location of the deck file.
     * @throws FileNotFoundException when the file location provided is invalid.
     */
    public DeckReader(String filePath) throws FileNotFoundException {
        this.filePath = filePath;

        File file = new File(filePath);
        scanner = new Scanner(file); //Create a scanner on the file at the given path.
    }

    /**
     * Reads from the deck file and creates a deck of cards.
     * @return a deck of cards.
     */
    public Stack<Card> read() {
        Stack<Card> deck = new Stack<>();

        while (scanner.hasNextLine()) { //Iterate over every line in the file to load the cards.
            int typeId = scanner.nextInt();
            String name = scanner.next();

            CardType cardType = CardType.getByTypeId(typeId);
            deck.add(new Card(name, readCardAbility(name, cardType))); //Create the card and add it to the deck.
        }

        scanner.close();

        Collections.shuffle(deck, Game.RANDOM); //Shuffle the cards.
        return deck;
    }

    /**
     * Instantiates a card ability from the current line in the deck file.
     * @param name the name of the card.
     * @param cardType the type of card ability.
     * @return a card ability.
     */
    private CardAbility readCardAbility(String name, CardType cardType) {
        CardAbility ability;

        switch (cardType) { //Read the required data depending on the card type.
            case MINION_BASIC:
                ability = new GiveBasicMinion(name, scanner.nextInt(), scanner.nextInt());
                break;
            case PROJECTILE:
                ability = new ShootProjectile(name, scanner.nextInt());
                break;
            case LIGHTNING:
                ability = new ShootLightning(name, scanner.nextInt());
                break;
            case BLESS:
                ability = new BlessTarget(name, scanner.nextInt(), scanner.nextInt());
                break;
            case VAMPIRE:
                ability = new GiveVampireMinion(name, scanner.nextInt(), scanner.nextInt(), scanner.nextInt());
                break;
            case WALL:
                scanner.nextInt(); //Ignore the attack damage.
                ability = new GiveWall(name, scanner.nextInt());
                break;
            case MINION_HORDE:
                ability = new GiveHordeMinion(name, scanner.nextInt(), scanner.nextInt(), scanner.nextInt());
                break;
            case MINION_TRAMPLE:
                ability = new GiveTrampleMinion(name, scanner.nextInt(), scanner.nextInt());
                break;
            case MINION_LEECH:
                ability = new GiveLeechMinion(name, scanner.nextInt(), scanner.nextInt(), scanner.nextInt());
                break;
            case SWORD:
                ability = new GiveSword(name, scanner.nextInt());
                break;
            case ARMOUR:
                ability = new GiveArmour(name, scanner.nextInt());
                break;
            default:
                throw new InvalidDeckFileException("Failed to determine a card type in " + filePath + ".");
        }

        return ability;
    }

    private final Scanner scanner;
    private final String filePath;
}