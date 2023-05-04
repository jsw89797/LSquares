package cs1302.game;

/**
This is the LatinSquaresDriver class, it runs the LatinSquaresGame play() method.
 */
public class LatinSquaresDriver {
    /**This is the main method which passs in args into a LatinSquaresGame
       object and initializes the game with game.play.
       @param args the command line argument provided with java command.
    */
    public static void main (String [] args) {
        String config = "";
        config = args[0];
        LatinSquaresGame game = new LatinSquaresGame(config);
        game.play();
    } //main
} //LatinSquaresDriver
