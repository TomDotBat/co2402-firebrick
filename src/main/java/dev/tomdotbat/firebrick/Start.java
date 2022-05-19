package dev.tomdotbat.firebrick;

/**
 * The initializer class for the game.
 */
public class Start {
    /**
     * The entry point of the program.
     * @param args the arguments the program was executed with.
     */
    public static void main(String[] args) {
        Game game = Game.getInstance();
        game.start();
    }
}