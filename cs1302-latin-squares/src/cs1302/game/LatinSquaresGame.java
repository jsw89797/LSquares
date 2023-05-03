package cs1302.game;

import java.util.Scanner;
import java.lang.Math;
import java.lang.Character;
import java.lang.Integer;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.NullPointerException;

/** The LatinSquaresGame class is responsible for the output and
functionality of LatinSquares when the LatinSquaresDriver is ran. */
public class LatinSquaresGame{
    int n = 0;
    String variables = "";
    int k = 0;
    int p = 0;
    String[][] board = null;
    Scanner promptInput = new Scanner(System.in);

    /**This is the cunstructor of LatinSquaresGame. In the constructor
       all variables n,k,p, as well as instance variables:
       variables, and board are defined*/
    public LatinSquaresGame(String config){
	try { //Try-catch to check for FileNotFoundExceptions
	    File configFile = new File(config);
	    Scanner fileReader = new Scanner(configFile);
	    String configArgs = ""; 
	    while (fileReader.hasNext()) {
		configArgs += fileReader.next();
	    }
	    int i = 0;
	    String numHolder = "";
	    while(i<configArgs.length()-1 &&
		  !(Character.isLetter(configArgs.charAt(i)))){
		numHolder += configArgs.charAt(i);
		i++;
	    }//while
	    n = Integer.valueOf(numHolder).intValue();
	    int constant1 = (n)+(numHolder.length());//Used to find k
	    for(int j = numHolder.length(); j < constant1; j++){
		if(!(Character.isDigit(configArgs.charAt(j)))){
		    variables += configArgs.charAt(j);
		}
	    }//for
	    k = Character.getNumericValue(configArgs.charAt(constant1));
	    p = (int)Math.ceil((Math.log(n)/Math.log(10))) + 2;
	    board = new String[n][n];
	    String preDetermined = configArgs.substring(n+numHolder.length()+1);
	    for(i = 0; i<k; i++){
		setPreDetermined(preDetermined);
		if(preDetermined.length()>3){
		    preDetermined = preDetermined.substring(3);
		}//if
	    }//for
	} 
	catch (FileNotFoundException e) { 
	    e.printStackTrace();
	}
    }//LatinSquaresGame

    /**This sets preDetermined values onto board.
      */
    public void setPreDetermined(String values){
	String holder = "";
	if(values.length() > 3){
	    holder = values.substring(0,4);
	}
	else{
	    holder = values;
	}//else
	int row = Character.getNumericValue(holder.charAt(0));
	int column = Character.getNumericValue(holder.charAt(1));
	String preDeterminedString = "[" + holder.charAt(2) + "]";
	board[row][column] = preDeterminedString + preDeterminedSpacePrint();
    }//setPreDetermined

    /**This returns correct number of spaces for predetermined values,
       when they are displayed on the board.
    */
    public String preDeterminedSpacePrint(){
	String holder = "";
	for(int i = 3; i<p; i++){
	    holder += " ";
	}//for
	holder += "|";
	return holder;
    }//preDeterminedSpacePrint


    /**This prints the welcome display to standard output */
    public void printWelcome(){
	String[] message = {
	    "  _           _   _        _____",
	    " | |         | | (_)      / ____|",
	    " | |     __ _| |_ _ _ __ | (___   __ _ _   _  __ _ _ __ ___  ___",
	    " | |    / _` | __| | '_ \\ \\___ \\ / _` | | | |/ _` | '__/ _ \\/ __|",
	    " | |___| (_| | |_| | | | |____) | (_| | |_| | (_| | | |  __/\\__ \\",
	    " |______\\__,_|\\__|_|_| |_|_____/ \\__, |\\__,_|\\__,_|_|  \\___||___/",
	    "                          CSCI 1302 | | v2018.fa",
	    "                                    |_|"
	};
	
	for (int i = 0; i < message.length; i++) {
	    //For loop to print the String array.
	    System.out.println(message[i]);
	}
	System.out.printf("n = %d { %s }\n", this.n, this.variables);
	//Two print statements to print game variables n and k.
	System.out.println("k = " + this.k); 	
    }//printWelcome

    /**This prints the board to standard output */
    public void displaySquare(){
	HorizontalNumber();
	for(int i = 0;i<n;i++){
	    String holder = Integer.toString(i);
	    System.out.print(i + " |");
	    for(int j = 0;j<n;j++){
		if(board[i][j] == null){
		    spacePrint();
		    System.out.print("|");
		}//if
		else{
		    System.out.print(board[i][j]);
		}
	    }//for
	    System.out.print("\n");
	}//for	
    }//displaySquare

    /**This prints the horizontal numbering for the board*/
    public void HorizontalNumber(){
	spacePrint();
	for(int i = 0; i<variables.length();i++){
	    System.out.print(i);
	    spacePrint();
	}//for
	System.out.print("\n");
    }//HorizontalNumber

    /**This prints the correct amount of spaces between each vertical bar on the board*/
    public void spacePrint(){
	for(int i = 0;i<p;i++){
	    System.out.print(" ");
	}//for
    }//spacePrint

    /**promptUser prompts the user after every turn. Also it runs each check using user input,
       and then if tests pass places the symbol at the directed location on the board */
    public void promptUser(){
	displaySquare();
	System.out.print("\n" + "latin-squares:");
	String ui = promptInput.nextLine();// "ui" or user input.
	if(qCheck(ui) && lengthCheck(ui) && numberCheck(ui) && nCheck(ui) && charCheck(ui)){
	    int row = Character.getNumericValue(ui.charAt(0));
	    int column = Character.getNumericValue(ui.charAt(2));
	    if(board[row][column] == null || !(board[row][column].charAt(0) == '[')){
		board[row][column] = ui.substring(4)+ charSpacePrint();
		
	    }//if
	}//if 
    }//promptUser

    /**This method returns the correct amount of spacing following a regular symbol
       on the board. */
    public String charSpacePrint(){
	String holder = "";
	for(int i = 1; i<p;i++){
	    holder += " ";
	}//for
	holder += "|";
	return holder;
    }//charSpacePrint

    /**qCheck checks to see if the user input q for quit, if yes the program is exited. */
    public boolean qCheck(String userInput){
	if(userInput.charAt(0) == 'q'){
	    System.out.println("GoodBye!");
	    System.exit(0);
	}//if
	return true;
    }//qCheck

    /** lengthCheck checks to see if the userInput is at correct length. */
    public boolean lengthCheck(String userInput){
	if(userInput.length()>5){
	    System.out.println("error: invalid input!");
	    return false;
	}
	return true;
    }//lengthCheck

    /**numberCheck checks to see if the userInput for coordinates are actually numbers. */
    public boolean numberCheck(String userInput){
	if(Character.isDigit(userInput.charAt(0)) && Character.isDigit(userInput.charAt(2))){
		return true;
	}//if
	System.out.println("error: invalid input!");
	return false;
    }

    /**nCheck checks to see if the user coordinates go above specified n */
    public boolean nCheck(String userInput){
    	int row = Character.getNumericValue(userInput.charAt(0));
	int column = Character.getNumericValue(userInput.charAt(2));
    	if(row >= n && column >= n){
	    System.out.println("error: invalid input!");
	    return false;
	}//if
	return true;
    }//nCheck

    /**charCheck checks to see if the character inputted by the user
       is within the original variable list. */
    public boolean charCheck(String userInput){
	for(int i = 0; i<variables.length(); i++){
	    if(userInput.charAt(4) == variables.charAt(i)){
		return true;
	    }
	}
	System.out.println("error: invalid input!");
	return false;   
    }//charCheck

    /**This method checks board to see if latin square is completed. */
    public boolean isLatinSquare(){
	try{//Try-catch used for null exception when square is uncomplete.
	    //Three for loops to split comparisons between rows/columns.
	    for(int i = 0; i<n; i++){
		for(int j = 0; j<n; j++){
		    for(int k = 1; k<n; k++){
			char a = ' ';
			char b = ' ';
			char c = ' ';
			if(board[i][j].charAt(0) == '['){
			    a = board[i][j].charAt(1);
			}//if
			else{ a = board[i][j].charAt(0);}
			if(board[i][k].charAt(0) == '['){
			    b = board[i][k].charAt(1);
			}//if
			else{ b = board[i][k].charAt(0);}
			if(board[k][j].charAt(0) == '['){
			    c = board[k][j].charAt(1);
			}//if
			else{c = board[k][j].charAt(0);
			    if(a == b  && j != k){
				return false;
			    }//if
			else if(a == c && i != k){
			    return false;
			}//else if
			}//if
		    }//forj
		}//for i
	    }//for k
	    return true;
	}//try
	catch(NullPointerException e){
	    return false;
	}//catch
    }//isLatinSquare
    
    /**This method prints the win message after the user completes the latinSquare */
    public void printWin(){
	String[] winMessage = {
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
	
	for(int i=0; i < winMessage.length; i++){
	    //For loop to print the string array.
	    System.out.print(winMessage[i]);
	}
	System.exit(0);	
    }//printWin
    
    /** This method runs the main game loop. Called by LatinSquaresDriver to
	run the game*/
    public void play(){
	printWelcome();
	while(!(isLatinSquare())){ //Controlling statement, whenever it returns true, the loop ends.
	    //game is played
	    promptUser();
	}//while
	displaySquare();
	printWin();
    }//play
    
}//LatinSquaresGame
