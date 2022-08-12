package battleship;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.io.File;
import battleship.MainGame.Square;
import battleship.resources.BattleshipButton;
import battleship.resources.BattleshipResource;
import hangmanB.HangmanGame;
import javafx.application.Application;

import javafx.geometry.Pos;

import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import mastermindB.GameController;
import mastermindB.GameModel;


public class MainMenu extends Application{
	
	
	// Mastermind A - Global variables
	private static Scanner ui;
    private static int remainingGuesses;
    private static int param1;
    private static int param2;
    private static String actualString;
	
	// Battleship B - Global Variables
	public static Stage windowMain;
	private static final int windowWidth = 960;
	private static final int windowHeight = 540;	
    public static MainGame enemyBoard, cEnemyBoard;
    private static Random random = new Random();
    public static int counterShips, counterMonsters;
    public static TextArea statusReport;
    public static Label currentScore, finalScore;
    
    public static Ship AircraftCarrier, Battleship, Submarine, Destroyer, PatrolBoat, Kraken, Cetus;
    
	public static BorderPane layoutMenu = new BorderPane(); // Main Menu
	public static BorderPane layoutLobby = new BorderPane(); // Lobby Menu
	public static BorderPane layoutOriginalMode = new BorderPane(); // Original Game Mode Menu
	public static BorderPane layoutOption = new BorderPane(); // Option Menu
	public static BorderPane layoutEnd = new BorderPane();

	public static Scene sceneMain, sceneLobby,sceneOriginalGame, sceneOption, sceneEnd;
	
	public static boolean enableCheats;
	
	public void start(Stage stage) throws Exception{
		selectGame();
	}
	public static void selectGame() throws IOException {
		System.out.println();
		System.out.println("1) Hangman A");
		System.out.println("2) Hangman B");
		System.out.println("3) Snakes and Ladders A");
		System.out.println("4) Snakes and Ladders B");
		System.out.println("5) Mastermind A");
		System.out.println("6) Mastermind B");
		System.out.println("7) Battleship B");
		System.out.println("8) QUIT \n");
		
		System.out.print("Please enter a game: ");
		Scanner scannerInput = new Scanner(System.in);
		String menuOption = scannerInput.next();
		
		switch(menuOption) {
		case "1":
			System.out.println("Hangman A Initialising \n");
			mainHangmanA();
			break;
		case "2":
			System.out.println("Hangman B Initialising \n");
			mainHangmanB();
			break;
		case "3":
			System.out.println("Snakes and Ladders A Initialising \n");
			mainSnakesLaddersA();
			break;
		case "4":
			System.out.println("Snakes and Ladders B Initialising \n");
			mainSnakesLaddersB();
			break;
		case "5":
			System.out.println("Mastermind A Initialising \n");
			mainMastermindA();
			break;
		case "6": 
			System.out.println("Mastermind B Initialising \n");
			mainMastermindB();
			break;
		case "7":
			System.out.println("Battleship B Initialising \n");
			mainBattleship();
			break;
	    case "8":
			System.out.println("Goodbye");
			System.exit(0);
	    	break;
		default:
			System.out.println("Please choose a number between 1 to 8!");
			selectGame();
			break;
		}
	}
	
	
	// 1) Hangman A - main
	public static void mainHangmanA() throws IOException {
		String playAgain; 	
		do
		{
			File dictionary = new File("src/hangmanA/HangmanWords.txt");

			System.out.print("Welcome to Hangman");

			int warningCounter = 0;

			Scanner input = new Scanner(System.in);
			Scanner textScanner = new Scanner(dictionary);

			int difficultyLevel = 0;
			boolean loop = true;

			System.out.println( "" + "\n"
					+ "\n"
					+ "  1 Easy - 6 lives"  + "\n" 
					+ "  2 Medium - 4 lives" + "\n"  
					+ "  3 Hard - 2 lives"
					+ "\n");


			while (loop) {
				System.out.print("Enter the difficulty level between 1 & 3: "
						+ "\n");

				if (!input.hasNextInt() || !input.hasNext()) {

					System.out.println("Invalid input"
							+ "\n");
					input.nextLine();
				} else {
					difficultyLevel = input.nextInt();
					if (difficultyLevel <= 3 && difficultyLevel >= 1)
						loop = false;
					else {
						System.out.println("Invalid input"
								+ "\n");
						input.nextLine();
					}

				}
			}

			System.out.println("difficultyLevel: " + difficultyLevel);

			ArrayList<String>words = new ArrayList<>();
			while(textScanner.hasNextLine()) {
				words.add(textScanner.nextLine());
			}

			String hidden_Answer = words.get((int)(Math.random()*words.size()));
			char[] textArray = hidden_Answer.toCharArray();

			char[] myAnswer = new char[textArray.length];
			for(int i = 0; i < textArray.length; i++) {
				myAnswer[i] = '?';

			}

			boolean finished = false;
			int life = 0;

			{  	
				if( difficultyLevel == 1) {	
					life = 6;
				}
				else if( difficultyLevel == 2) {
					life = 4;
				}		
				else if (difficultyLevel == 3){
					life = 2;
				}	

			}

			while(finished == false) {
				System.out.print("" + "\n"
						+ "Enter a letter:");

				String letter = input.next();

				while(letter.length() != 1 || Character.isDigit(letter.charAt(0))) {
					warningCounter++;
					System.out.println("" + "\n"
							+ "Invalid input, after three, one life will be removed ");
					letter = input.next();


					if (warningCounter == 2) {
						System.out.println(""+ "\n"
								+ "One life has been removed");
						life--;
						warningCounter += -3;	

					}  

				}

				Boolean found = false;
				for(int i=0; i< textArray.length; i++) {
					if(letter.charAt(0) == textArray[i]) {
						myAnswer[i] = textArray[i];
						found = true;

					}
				}

				if(!found) {
					life--;

					System.out.println(""+ "\n"
							+ "Wrong Letter"                 
							+ "\n");
				}

				Boolean completed = true;
				for(int i = 0; i < myAnswer.length; i++) {
					if(myAnswer[i] == '?') {
						System.out.print(" _");
						completed = false;
					}
					else {
						System.out.print(" " + myAnswer[i]);

					}

				}

				System.out.println("\n"
						+"\n"
						+ "Warning Counter: " + warningCounter);


				System.out.println("\n" 

	            + "lives: " + life
	            +"\n"); 

				drawHangman(life);

				if (completed) {
					System.out.print("YOU WIN");
					finished = true;

				}

				if(life <= 0) {
					System.out.println("YOU LOSE");
					finished = true;
				}
			}

			System.out.println("\n"
					+"Answer:" + hidden_Answer); 

			System.out.println("Do you want to play again?: Yes or No"); 
			playAgain = input.next();
			if (playAgain.equalsIgnoreCase("No")){
				selectGame();
			}
		}

		while(playAgain.equalsIgnoreCase("Yes"));
	}
	
	public static void drawHangman(int l) {
		if(l == 6) {
			System.out.println("    ------|");
			System.out.println("          |");
			System.out.println("          |");
			System.out.println("          |");
			System.out.println("          |");
			System.out.println("          |");
			System.out.println("          |");
		}
		else if(l == 5) {
			System.out.println("    ------|");
			System.out.println("    O     |");
			System.out.println("          |");
			System.out.println("          |");
			System.out.println("          |");
			System.out.println("          |");
			System.out.println("          |");
		}
		else if(l == 4) {
			System.out.println("    ------|");
			System.out.println("    O     |");
			System.out.println("   (|     |");
			System.out.println("          |");
			System.out.println("          |");
			System.out.println("          |");
			System.out.println("          |");
		}
		else if(l == 3) {
			System.out.println("    ------|");
			System.out.println("    O     |");
			System.out.println("   (|)    |");
			System.out.println("          |");
			System.out.println("          |");
			System.out.println("          |");
			System.out.println("          |");
		}
		else if(l == 2) {
			System.out.println("    ------|");
			System.out.println("    O     |");
			System.out.println("   (|)    |");
			System.out.println("   (      |");
			System.out.println("          |");
			System.out.println("          |");
			System.out.println("          |");
		}
		else if(l == 1) {
			System.out.println("    ------|");
			System.out.println("    O     |");
			System.out.println("   (|)    |");
			System.out.println("   ( )    |");
			System.out.println("          |");
			System.out.println("          |");
			System.out.println("          |");
		}
		else{
			System.out.println("    ------|");
			System.out.println("    O     |");
			System.out.println("   (|)    |");
			System.out.println("   ( )    |");
			System.out.println("   - -    |");
			System.out.println("          |");
			System.out.println("          |");
		}

	}
	
	
	// 2) Hangman B - main
	public static void mainHangmanB() throws IOException {
		  System.out.println("Welcome to Hangman!  Good luck you have 10 tries");
		  HangmanGame hangmanGame = new HangmanGame();
		  hangmanGame.newGame();
		  hangmanGame.play();
	}

	// 3) Snakes and Ladders A - main 
	public static void mainSnakesLaddersA() throws IOException {
		System.out.println("Snakes and Ladders: Variation A");
		System.out.println();
		
		Scanner input = new Scanner(System.in);  
		System.out.print("Play? (Y / N): ");
	    String play = input.nextLine();
	    
	    switch (play) 
	    {
	    case "Yes":
	    case "yes":
	    case "YES":
	    case "Y":
	    case "y":
	    	difficulty();
	    	break;
	    case "No":
	    case "no":
	    case "NO":
	    case "N":
	    case "n":
	    	System.out.println("Ok");
	    	break;
	    default:
	    	System.out.println("PeePee");
	    	break;
	    }
	}
	public static void difficulty() throws IOException 
	{	
		Scanner input = new Scanner(System.in);  
		System.out.print("Choose Difficult (Easy, Medium, Hard): ");
	    String difficulty = input.nextLine();
	    
	    switch (difficulty) 
	    {
	    case "Easy":
	    case "easy":
	    case "EASY":
	    case "e":
	    case "E":
	    	easy();
	    	break;
	    case "Medium":
	    case "medium":
	    case "MEDIUM":
	    case "m":
	    case "M":
	    	medium();
	    	break;
	    case "Hard":
	    case "hard":
	    case "HARD":
	    case "h":
	    case "H":
	    	hard();
	    	break;
	    }
	}
	
	public static void easy() throws IOException 
	{
		int biscuit_num = 0;
		int stick_num = 0;
		
		int[] biscuit_arr = {14, 51, 66, 75};
		int[] stick_arr = {7, 23, 35, 45}; 
		
		int[] snakes_heads ={32, 60, 63, 70, 73, 82, 89, 97}; 
		int[] ladder_bottom = {6, 8, 26, 50, 55, 59}; 
		
		player_choice(biscuit_num, stick_num, biscuit_arr, stick_arr, snakes_heads, ladder_bottom);
	}
	
	public static void medium() throws IOException 
	{
		int biscuit_num = 0;
		int stick_num = 0;
		
		int[] biscuit_arr = {35, 51};
		int[] stick_arr = {7, 23};
		
		int[] snakes_heads ={32, 60, 63, 70, 73, 82, 89, 97}; 
		int[] ladder_bottom = {6, 8, 26, 50, 55, 59};   
		
		player_choice(biscuit_num, stick_num, biscuit_arr, stick_arr, snakes_heads, ladder_bottom);
	}
	
	public static void hard() throws IOException 
	{
		int biscuit_num = 0;
		int stick_num = 0;
		
		int[] biscuit_arr = {35};
		int[] stick_arr = {7};
		
		int[] snakes_heads ={32, 60, 63, 70, 73, 82, 89, 97}; 
		int[] ladder_bottom = {6, 8, 26, 50, 55, 59}; 
		
		player_choice(biscuit_num, stick_num, biscuit_arr, stick_arr, snakes_heads, ladder_bottom);
	}
	
	public static void player_choice(int biscuit_num, int stick_num, int[] biscuit_arr, int[] stick_arr, int[] snakes_heads, int[] ladder_bottom) throws IOException 
	{
		{
			Scanner input = new Scanner(System.in);  
		    System.out.print("How many opponents? (1 / 2 / 3): ");

		    String p_choice = input.nextLine();
		    
		    switch (p_choice) 
		    {
		    case "1":
		    	two_players(biscuit_num, stick_num, biscuit_arr, stick_arr, snakes_heads, ladder_bottom);
		    	break;
		    case "2":
		    	three_players(biscuit_num, stick_num, biscuit_arr, stick_arr, snakes_heads, ladder_bottom);
		    	break;	
		    case "3":
		    	four_players(biscuit_num, stick_num, biscuit_arr, stick_arr, snakes_heads, ladder_bottom);
		    	break;	
		    default:
		    	System.out.println("You must enter a number between 1 to 3.");
		    	player_choice(biscuit_num, stick_num, biscuit_arr, stick_arr, snakes_heads, ladder_bottom);
		    	break;
		    }
		}
	}
	

	public static void two_players(int biscuit_num, int stick_num, int[] biscuit_arr, int[] stick_arr, int[] snakes_heads, int[] ladder_bottom) throws IOException 
	{
		int initial_num = 0;
		
		Player[] player = new Player[2];  // new stands for create an array object
		player[0] = new Player("Red", 1); // new stands for create an Player object
		player[1] = new Player("Blue", 1);
		
		System.out.println("Current Number of Players: " + player.length);
		
		ArrayList<String> finished_player = new ArrayList<>();
		
		player_loop(player, finished_player, initial_num, biscuit_num, stick_num, biscuit_arr, stick_arr, snakes_heads, ladder_bottom);
	}
	
	public static void three_players(int biscuit_num, int stick_num, int[] biscuit_arr, int[] stick_arr, int[] snakes_heads, int[] ladder_bottom) throws IOException 
	{
		int initial_num = 0;
		
		Player[] player = new Player[3];  // new stands for create an array object
		player[0] = new Player("Red", 1); // new stands for create an Player object
		player[1] = new Player("Blue", 1);
		player[2] = new Player("Orange", 1);
		
		System.out.println("Current Number of Players: " + player.length);
		
		ArrayList<String> finished_player = new ArrayList<>();
		
		player_loop(player, finished_player, initial_num, biscuit_num, stick_num, biscuit_arr, stick_arr, snakes_heads, ladder_bottom);
	}
	
	public static void four_players(int biscuit_num, int stick_num, int[] biscuit_arr, int[] stick_arr, int[] snakes_heads, int[] ladder_bottom) throws IOException 
	{
		int initial_num = 0;
				
		Player[] player = new Player[4];  // new creates an array object
		player[0] = new Player("Red", 1); // new creates an Player object
		player[1] = new Player("Blue", 1);
		player[2] = new Player("Orange", 1);
		player[3] = new Player("Purple", 1);
		
		System.out.println("Current Number of Players: " + player.length);
		
		ArrayList<String> finished_player = new ArrayList<>();
		
		player_loop(player, finished_player, initial_num, biscuit_num, stick_num, biscuit_arr, stick_arr, snakes_heads, ladder_bottom);
	}
	

	public static void player_loop(Player[] player, ArrayList<String> finished_player, int i, int biscuit_num, int stick_num, int[] biscuit_arr, int[] stick_arr, int[] snakes_heads, int[] ladder_bottom) throws IOException 
	{
		
		for (i=0; i<(player.length); i++) 
		{
			String current_player_name = player[i].getName();
			int current_player_score = player[i].getScore();
			//the_game(player, finished_player, current_player_name, current_player_score, i, biscuit_num, stick_num, biscuit_arr, stick_arr, snakes_heads, ladder_bottom);
			the_game(player, finished_player, current_player_name, current_player_score, i, biscuit_num, stick_num, biscuit_arr, stick_arr, snakes_heads, ladder_bottom);
		}
		
		player_loop(player, finished_player, i, biscuit_num, stick_num, biscuit_arr, stick_arr, snakes_heads, ladder_bottom);
	}
	
	public static void the_game(Player[] player, ArrayList<String> finished_player, String names, int scores, int i, int biscuit_num, int stick_num, int[] biscuit_arr, int[] stick_arr, int[] snakes_heads, int[] ladder_bottom) throws IOException 
	{
		int[] dice_Num = {1, 2, 3, 4, 5, 6};
		int Dice_Roll = new Random().nextInt(dice_Num.length)+1;
		
		int previous_position = 0;
		
		System.out.println();
		System.out.println("____________________________________");
		System.out.println();
		
    	System.out.println("Finished Players: " + finished_player.size());
    	System.out.println();
    	
		Scanner input = new Scanner(System.in);  
		System.out.print(names + " - Press R to roll the dice: ");

	    String roll = input.nextLine();
	    
	    switch(roll) 
	    {
	    case "r":
	    	previous_position = scores;
	    	scores += Dice_Roll;
			player[i].setScore(scores);
			scores = player[i].getScore();
	    	System.out.println(names + " rolled a " + Dice_Roll + " and moved from square " + previous_position + " to " + scores);
			System.out.println();
			break;
	    case "end":
	    	goodbye();
			break;
	    }
	    
	    if (scores > 100) 
	    {	
	    	System.out.println("Dice Roll is too High");
	    	System.out.println();
	    	scores -= Dice_Roll;
	    }
	    
	    if (scores == 100) 
	    {
	    	goodbye();
	    }
	    
	    if (Dice_Roll == 6) 
	    {
	    	the_game(player, finished_player, names, scores, i, biscuit_num, stick_num, biscuit_arr, stick_arr, snakes_heads, ladder_bottom);
	    }
	    
	    if (finished_player.size() == (player.length - 1)) 
	    {
	    	goodbye();
	    }
	    
	    if (finished_player.size() > 0) 
	    {	
	    	for (int t=0; t<(finished_player.size()); t++) 
    	    {
    	    	if (finished_player.get(t) == names) 
    	    	{
    	    		biscuits(player, finished_player, scores, biscuit_num, stick_num, biscuit_arr, stick_arr, snakes_heads, ladder_bottom);
    	    		sticks(player, finished_player, scores, i, biscuit_num, stick_num, biscuit_arr, stick_arr, snakes_heads, ladder_bottom);
    	    		snakes(scores, previous_position, i, player, finished_player, biscuit_num, stick_num, biscuit_arr, stick_arr, snakes_heads, ladder_bottom);
    	    		ladders(scores, previous_position, i, player, finished_player, biscuit_num, stick_num, biscuit_arr, stick_arr, snakes_heads, ladder_bottom);
    	    		player_loop(player, finished_player, i, biscuit_num, stick_num, biscuit_arr, stick_arr, snakes_heads, ladder_bottom);
    	    	}
    	    	else 
    	    	{
    	    		the_game(player, finished_player, names, scores, i, biscuit_num, stick_num, biscuit_arr, stick_arr, snakes_heads, ladder_bottom);
    	    	}
    	    }
	    }
	}
	
	public static void snakes(int current_position, int previous_position, int i, Player[] player, ArrayList<String> finished_player, int biscuit_num, int stick_num, int[] biscuit_arr, int[] stick_arr, int[] snakes_heads, int[] ladder_bottom) 
	{
		int[] snakes_tails = {13, 38, 3, 25, 47, 43, 53, 12}; 
		
		
		
		for (int sh=0; sh<(snakes_heads.length); sh++) 
		{
		    	if (current_position == snakes_heads[sh]) 
		    	{
		    		if (biscuit_num > 0)
		    		{
		    			System.out.println("Luckily you have a biscuit. You feed it to them and you remain at square at" + current_position);
		    			System.out.println("Number of biscuit - 1");
		    			System.out.println();
		    			biscuit_num -= 1;
		    		}
		    		else 
		    		{
		    			previous_position = current_position;
		    			current_position = snakes_tails[sh];
		    			System.out.println("You have fallen down from square " + previous_position + " to " + current_position);
		    			System.out.println();
		    		}
		    	}
		    } 
	}
	
	public static void ladders(int current_position, int previous_position, int lt, Player[] player, ArrayList<String> finished_player, int biscuit_num, int stick_num, int[] biscuit_arr, int[] stick_arr, int[] snakes_heads, int[] ladder_bottom) 
	{
		int[] ladder_top = {16, 41, 29, 93, 80, 84};
		
		
		for (lt=0; lt<(ladder_bottom.length); lt++) 
		    {
		    	if (current_position == ladder_bottom[lt]) 
		    	{
		    		ladders(current_position, previous_position, lt, player, finished_player, biscuit_num, stick_num, biscuit_arr, stick_arr, snakes_heads, ladder_bottom);
		    	}
		    }
		    
		if (stick_num > 0) 
		{
			System.out.println("Because you currently have a stick, you can climb up ten extra squares");
			System.out.println();
			previous_position = current_position;
			current_position = ladder_top[lt] + 10;
			stick_num -= 1;
			System.out.println("You have fallen down from square " + previous_position + " is now " + current_position);
			System.out.println();
		}
		else 
		{
			previous_position = current_position;
			current_position = ladder_top[lt];
			System.out.println(previous_position + " is now " + current_position);
			System.out.println();
		}
	}
	
	public static void biscuits(Player[] player, ArrayList<String> finished_player, int current_position, int biscuit_num, int stick_num, int[] biscuit_arr, int[] stick_arr, int[] snakes_heads, int[] ladder_bottom) 
	{		
		 for (int b=0; b<(biscuit_arr.length); b++) 
		    {
			 if (current_position == biscuit_arr[b]);
			 	System.out.println("Yor have a biscuit");
		    	System.out.println();
		    	biscuit_num += 1;
		    }
	}
	
	public static void sticks(Player[] player, ArrayList<String> finished_player, int current_position, int i, int biscuit_num, int stick_num, int[] biscuit_arr, int[] stick_arr, int[] snakes_heads, int[] ladder_bottom) 
	{
		for (int s=0; s<(stick_arr.length); s++) 
	    {
			if (current_position == stick_arr[s]) 
	    	{
	    		System.out.println("You has received a biscuit");
		    	System.out.println();
		    	stick_num += 1;
	    	}
	    }
	}
	
	public static void goodbye() throws IOException 
	{
		System.out.println();
    	System.out.println("Goodbye");
    	selectGame();
	}

	// 4) Snakes and Ladders B - main 
	public static void mainSnakesLaddersB() throws IOException {
		System.out.println("Welcome to the Game");  		// prints the welcome message
		System.out.println(" ");
		System.out.println("How many players are playing? (Choose between 2 to 4)"); 
		// prints this statement and tells user to choose the number of players
		Scanner sc = new Scanner(System.in);
		int numplayer = sc.nextInt();						// user input number of players
		String[] name = {"Red", "Blue", "Green", "Yellow"}; //  name of the players in an array
		String[] player = new String[numplayer]; 		    //  players is an array which contains number of players
		int[] location = new int [numplayer];			    //  location is an array which contains number of players
		int[] winposition = new int[numplayer];				//   win position is an array which contains number of players
		boolean[] biscuit = new boolean [numplayer]; 		//   biscuit is an array which contains number of players
		boolean[] big_stick = new boolean [numplayer]; 		//   big stick is an array which contains number of players
		boolean[] Move = new boolean [numplayer]; 			//   move is an array which contains number of players
			for (int i = 0; i < numplayer; i++) 
			{
				player [i] = name[i]; 		// players are assigned with name 
				location [i] = 1;			// location is set to square 1
				biscuit [i] = false;		// biscuit is set to false at the start
				big_stick [i]= false;		// big stick is set to false at the start
				Move [i] = false;			// move is set to false at the start
				winposition[i] = 0;			// win position is set to 0
			}
		System.out.println("Game Started");	
		System.out.println(" ");
		int[][] snake = Snakes();			 		// snake is an array which calls the method Snakes
		int[][] ladder = Ladders();					// ladder is an array which calls the method Ladder
		int[] biscuitlocation = biscuit();			// biscuit location is an array which calls the method biscuit
		int[] bigsticklocation = big_stick(); 		// big stick is an array which calls the method big_stick
		boolean[] win = new boolean [numplayer];	// win is an array which contains number of players
		boolean dicesix = false; 					// dice six is set to false at the start
		int playernumber = numplayer; 				// player number is set to number of players
		int i = 0;
		int j = 0;
		do 
		{ 
			if(playernumber > 1) 
			{
				for (i = 0; i <= numplayer-1; i++)	// for loop to have all the players
				{
					if(!win[i]) // if win is false then player can play
					{
						System.out.println(player[i] + "  Press 'r' to roll dice"); 
						// prints this statement and tells user to press r to play
						Game(player, location, biscuit, big_stick, Move, snake, ladder, win, i, winposition, biscuitlocation, bigsticklocation, dicesix);
						// game method is called
						if (i == numplayer-1) 		// this statement is for players
						{
							i = -1;
						}
					}
				}
			}
			else 
			{
				for (j = 0; j <= numplayer-1; j++)
				{
					System.out.println(player[j] + " is " + winposition[j]);
				}
			}
			if (playernumber == numplayer) // when player number is equal to number of players 
			{
				System.out.println("------------------------------------");
				System.out.println("GAME ENDED!");
				System.out.println("------------------------------------");
				selectGame();
//				System.exit(0); // game ends 
				break;
			}
		}
		while (win[i]); // this continues in a loop until players reach 100th square	
	}
	public static void Game(String[] player,int[] location,boolean[] biscuit,boolean[] big_stick,boolean[] Move,int[][] snake, int [][] ladder, boolean[] win, int playerindex, int[] winposition, int[] biscuitlocation, int[] bigsticklocation, boolean dicesix) 
	{
		String roll = null; 				// roll is set to null
		int dice = 1;						// dice is set to 1
		int pos = 1;						// position is set to 1
		int[][] snakes = Snakes();			// snakes is a 2d array which contains Snakes method
		Scanner s = new Scanner(System.in);
	    roll = s.next();					// user input r to toll the dice
		dice = roll_Dice();					// dice is set to the method roll_Dice
			
			if (!Move[playerindex])  		// when move is false all the below code will work
			{
				int Location = Move(location[playerindex], dice);
				location[playerindex] = Location; 
				if(Location == 2) 			// if location is equal to 2 then the code below will work
				{
					System.out.println("Lets go" + " " + player[playerindex]);
					System.out.println(player[playerindex] + " " + "rolled" + " " + dice + " " + "and moved to square" + " " + location[playerindex] );
					System.out.println("------------------------------------");
					Move[playerindex] = true;
				}
				else
				{
					System.out.println(player[playerindex] + " " + "rolled" + " " + dice);
					System.out.println("Try again! (You need 1 or 6 to move)");
					System.out.println("------------------------------------");
				}
			}
			else
			{
				int Location = location[playerindex] + dice;
				if(Location == 100) 		// if location is equal to 100 then the code below will work and player wins
				{
					location[playerindex] += dice;
					System.out.println(" ");
					System.out.println(player[playerindex] + " " + "rolled" + " " + dice + " " + "and moved to square" + " " + location[playerindex]);
					System.out.println("------------------------------------");
					System.out.println(win(player, location, playerindex));
					System.out.println("------------------------------------");
					System.out.println();
					System.out.println(" ");
					win[playerindex] = true;
					winposition[playerindex] = pos;
					pos++;
				}
				else if(Location > 100) 	// if location is bigger then 100 then the code below will work
				{
					System.out.println(player[playerindex] + " " + "rolled" + " " + dice);
					System.out.println(player[playerindex] + " need " + (100-location[playerindex]) + " to win " );
					System.out.println("------------------------------------");
				}
				else 
				{
					System.out.println(player[playerindex] + " " + "rolled" + " " + dice + " " + "and moved from square" + " " + location[playerindex] + " to sqaure " + Location);
					System.out.println("------------------------------------");
					location[playerindex] += dice;
					for (int i = 0; i < 2; i++)
					{
						if (biscuitlocation[i] == location[playerindex]) 	// if location equal to biscuit then the code below will work
						{
							System.out.println(" ");
							System.out.println("------------------------------------");
							System.out.println(player[playerindex] + " Get Biscuit and feed the next snake you met");
							System.out.println("------------------------------------");
							System.out.println(" ");
							biscuit[playerindex] = true;
						}
					}
					for (int i = 0; i < 2; i++)
					{
						if (bigsticklocation[i] == location[playerindex])	// if location equal to big stick then the code below will work
						{
							System.out.println(" ");
							System.out.println("------------------------------------");
							System.out.println(player[playerindex] + " Get Big Stick and scare the next snake you met");
							System.out.println("------------------------------------");
							System.out.println(" ");
							big_stick[playerindex] = true;
						}
					}
					for (int i = 0; i < snake.length; i++) 
					{
						for(int j = 0; j < snake[0].length; j++)
						{
						if (snake[0][j] == location[playerindex])	// if location equal to snake then the code below will work
						{
							if (biscuit[playerindex])	// if location of the player equal to biscuit then the code below will work
							{
								System.out.println(" ");
								System.out.println("------------------------------------");
								System.out.println(player[playerindex] + " gave the Snake a Biscuit and can stay on the current square");
								System.out.println("------------------------------------");
								System.out.println(" ");
								location[playerindex] = snake[0][j];
								biscuit[playerindex] = true;
							}
							else if (big_stick[playerindex]) // if location of the player equal to big stick then the code below will work
							{
								if(location[playerindex] > 90) 	// if location of the player is bigger then 90 then big stick won't work
								{
									System.out.println(" ");
									System.out.println(player[playerindex] + " you can't scare the Snake after the 90th square");
									System.out.println("------------------------------------");
									location[playerindex] = snake[1][j];
									System.out.println(player[playerindex] + " Swallowed by Snake and moved to " + location[playerindex]  );
									System.out.println("------------------------------------");
									System.out.println(" ");
								}
								else 	// if location of the player is less than 90 then the code below will work
								{
									System.out.println(" ");
									System.out.println("------------------------------------");
									System.out.println(player[playerindex] + " scare the snake. Snake now moved to " + (snake[0][j] + 10));
									System.out.println("------------------------------------");
									System.out.println(" ");
									snake[0][j] += 10;
									snake[1][j] += 10;
									big_stick[playerindex] = false;
								}
							}
							else		// when location of the player is on snakes head the code below will work
							{
								location[playerindex] = snake[1][j];
								System.out.println(" ");
								System.out.println("------------------------------------");
								System.out.println(player[playerindex] + " Swallowed by Snake and moved to " + location[playerindex]  );
								System.out.println("------------------------------------");
								System.out.println(" ");
							}
						}
					}
						}
				}
					for (int i = 0; i < ladder.length-1; i++) 
					{
						for(int j = 0; j < ladder[0].length; j++)
						{
						if (ladder[0][j] == location[playerindex])
						{
								location[playerindex] = ladder[1][j]; // when location of the player is at the bottom of the ladder the code below will work
								System.out.println(" ");
								System.out.println("------------------------------------");
								System.out.println(player[playerindex] + " Climbed up the Ladder and moved to " + location[playerindex]  );
								System.out.println("------------------------------------");
								System.out.println(" ");
						}
					}
				}
			}
			if (dice == 6)		// when player get 6 on dice they will get extra turn and code below will work
			{
				dicesix = true;
				System.out.println(player[playerindex] + " rolled " + dice + "! " + player[playerindex] + " have additional turn " );
				System.out.println("------------------------------------");
				Game(player, location, biscuit, big_stick, Move, snake, ladder, win, playerindex, winposition, biscuitlocation, bigsticklocation, dicesix);
			}
		}

	public static int[][] Snakes() 						// 2d array of Snakes
	{
		int[][] snake = 
			{
				{23,69,56,43,86,62,89,96,94,98},		// head of the snakes
				{5, 12,25,39,54,59,72,84,73,58},		// tail of the snakes
			};
		return snake;
	}
	
	public static int[][] Ladders() 					// 2d array of ladders
	{
		int[][] ladder = 
			{
				{3, 16, 8,37,33,50,64,80,77,89},		// bottom of the ladder
				{21,26,55,76,74,70,83,99,95,91},		// top of the ladder
			};
		return ladder;
	}
	
	public static int[] big_stick() 					// big_stick is in an array
	{
		int[] bigstick = {29,44};						// big stick values
		return bigstick;
	}
	
	public static int[] biscuit() 						// biscuit is in an array
	{
		int[] biscuit = {55,93};						// biscuit values
		return biscuit;
	}
	
	public static int roll_Dice()						// roll dice method
	{
		Random rand = new Random();						// random method imported
		int dice = rand.nextInt(6)+1;					// dice is set to random
		return dice;
	}
	
	public static int Move(int location, int dice) 		// move method
	{
		switch(dice) 
		{
			case 1: 									// if player get 1 on dice or
			case 6:										// if player get 6 on dice 
				location = 2;							// player will move to square 2
				break;
		}
		return location;
	}
	
	public static String win(String[] player, int[] location, int playerindex) 	// win method
	{
		if(location[playerindex] == 100); 						// if location of the player is 100 
		{
			return (player[playerindex] + " " + "Win"); 		// player win the this statement is printed
		}
	}
	
	// 5) Mastermind A - main
	public static void mainMastermindA() throws IOException {
        ui = new Scanner(System.in);
        while (true) {
        	 System.out.println("__   __           _                      _         _");        //Those print statements will become clear once the programm is running
             System.out.println("l  \\/  l         l l                    (_)       l l");          
             System.out.println("l      l __ _ ___l l_ ___ _ __ _ __ ___  _ _ _   _l l");
             System.out.println("l \\  / l/  _`/ __l __/ _ \\ `__\\ `_ _` _\\l l _`\\/ _l l");
             System.out.println("l l\\/l l  (_l\\__ \\ ll  __/ l  l l l l l l l l l (_l l ");
             System.out.println("l_l  l_l\\__,_l___/\\__\\___l_l  l_l l_l l_l_l l_l\\__,_l\n");
            System.out.println("Welcome to Mastermind\n");
            System.out.println("Mastermind is a difficult puzzle game, in which" 
            + "\none player tries to guess the code their opponent comes up with."+
            		"\nIn this case the opponent will be the the computer."+"\nThe code is made of different colours!"+"\nThe first thing that i would encourage you to do is to choose"
            +"\nthe amount of guesses that you want to have."+"\nBy standart it`s 12, but you can chose as many as you want.\n\n");
            remainingGuesses = guess();
            actualString = menuChoices();
            //System.out.println("String: " + actualString); //<- Get rid of the comment notation if you want to see the answer all the time!
            ui.nextLine();
            //getting guess and returning
            while (remainingGuesses > 0) {
                if (getUserGuesses()) {               	
                    break;
                }
                remainingGuesses--;
            }
            //result calculation and printing
            int result = calculateResult();
            System.out.println("You Scored " + result + " points.");
            exit();
                break;

        }

        //closing Scanner Stream
        closeScanner();
	}
    private static int calculateResult() {
        return remainingGuesses + param1 + param2;

    }

    private static void exit() throws IOException {
    	 ui = new Scanner(System.in);
    	  
         Scanner ui = new Scanner(System.in);

         System.out.println("\nYou Win!"+"Thank you for playing!"+"\nPlease chose from the following"+"\n1. Restart the game."+"\n2. QUIT");
         int re = ui.nextInt();

         if(re==1) {
             menuChoices();
             
         }

         if (re==2){
             selectGame();
             
         }
    }
        	          	        	               
    private static void Rules() {
    	System.out.println("RULES:\n"+"\n1.Only accepted guess values are \"R\", \"G\", \"B\", \"M\", \"W\", \"P\", \"I\", \"Y\", \"O\", \"S\"" +
    "\n2.Only upper case allowed"+"\n3.If you use invalid charechters the guess will be counted!"+
    			"\n4.If you see `+` sight it would indicate a right colour in the right position."
    +"\n5.If you see `-` sign it means that you have guessed the colour but not the position.\n\n");

    }

    private static boolean getUserGuesses() {
        System.out.println("Please Enter your guess: ");
        String guess = ui.nextLine();

        if (guess.equalsIgnoreCase(actualString))
            return true;
        for (int i = 0; i < guess.length(); i++) {
            if (guess.charAt(i) == actualString.charAt(i))
                System.out.print("+");
            else if (actualString.contains("" + guess.charAt(i)))
                System.out.print("-");
        }
        System.out.println();
        return false;
    }

    private static String menuChoices() {
        System.out.println("1.Length of code 3 - Range of colours 6\n" +
    "2.Length of code 3 - Range of colours 7\n" +
        		"3.Length of code 3 - Range of colours 8\n"
    + "4.Length of code 3 - Range of colours 9\n"
        		+ "5.Length of code 3 - Range of colours 10\n"
    + "6.Length of code 4 - Range of colours 6\n"
        		+ "7.Length of code 4 - Range of colours 7\n"
    + "8.Length of code 4 - Range of colours 8\n"
        		+ "9.Length of code 4 - Range of colours 9\n"
    + "10.Length of code 4 - Range of colours 10\n");

        int option = 0;

        while (true) {
            try {
                option = ui.nextInt();
                switch (option) {    //switch statement with cases used to set different parameters fot the generation of the code
                    case 1:
                        param1 = 3;
                        param2 = 6;
                        Rules();
                        break;
                    case 2:
                        param1 = 3;
                        param2 = 7;
                        Rules();
                        break;
                    case 3:
                        param1 = 3;
                        param2 = 8;
                        Rules();
                        break;
                    case 4:
                        param1 = 3;
                        param2 = 9;
                        Rules();
                        break;
                    case 5:
                        param1 = 3;
                        param2 = 10;
                        Rules();
                        break;
                    case 6:
                        param1 = 4;
                        param2 = 6;
                        Rules();
                        break;
                    case 7:
                        param1 = 4;
                        param2 = 7;
                        Rules();
                        break;
                    case 8:
                        param1 = 4;
                        param2 = 8;
                        Rules();
                        break;
                    case 9:
                        param1 = 4;
                        param2 = 9;
                        Rules();
                        break;
                    case 10:
                        param1 = 4;
                        param2 = 10;
                        Rules();
                        break;
                    default:
                        System.out.println("You've entered a value that isn't a menu option Try again please");
                        continue;
                }
                return codegen(param1, param2);
            } catch (Exception e) {
                System.out.println("Only Integers allowed 1-10");
                ui.nextLine();
                continue;
            }

        }
    }

    private static void closeScanner() {
        ui.close();
    }

    static String codegen(int param1, int param2) {
        String[] codegen = {"R", "G", "B", "M", "W", "P", "I", "Y", "O", "S"};
        String result = "";
        Random codeGenerator = new Random();
        for (int i = 0; i < param1; i++) {
            int nextRandom = codeGenerator.nextInt(param2);
            result += codegen[nextRandom];
        }
        return result;
    }

    static int guess() {
        System.out.println("How many guesses do you want to play with?\n");
        while (true) {
            try {
                int guess = ui.nextInt();
                return guess;
            } catch (Exception e) {
                System.out.println("Only Integers allowed");
                ui.nextLine();
            }
        }


    }
	
    // 6) Mastermind B - main
    public static void mainMastermindB() throws IOException {
		/*Output statement containing welcome message*/
		System.out.println("***********************");
		System.out.println("Welcome to Mastermind!");
		System.out.println("***********************");
		System.out.println();

		while (true) { /*while is set true in order to output the content below*/
			System.out.println("Would you like to play? (Yes/No)");
			Scanner input = new java.util.Scanner(System.in); //Set scanner to check for user input off keyboard
			String doplay = input.nextLine(); /*String variable "doplay" will act as string input for the user which will then be checked by the scanner*/

			if (doplay.equalsIgnoreCase("yes")) { /*conditional statement regarding if the users input is equal to "yes"*/
				System.out.println("Try to guess a code of 4 colours."); 
				System.out.println("Red (r), Orange (o), Yellow (y), Green (g), Blue (b), Pink(p)");
				//if "yes" the following will be output above
				System.out.println("--------------------------");
				System.out.println("Please select the mode you would like to play:");
				System.out.println("---- N (For Normal) ----");
				System.out.println("---- VB (For VariationB) ----");
				System.out.println("Please enter the initials of the mode you would like to play, as can be seen above");
				Scanner o = new Scanner(System.in);
				String userchoice = o.nextLine();
				if (userchoice.equalsIgnoreCase("N")){ //if choice is N
					GameModel gameModel = new GameModel("String a"); //run constructors
					GameController gameController = new GameController(gameModel, userchoice);
				}
				//if choice is VB
				else if (userchoice.equalsIgnoreCase("VB")){
					GameModel gameModel = new GameModel(); //run constructors
					GameController gameController = new GameController(gameModel, userchoice);
				}
				//if the user selects neither N or VB
				else if (userchoice != "VB" | userchoice != "N"){
				System.out.println("Invalid, please try agai \n"); //output
				}

			} else if (doplay.equalsIgnoreCase("no")) { //if user says no
				System.out.println("Thank you for playing MasterMind! Goodbye."); //output
				selectGame();
				break; //end
			}

		}
    }
    
    // 7) Battleship B - main
	public static void mainBattleship() throws IOException {
		
		windowMain = new Stage();
		windowMain.setTitle("Battleship");
		
		sceneMain = new Scene(layoutMenu, windowWidth, windowHeight); // Main Menu
		sceneLobby = new Scene(layoutLobby, windowWidth, windowHeight); // Lobby Menu
		sceneOriginalGame = new Scene(layoutOriginalMode, windowWidth, windowHeight); // Original Game Mode Menu
		sceneOption = new Scene(layoutOption, windowWidth, windowHeight);
		sceneEnd = new Scene(layoutEnd, windowWidth, windowHeight);
		
		Background backgroundMain = BattleshipResource.ImageBackground1(windowWidth, windowHeight); // Background image
		
		// Main Menu -----------------------------------------
		HBox topMenu = new HBox();
		topMenu.setAlignment(Pos.CENTER);
		
		// Title image
		ImageView titleMain  = BattleshipResource.ImageTitle1();
		
		topMenu.getChildren().addAll(titleMain);
		
		// Centre of the menu		
		VBox centreMenu = new VBox(10); // putting an integers between the parameters puts a space between each object.
		centreMenu.setAlignment(Pos.CENTER);
		
		// Play button
		BattleshipButton  btnPlay = new BattleshipButton("PLAY");
		btnPlay.setOnAction(e -> {
			windowMain.setScene(sceneLobby);
		});	
		
		// Option button
		BattleshipButton btnOptions = new BattleshipButton("HELP");
		btnOptions.setOnAction(e -> windowMain.setScene(sceneOption));
		
		// Quit Button
		BattleshipButton btnQuit = new BattleshipButton("QUIT");
		btnQuit.setOnAction(e -> {
			try {
				quitProgram();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		windowMain.setOnCloseRequest(e -> {
			e.consume(); // this will allow the program to close properly otherwise it will still close if user presses no
			try {
				quitProgram();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		centreMenu.getChildren().addAll(btnPlay, btnOptions, btnQuit);
		
		layoutMenu.setTop(topMenu);
		layoutMenu.setCenter(centreMenu);
		layoutMenu.setBackground(backgroundMain);
		
		// Lobby Menu ----------------------------------
		HBox centreLobby = new HBox(10);
		centreLobby.setAlignment(Pos.CENTER);		
		
		// Original button
		BattleshipButton btnOriginal = new BattleshipButton("ORIGINAL");
		btnOriginal.setOnAction(e -> {
		    AircraftCarrier = new Ship(Math.random() < 0.5, 5, "AircraftCarrier");
		    Battleship = new Ship(Math.random() < 0.5, 4, "Battleship");
		    Submarine = new Ship(Math.random() < 0.5, 3, "Submarine");
		    Destroyer = new Ship(Math.random() < 0.5, 3, "Destroyer");
		    PatrolBoat = new Ship(Math.random() < 0.5, 2, "PatrolBoat");
		    Kraken = new Ship(Math.random() < 0.5, 1, "Kraken");
		    Cetus = new Ship(Math.random() < 0.5, 1, "Cetus"); 
			counterShips = 5;
			counterMonsters = 2;
			MainGame.ships = 5;
			
			windowMain.setScene(sceneOriginalGame);
	        enemyBoard = new MainGame(event -> {
	        	Square cell = (Square) event.getSource();
	            if (cell.wasShot)
	                return;
	            cell.shoot(); // this line lets the user shoot.
	            if (enemyBoard.ships == 0) {
	                windowMain.setScene(sceneEnd);
	            }

	        });
			startGame();
			layoutOriginalMode.setCenter(enemyBoard);
			statusReport.appendText("Aircraft Carrier is un-sunk \n");
			statusReport.appendText("Battleship is un-sunk \n");
			statusReport.appendText("Submarine is un-sunk \n");
			statusReport.appendText("Destroyer is un-sunk \n");
			statusReport.appendText("Patrol Boat is un-sunk \n");
		});
		// CLASSIC button
		BattleshipButton btnClassic = new BattleshipButton("CLASSIC");
		
		// Back button (lobby to main)
		BattleshipButton btnBackLobbyToMain = new BattleshipButton("BACK");
		btnBackLobbyToMain.setOnAction(e -> windowMain.setScene(sceneMain));
		
		CheckBox cbCheats = BattleshipResource.CustomCheckBox("Enable cheats?", 20);
		cbCheats.setOnAction(e -> {
			if (cbCheats.isSelected()) {
				enableCheats = true;
			}
			else if (!cbCheats.isSelected()) {
				enableCheats = false;
			}
		});
		
		centreLobby.getChildren().addAll(btnOriginal, btnClassic);
		
		layoutLobby.setTop(cbCheats);
		layoutLobby.setCenter(centreLobby);
		layoutLobby.setBottom(btnBackLobbyToMain);
		layoutLobby.setBackground(backgroundMain);
		
		// Option Menu -------------------------		
		Label labelHelpMessage1 = BattleshipResource.CustomLabel("1) The black squares indicates the shots that you have missed", true, 800, 20);
		Label labelHelpMessage2 = BattleshipResource.CustomLabel("2) The red squares indicates that you missed hitting a ship", true, 800, 20);
		Label labelHelpMessage3 = BattleshipResource.CustomLabel("3) The green squares indicates that you hit a sea monster", true, 800, 20);
		Label labelHelpMessage4 = BattleshipResource.CustomLabel("4) There are 5 different ships: Aircraft Carrier (size 5), Battleship (size 4), Submarine (size 3), Destroyer (size 3) and Patrol Boat (size 2) ", true, 800, 20);
		Label labelHelpMessage5 = BattleshipResource.CustomLabel("5) There are 2 different monsters: Kraken who will return your score back to 0 and Cetus who will move all un-sunk ships", true, 800, 20);
		Label labelHelpMessage6 = BattleshipResource.CustomLabel("6) You will win when you manage to destroy all the ships", true, 800, 20);
		
		VBox boxHelpMessage = new VBox(10);
		boxHelpMessage.getChildren().addAll(labelHelpMessage1, labelHelpMessage2, labelHelpMessage3, labelHelpMessage4, labelHelpMessage5, labelHelpMessage6);
		boxHelpMessage.setAlignment(Pos.CENTER);
		
		// Back button (lobby to main)
		BattleshipButton btnBackOptionToMain = new BattleshipButton("BACK");
		btnBackOptionToMain.setOnAction(e -> windowMain.setScene(sceneMain));
		
		layoutOption.setCenter(boxHelpMessage);
		layoutOption.setBottom(btnBackOptionToMain);
		layoutOption.setBackground(backgroundMain);
		
		// Original Game Mode -------------------*------        
		
		// I have set the game board in the Original button in the lobby
		VBox boxStatus = new VBox(10);
		Label statusLabel = BattleshipResource.CustomLabel("STATUS REPORT: ", false, 200, 10);
		statusReport = new TextArea();
		statusReport.setMaxHeight(400);
		statusReport.setMaxWidth(200);
		statusReport.setEditable(false);

		
		boxStatus.getChildren().addAll(statusLabel, statusReport);
		boxStatus.setAlignment(Pos.CENTER);
		
		HBox boxScore = new HBox();
		Label labelScore = BattleshipResource.CustomLabel("Score: ", true, 300, 20);
		currentScore = BattleshipResource.CustomLabel("0", true, 300, 20);
		boxScore.getChildren().addAll(labelScore, currentScore);
		boxScore.setAlignment(Pos.CENTER);
		
		BattleshipButton btnBackOriginalToLobby = new BattleshipButton("QUIT");
		btnBackOriginalToLobby.setOnAction(e -> {	
			try {
				boolean answer2 = WarningWindow.quitTask("CONFIRM QUITING GAME", "ARE YOU SURE YOU WANT TO QUIT YOUR CURRENT GAME?");
				if (answer2) { // There is no need for answer1	 == true because if answer1 is false then it won't close the program and if answer1 is true then it will close the program.
					windowMain.setScene(sceneEnd);
					layoutOriginalMode.getChildren().remove(enemyBoard);
					statusReport.setText("");
					currentScore.setText("0");
					MainGame.score = 0;
				}

			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});
		
		layoutOriginalMode.setTop(boxScore);
		layoutOriginalMode.setBottom(btnBackOriginalToLobby);
		layoutOriginalMode.setRight(boxStatus);
		layoutOriginalMode.setCenter(enemyBoard);
		layoutOriginalMode.setBackground(backgroundMain);
		
		// End Menu ----------------------
		HBox boxFinalScore = new HBox();
		Label labelFinalScore = BattleshipResource.CustomLabel("Your final Score: ", true, 900, 45);
		
		finalScore = BattleshipResource.CustomLabel("0", true, 300, 45);
		boxFinalScore.getChildren().addAll(labelFinalScore, finalScore);
		boxFinalScore.setAlignment(Pos.CENTER);
		
		VBox boxEnd = new VBox(40);
		
		BattleshipButton btnPlayAgain = new BattleshipButton("PLAY AGAIN");
		btnPlayAgain.setOnAction(e -> {
			windowMain.setScene(sceneLobby);
			statusReport.setText("");
			currentScore.setText("0");
			MainGame.ships = 5;
			MainGame.score = 0;
		});
		
		BattleshipButton btnEndToMain = new BattleshipButton("MAIN MENU");
		btnEndToMain.setOnAction(e -> {
			windowMain.setScene(sceneMain);
			statusReport.setText("");
			currentScore.setText("0");
			MainGame.ships = 5;
			MainGame.score = 0;
		});
		
		BattleshipButton btnQuitEnd = new BattleshipButton("QUIT");
		btnQuitEnd.setOnAction(e -> {
			try {
				quitProgram();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		boxEnd.getChildren().addAll(boxFinalScore, btnPlayAgain, btnEndToMain, btnQuitEnd);
		boxEnd.setAlignment(Pos.CENTER);
		
		layoutEnd.setCenter(boxEnd);
		layoutEnd.setBackground(backgroundMain);
		
		windowMain.getIcons().add(BattleshipResource.ImageIcon());		
		windowMain.setResizable(false); // prevents screen from being full screen
		windowMain.setScene(sceneMain);
		windowMain.show();
	}
	
	private static void quitProgram() throws IOException {
		boolean answer1 = WarningWindow.quitTask("CONFIRM CLOSING PROGRAM", "ARE YOU SURE YOU WANT TO CLOSE THE PROGRAM?");
		if (answer1) { // There is no need for answer1 == true because if answer1 is false then it won't close the program and if answer1 is true then it will close the program.
			windowMain.close();
			selectGame();
		}
	}
	
    public static void startGame() {
        while (counterShips > 0) {
            if (counterShips == 5) {
            	int xShip1 = random.nextInt(10);;
            	int yShip1 = random.nextInt(10);;
              	if (enemyBoard.placeShip(AircraftCarrier, xShip1, yShip1)) {
              		counterShips--;
                      }
            }
            else if (counterShips == 4) {
            	int xShip2 = random.nextInt(10);;
            	int yShip2 = random.nextInt(10);;
            	if (enemyBoard.placeShip(Battleship, xShip2, yShip2)) {
              		counterShips--;
                      }
            }
            else if (counterShips == 3) {
            	int xShip3 = random.nextInt(10);;
            	int yShip3 = random.nextInt(10);;
              	if (enemyBoard.placeShip(Submarine, xShip3, yShip3)) {
              		counterShips--;
                      }
            }
            else if (counterShips == 2) {
            	int xShip4 = random.nextInt(10);;
            	int yShip4 = random.nextInt(10);;
              	if (enemyBoard.placeShip(Destroyer, xShip4, yShip4)) {
              		counterShips--;
                      }
            }
            else if (counterShips == 1) {
            	int xShip5 = random.nextInt(10);;
            	int yShip5 = random.nextInt(10);;
              	if (enemyBoard.placeShip(PatrolBoat, xShip5, yShip5)) {
              		counterShips--;
                      }
            }
        }
        
    	while (counterMonsters > 0) {
            if (counterMonsters == 2) {
            	int xMonster1 = random.nextInt(10);
                int yMonster1 = random.nextInt(10);
            	if (enemyBoard.placeMonster(Kraken, xMonster1, yMonster1)) {
            		counterMonsters--;
                    }
            }
            else if (counterMonsters == 1) {
            	int xMonster2 = random.nextInt(10);
            	int yMonster2 = random.nextInt(10);
            	if (enemyBoard.placeMonster(Cetus, xMonster2, yMonster2)) {
            		counterMonsters--;
                    }
            }
    	}
    }
	
	public static void main(String[] args) {
		launch(args);
	}	
}

