package mastermindB;

import java.util.Arrays;
import java.util.Scanner;

public class GameController {
	//run constructor
	public GameController(GameModel model, String choice) {

		//set base guess number
		int guessNumber = 1;

		//retrieve string representation of the Solution, [ , , , ]
		String actualstringsolution = Arrays.toString(model.getSolution());

		//if user selects N from MasterMind class
		if (choice.equalsIgnoreCase("N")) {


			System.out.println(actualstringsolution);
			//check keyboard input
			java.util.Scanner input = new java.util.Scanner(System.in);
			//stopLaying is false so game continues
			boolean stopPlaying = false;
			//set a
			int a = 1;
			while (a != 0) {
				//limit of guesses & output if the condition is met
				if (guessNumber > 12) {
					System.out.println("***********************");
					System.out.println("Game over. You lose.");
					System.out.println("The correct code was: " +" Colour Code: " + actualstringsolution + " Number of turns: " + guessNumber); // reveals the answer, etc
					System.out.println("***********************\n");
					break;
				}
				//Input guess
				System.out.println("\nEnter guess" + guessNumber + ": ");
				String guess = input.nextLine();//input 
				if (guess.length() == 4) { //if guess length of user input = 4

					//checks if guess contains exceptions/duplicates
					if (!(guess.contains("a") || check(guess) || guess.contains("c") || guess.contains("d")
							|| guess.contains("e") || guess.contains("f") || guess.contains("h") || guess.contains("i")
							|| guess.contains("j") || guess.contains("k") || guess.contains("l") || guess.contains("m")
							|| guess.contains("n") || guess.contains("q") || guess.contains("s") || guess.contains("t")
							|| guess.contains("u") || guess.contains("w") || guess.contains("x")
							|| guess.contains("z"))) {

						stopPlaying = isCorrect(guess, model.getSolution()); // stop playing when you guess correctly
						int rightColorRightPlace = getRightColorRightPlace(guess, model.getSolution()); //call method within variable
						int rightColorWrongPlace = getRightColorWrongPlace(guess, model.getSolution());
						int correctedRightColorWrongPlace = rightColorWrongPlace - rightColorRightPlace; //this will sort the order of the outputs for + and -

						//the variable contains the guess & solution as shown above, so for each guess that is correct after each loop, a + will output
						for (int i = 0; i < rightColorRightPlace; i++) { 
							System.out.print("+");
						}
						//the variable contains the guess & solution as shown above, so for each guess that is correct after each loop, a - will output
						for (int i = 0; i < correctedRightColorWrongPlace; i++) {
							System.out.print("-");
						}
						//if there are 4 +
						if (rightColorRightPlace == 4) {
							stopPlaying = true;
							System.out.println();
							break;

						}
						//guessNumber is incremented after each guess
						guessNumber++;
					} else {
						System.out.println("You've included an invalid color. Try that again."); //output if invalid input is detected
					}
				} else if (guess.length() != 4) { //if the length of the guess/input is not equal to 4
					System.out.println("Your guess must have 4 colors.");
				}
			}
			if (stopPlaying == true) {
				System.out.println("***********************");
				System.out.println("You win! " + " Colour Code: " + actualstringsolution + " Number of Guesses: " + guessNumber); //user wins, correct code is displayed, and number of guess
				System.out.println("***********************\n");
			}

			/*////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////*/

		} else if (choice.equalsIgnoreCase("VB")) {

			System.out.println(actualstringsolution);
			//check keyboard input
			java.util.Scanner input = new java.util.Scanner(System.in);
			//stopLaying is false so game continues
			boolean stopPlaying = false;
			int a = 1;
			while (a != 0) {
				if (guessNumber > 12) {
					System.out.println("***********************");
					System.out.println("Game over. You lose.");
					System.out.println("The correct code was: " +" Colour Code: " + actualstringsolution + " Number of turns: " + guessNumber); // reveals the answer
					System.out.println("***********************\n");
					break;
				}
				System.out.println("\nEnter guess number " + guessNumber + ": ");
				String guess = input.nextLine(); //input
				if (guess.length() == 4) { //length of user input guess if 4

					//checks for exceptions
					if (!(guess.contains("a") || guess.contains("c") || guess.contains("d") || guess.contains("e")
							|| guess.contains("f") || guess.contains("h") || guess.contains("i") || guess.contains("j")
							|| guess.contains("k") || guess.contains("l") || guess.contains("m") || guess.contains("n")
							|| guess.contains("q") || guess.contains("s") || guess.contains("t") || guess.contains("u")
							|| guess.contains("w") || guess.contains("x") || guess.contains("z"))) {

						stopPlaying = isCorrect(guess, model.getSolution()); // stop playing when you guess correctly
						int rightColorRightPlace = getRightColorRightPlace(guess, model.getSolution()); //call method within variable
						int rightColorWrongPlace = getRightColorWrongPlace(guess, model.getSolution()); 
						int correctedRightColorWrongPlace = rightColorWrongPlace - rightColorRightPlace; //this will sort the order of the outputs for + and -

						//the variable contains the guess & solution as shown above, so for each guess that is correct after each loop, a + will output
						for (int i = 0; i < rightColorRightPlace; i++) { 
							System.out.print("+");
						}
						//the variable contains the guess & solution as shown above, so for each guess that is correct after each loop, a - will output
						for (int i = 0; i < correctedRightColorWrongPlace; i++) {
							System.out.print("-");
						}
						//if there are 4 +
						if (rightColorRightPlace == 4) {
							stopPlaying = true; //stop playing is switched to true
							break;

						}
						//guessNumber is incremented after each guess
						guessNumber++;

					} else {
						System.out.println("You've included an invalid color. Try that again."); //output if invalid input is detected
					}
				} if (guess.length() != 4) { //if the length of the guess/input is not equal to 4
					System.out.println("Your guess must have 4 colors."); 
				}
			}
			if (stopPlaying == true) { //if the stopPlaying condition is true
				System.out.println("***********************"); 
				System.out.println("You win! " + " Colour Code: " + actualstringsolution + " Number of Guesses: " + guessNumber); //user wins, correct code is displayed, and number of guess
				System.out.println("***********************\n");
			}
		} 
	}
	//boolean check method to check for duplicates
	public static boolean check(CharSequence g) {
		for (int i = 0; i < g.length(); i++) {
			for (int j = i + 1; j < g.length(); j++) {
				if (g.charAt(i) == g.charAt(j)) {
					return true;
				}
			}
		}
		return false;
	}
	//boolean isCorrect method to check if arrays are equal
	public boolean isCorrect(String guess, char[] solution) {
		boolean guessIsCorrect = false; //set false
		char[] guessArray = guess.toCharArray(); //convert string to array
		if (Arrays.equals(guessArray, solution)) {//checks if arrays are equal to one another
			guessIsCorrect = true;
		}
		return guessIsCorrect;
	}

	//to check if colours are correct and in right place
	public int getRightColorRightPlace(String guess, char[] solution) {
		int rightColorRightPlace = 0;
		char[] guessArray = guess.toCharArray(); //convert string to array
		for (int i = 0; i < guessArray.length; i++) { //loop through the guess

			if (guessArray[i] == solution[i]) {//if the array members are the same
				rightColorRightPlace++; //increment
			}
		}
		return rightColorRightPlace;
	}

	//code to get rightColourWrong place "-"
	public int getRightColorWrongPlace(String guess, char[] solution) {
		char[] guessArray = guess.toCharArray();
		int totalMalpositioned = 0;
		int[] guessArrayCounts = new int[6];
		int[] solutionArrayCounts = new int[6];
		char guessValue; 
		char solutionValue; 

		for (int i = 0; i < 4; i++) { 
			guessValue = guessArray[i];
			solutionValue = solution[i];
			int guessColorAsInt = colourChartoInt(guessValue);
			int solutionColourAsInt = colourChartoInt(solutionValue);
			guessArrayCounts[guessColorAsInt - 1] += 1; 
			solutionArrayCounts[solutionColourAsInt - 1] += 1;
		}
		for (int j = 0; j < solutionArrayCounts.length; j++) {
			totalMalpositioned += Math.min(guessArrayCounts[j], solutionArrayCounts[j]); 
		}

		return totalMalpositioned;
	}
	//convert colour to int
	private int colourChartoInt(char colourChar) {
		int colourInt = 0; // 0 unassigned to any colour.
		if (colourChar == 'r') {
			colourInt = 1;
		} else if (colourChar == 'o') {
			colourInt = 2;
		} else if (colourChar == 'y') {
			colourInt = 3;
		} else if (colourChar == 'g') {
			colourInt = 4;
		} else if (colourChar == 'b') {
			colourInt = 5;
		} else if (colourChar == 'p') {
			colourInt = 6;
		}
		return colourInt;
	}

}