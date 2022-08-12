package hangmanB;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import battleship.MainMenu;



public class HangmanGame {
		public static final String[] WORDS = {
		  "computer", "laptop", "pc", "mouse", "keyboard", 
		  "microphone", "phone", "android", "sports", "football",
		  "red", "yellow", "green", "blue", "pink", 
		  "happy", "true", "flase", "final", "basketball",
		  "float", "loop", "if", "go", "chair", 
		  "table", "lamp", "desk", "money", 
		  "school", "integer", "new", "doctor", "package", 
		  "private", "public", "protected", "cable", 
		  "shirt", "vest", "playstation", "xbox", "switch", 
		  "raptor", "pen", "gloves", "remote", 
		  "paint", "adapter", "socks", "sync", "speaker", "headphones"
		};

		public static final Random RANDOM = new Random();
		// Max errors before user lose
		public static final int MAX_ERRORS = 10;
		// Word to find
		private String wordToFind;
		// Word found stored in a char array to show progression of user
		private char[] wordFound;
		private int nbErrors;
		// letters already entered by user
		private ArrayList < String > letters = new ArrayList < > ();

		// Method returning randomly next word to find
		private String nextWordToFind() {
		  return WORDS[RANDOM.nextInt(WORDS.length)];
		}

		// Method for starting a new game
		public void newGame() {
		  nbErrors = 0;
		  letters.clear();
		  wordToFind = nextWordToFind();

		  // word found initialisation
		  wordFound = new char[wordToFind.length()];

		  for (int i = 0; i < wordFound.length; i++) {
		  wordFound[i] = '_';
		  }
		}

		// Method returning if word is found by user
		public boolean wordFound() {
		  return wordToFind.contentEquals(new String(wordFound));
		}

		// Method updating the word found after user entered a character
		private void enter(String c) {
		  // we update only if c has not already been entered
		  if (!letters.contains(c)) {
		  // we check if word to find contains c
		  if (wordToFind.contains(c)) {
		    // if so, we replace _ by the character c
		    int index = wordToFind.indexOf(c);

		    while (index >= 0) {
		    wordFound[index] = c.charAt(0);
		    index = wordToFind.indexOf(c, index + 1);
		    }
		  } else {
		    // c not in the word => error
		    nbErrors++;
		  }

		  // c is now a letter entered
		  letters.add(c);
		  }
		}

		// Method returning the state of the word found by the user until by now
		private String wordFoundContent() {
		  StringBuilder builder = new StringBuilder();

		  for (int i = 0; i < wordFound.length; i++) {
		  builder.append(wordFound[i]);

		  if (i < wordFound.length - 1) {
		    builder.append(" ");
		  }
		  }

		  return builder.toString();
		}

		// Play method for Hang man Game
		public void play() throws IOException {
		  try (Scanner input = new Scanner(System.in)) {
		  // we play while nbErrors is lower than max errors or user has found the word
		  while (nbErrors < MAX_ERRORS) {
		    System.out.println("\nEnter a letter : ");
		    // get next input from user
		    String str = input.next();

		    // keep first letter
		    if (str.length() > 1) {
		    str = str.substring(0, 1);
		    }

		    // update word found
		    enter(str);

		    // display current state
		    System.out.println("\n" + wordFoundContent());

		    // check if word is found
		    if (wordFound()) {
		    System.out.println("\nYou win!");
		    MainMenu.selectGame();
		    break;
		    } else {
		    // we display nb tries remaining for the user
		    System.out.println("\n=> Nb tries remaining : " + (MAX_ERRORS - nbErrors));
		    }
		
		  }

		  if (nbErrors == MAX_ERRORS) {
		    // user lose
		    System.out.println("\nYou lose!");
		    System.out.println("=> Word to find was : " + wordToFind);
		    MainMenu.selectGame();
		  }
		  }
		}

//		public static void main(String[] args) {
//		  System.out.println("Welcome to Hangman!  Good luck you have 10 tries");
//		  HangmanGame hangmanGame = new HangmanGame();
//		  hangmanGame.newGame();
//		  hangmanGame.play();
//		}

		}


