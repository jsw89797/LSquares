package cs1302.game; //Package statement
import java.util.Scanner; //Import needed java packages.

/**This class file, LatinSquaresDriver, sets up and starts the Latin squares game. */
public class LatinSquaresDriver {
    /**In the main method, args[0] is taken in as a string variable,
     and a LatinSquaresGame object is constructed and used to play game.*/
    public static void main(String[] args) { 
	String config = args[0];
	LatinSquaresGame game = new LatinSquaresGame(config);
	game.play();
    }
}
