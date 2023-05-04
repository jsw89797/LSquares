package cs1302.game;
//package

//import statments
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.lang.Math;
import java.lang.Integer;
import java.lang.Character;
import java.lang.NullPointerException;

/** This is the LatinSquaresGame class file, all algorithms for the
    Latin Squares game is completed here.*/

public class LatinSquaresGame {
    int n = 0;
    int k = 0;
    int p = 0;
    String variables = "";
    String[][] display = null;
    Scanner prompt = new Scanner(System.in);

    /** This is the LatinSquaresGame constructor it construcs the game and
        sets needed variables correctly from the command line argument provided.
        @param config The configuration of the game.
    */

    public LatinSquaresGame(String config) {
        try {
            File conFile = new File(config);
            Scanner fileReader = new Scanner(conFile);
            String args = "";
            while (fileReader.hasNext()) {
                args += fileReader.next();
            } //while
            int i = 0;
            String holder = "";
            while (i < args.length() - 1 &&
                   !(Character.isLetter(args.charAt(i)))) {
                holder += args.charAt(i);
                i++;
            } //while
            n = Integer.valueOf(holder).intValue();
            int c1 = (n) + (holder.length());
            for (int j = holder.length(); j < c1; j++) {
                if (!(Character.isDigit(args.charAt(j)))) {
                    variables += args.charAt(j);
                } //if
            } //for
            k = Character.getNumericValue(args.charAt(c1));
            p = (int)Math.ceil((Math.log(n) / Math.log(10))) + 2;
            display = new String[n][n];
            String preDetermined = args.substring(n + holder.length() + 1);
            for (i = 0; i < k; i++) {
                setPre(preDetermined);
                if (preDetermined.length() > 3) {
                    preDetermined = preDetermined.substring(3);
                } //if
            } //for
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } //catch
    } //LatinSquaresGame

    /** The setPre method sets the predetermined values in their correct spots on board.
        @param values The values indicated as predetermined values
    */

    public void setPre(String values) {
        String holder = "";
        if (values.length() > 3) {
            holder = values.substring(0,4);
        }  else {
            holder = values;
        } //else
        int row = Character.getNumericValue(holder.charAt(0));
        int column = Character.getNumericValue(holder.charAt(1));
        String pDString = "[" + holder.charAt(2) + "]";
        display[row][column] = pDString + pDPadd();
    } //setPre

    /** The pDPadd method is responsible for printing the correct amount of padding following the
        predetermined character in the LatinSquares cell.
        @return String containing the proper amount padding.
    */
    public String pDPadd() {
        String holder = "";
        for (int i = 3; i < p; i++) {
            holder += " ";
        } //for
        holder += "|";
        return holder;
    } //pDPadd

    /** The printWelcome method prints the welcome screen for the LatinSquare game. */
    public void printWelcome() {
        String[] welcome = {
            "  _           _   _        _____",
            " | |         | | (_)      / ____|",
            " | |     __ _| |_ _ _ __ | (___   __ _ _   _  __ _ _ __ ___  ___",
            " | |    / _` | __| | '_ \\ \\___ \\ / _` | | | |/ _` | '__/ _ \\/ __|",
            " | |___| (_| | |_| | | | |____) | (_| | |_| | (_| | | |  __/\\__ \\",
            " |______\\__,_|\\__|_|_| |_|_____/ \\__, |\\__,_|\\__,_|_|  \\___||___/",
            "                          CSCI 1302 | | v2018.fa",
            "                                    |_|"
        };
        for (int i = 0; i < welcome.length ;i++) {
            System.out.println(welcome[i]);
        } //for
        System.out.printf("n = %d ", this.n);
        System.out.print("{" + this.variables + "}" + "\n");
        System.out.println("k = " + this.k);
    } //printWelcome

    /** The horizontal method is responsible for printing the horizontal numbering
        across the top in the Latin Square.
    */

    public void horizontal() {
        pad();
        for (int i = 0; i < variables.length(); i++) {
            System.out.print(i);
            pad();
        } //for
        System.out.print("\n");
    } //horizontal

    /** The pad method is responsible for printing the correct amount of padding
        in an empty cell in the LatinSquare.
    */

    public void pad() {
        for (int i = 0; i < p; i++) {
            System.out.print(" ");
        } //for
    } //pad

    /** The charPad method is responsible for printing the correct amount of padding
        after a character in the square.
        @return String containing correct amount of padding.
    */
    public String charPad() {
        String holder = "";
        for (int i = 1; i < p; i++) {
            holder += " ";
        } //for
        holder += "|";
        return holder;
    } //charPad

    /** The displaySquare is responsible for printing the current Latin Square game. */

    public void displaySquare() {
        horizontal();
        for (int i = 0; i < n; i++) {
            String holder = Integer.toString(i);
            System.out.print(i + " |");
            for (int j = 0; j < n; j++) {
                if (display[i][j] == null) {
                    pad();
                    System.out.print("|");
                }  else {
                    System.out.print(display[i][j]);
                } //else
            } //for
            System.out.print("\n");
        } //for
    } //displaySquare


    /**The quit method checks to see if the user input q for quit, if yes the program is exited.
       @param userInput Input collected from user.
       @return Boolean returning true if the user hasn't input q.
     */

    public boolean quit(String userInput) {
        if (userInput.charAt(0) == 'q') {
            System.out.println("GoodBye!");
            System.exit(0);
        } //if
        return true;
    } //quit

    /** The length method checks to see if the userInput is at correct length.
        @param userInput Input collected from user.
        @return Boolean returning true if user input is the correct length.
     */

    public boolean length(String userInput) {
        if (userInput.length( ) > 5) {
            System.out.println("error: invalid input!");
            return false;
        } //if
        return true;
    } //length

    /**The number method checks to see if the userInput for coordinates are actually numbers.
       @param userInput Input collected from user.
       @return Boolean returning true if user coordinate is a number.
     */

    public boolean number(String userInput) {
        if (Character.isDigit(userInput.charAt(0)) &&
            Character.isDigit(userInput.charAt(2))) {
            return true;
        } //if
        System.out.println("error: invalid input!");
        return false;
    } //number

    /**The limit method checks to see if the user coordinates go above specified n.
       @param userInput Input collected from user.
       @return Boolean returning true if input is within boundaries.
     */

    public boolean limit(String userInput) {
        int row = Character.getNumericValue(userInput.charAt(0));
        int column = Character.getNumericValue(userInput.charAt(2));
        if (row >= n && column >= n) {
            System.out.println("error: invalid input!");
            return false;
        } //if
        return true;
    } //limit

    /**The chars method checks to see if the character inputed by the user
       is within the original variable list.
       @param userInput Input collected from user.
       @return Boolean returning true if input is a legal character.
    */

    public boolean chars(String userInput) {
        for (int i = 0; i < variables.length(); i++) {
            if (userInput.charAt(4) == variables.charAt(i)) {
                return true;
            }
        }
        System.out.println("error: invalid input!");
        return false;
    } //charCheck


    /** This method prompts the user for input. */
    public void promptUser() {
        displaySquare();
        System.out.print("\n" + "latin-squares:");
        String input = prompt.nextLine();
        if (quit(input) && length(input) && number(input)
            && limit(input) && chars(input)) {
            int row = Character.getNumericValue(input.charAt(0));
            int column = Character.getNumericValue(input.charAt(2));
            if (display[row][column] == null ||
                !(display[row][column].charAt(0) == '[')) {
                display[row][column] = input.substring(4) + charPad();
            } //if
        } //if
    } //promptUser


    /** This is the isLatinSquareMethod, in this method the LatinSquare is checked for completion.
        If the latin square is completed correctly it will return true.
        @return Boolean indicating whether it is a complete LatinSquare or not.
    */

    public boolean isLatinSquare() {
        try { //Try-catch used for null exception when square is uncomplete.
            //Three for loops to split comparisons between rows/columns.
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    for (int k = 1; k < n; k++) {
                        char a = ' ';
                        char b = ' ';
                        char c = ' ';
                        if (display[i][j].charAt(0) == '[') {
                            a = display[i][j].charAt(1);
                        } else {
                            a = display[i][j].charAt(0);
                        }
                        if (display[i][k].charAt(0) == '[') {
                            b = display[i][k].charAt(1);
                        } else {
                            b = display[i][k].charAt(0);
                        }
                        if (display[k][j].charAt(0) == '[') {
                            c = display[k][j].charAt(1);
                        } else {
                            c = display[k][j].charAt(0);
                            if (a == b  && j != k) {
                                return false;
                            } else if (a == c && i != k) {
                                return false;
                            } //else if
                        } //else
                    } //forj
                } //for i
            } //for k
            return true;
            //Returns true and ends game loop.
        }   catch (NullPointerException e) {
            return false;
        } //catch
    } //isLatinSquare


    /**This is the printWin method it notifies the user they have won
       once the Latin Square is complete. This also exits the game. */

    public void printWin() {
        String[] win = {
            "                                .''.\n",
            "      .''.             *''*    :_\\/_:     .\n",
            "     :_\\/_:   .    .:.*_\\/_*   : /\\ :  .'.:.'.\n",
            " .''.: /\\ : _\\(/_  ':'* /\\ *  : '..'.  -=:o:=-\n",
            ":_\\/_:'.:::. /)\\*''*  .|.* '.\\'/.'_\\(/_'.':'.'\n",
            ": /\\ : :::::  '*_\\/_* | |  -= o =- /)\\    '  *\n",
            " '..'  ':::'   * /\\ * |'|  .'/.\\'.  '._____\n",
            "     *        __*..* |  |     :      |.   |' .---\"|\n",
            "      _*   .-'   '-. |  |     .--'|  ||   | _|    |\n",
            "   .-'|  _.|  |    ||   '-__  |   |  |    ||      | \n",
            "   |' | |.    |    ||       | |   |  |    ||      |\n",
            "___|  '-'     '    \"\"       '-'   '-.'    '`      |____\n",
            "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n",
            "CONGRATULATIONS! YOU COMPLETED THE LATIN SQUARE!",
        };
        for (int i = 0; i < win.length; i++) {
            System.out.print(win[i]);
        } //for
        System.exit(0);
    } //printWin

    /** This is the play method, it is responsible for playing the game,
        within its loop it calls the prompt method as well as other game integral
        methods. */

    public void play() {
        printWelcome();
        while (true) {
            promptUser();
            if (isLatinSquare()) {
                displaySquare();
                printWin();
            } //if
        } //while
    } //play
} //LatinSquaresGame
