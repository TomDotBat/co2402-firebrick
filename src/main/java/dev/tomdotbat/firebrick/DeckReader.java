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

public class DeckReader {
    public DeckReader(String filePath) throws FileNotFoundException {
        this.filePath = filePath;

        File file = new File(filePath);
        scanner = new Scanner(file); //Create a scanner on the file at the given path.
    }

    public Stack<Card> read() throws FileNotFoundException {
        Stack<Card> deck = new Stack<>();

        while (scanner.hasNextLine()) { //Iterate over every line in the file to load the cards.
            int typeId = scanner.nextInt();
            String name = scanner.next();

            CardType cardType = CardType.getByTypeId(typeId);
            deck.add(new Card(name, readCardAbility(name, cardType))); //Create the card and add it to the deck.
        }

        scanner.close();

        Collections.shuffle(deck); //Shuffle the cards.
        return deck;
    }

    private CardAbility readCardAbility(String name, CardType cardType) {
        CardAbility ability = null;

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

    private Scanner scanner;
    private final String filePath;
}