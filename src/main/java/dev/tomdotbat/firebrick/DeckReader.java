package dev.tomdotbat.firebrick;

import dev.tomdotbat.firebrick.cards.*;
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
     * Sets whether the deck should be shuffled or not.
     * @param shouldShuffle whether the deck should be shuffled or not.
     */
    public void setShouldShuffle(boolean shouldShuffle) {
        this.shouldShuffle = shouldShuffle;
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
            deck.add(readCard(name, cardType)); //Create the card and add it to the deck.
        }

        scanner.close();

        if (shouldShuffle) {
            Collections.shuffle(deck, Game.RANDOM); //Shuffle the cards if desired.
        }

        return deck;
    }

    /**
     * Instantiates a card from the current line in the deck file.
     * @param name the name of the card.
     * @param cardType the type of card.
     * @return a card ability.
     */
    private Card readCard(String name, CardType cardType) {
        Card card;

        switch (cardType) { //Read the required data depending on the card type.
            case MINION_BASIC:
                card = new BasicMinionCard(name, scanner.nextInt(), scanner.nextInt());
                break;
            case PROJECTILE:
                card = new ProjectileCard(name, scanner.nextInt());
                break;
            case LIGHTNING:
                card = new LightningCard(name, scanner.nextInt());
                break;
            case BLESS:
                card = new BlessCard(name, scanner.nextInt(), scanner.nextInt());
                break;
            case VAMPIRE:
                card = new VampireMinionCard(name, scanner.nextInt(), scanner.nextInt(), scanner.nextInt());
                break;
            case WALL:
                scanner.nextInt(); //Ignore the attack damage.
                card = new WallCard(name, scanner.nextInt());
                break;
            case MINION_HORDE:
                card = new HordeMinionCard(name, scanner.nextInt(), scanner.nextInt(), scanner.nextInt());
                break;
            case MINION_TRAMPLE:
                card = new TrampleMinionCard(name, scanner.nextInt(), scanner.nextInt());
                break;
            case MINION_LEECH:
                card = new LeechMinionCard(name, scanner.nextInt(), scanner.nextInt(), scanner.nextInt());
                break;
            case SWORD:
                card = new SwordCard(name, scanner.nextInt());
                break;
            case ARMOUR:
                card = new ArmourCard(name, scanner.nextInt());
                break;
            default:
                throw new InvalidDeckFileException("Failed to determine a card type in " + filePath + ".");
        }

        return card;
    }

    private final Scanner scanner;
    private final String filePath;
    private boolean shouldShuffle = false;
}