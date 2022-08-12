package mastermindB;

import java.util.Scanner;

public class MasterMindStart {

	
	public static void main(String[] args) {
		/*Output statement containing welcome message*/
		System.out.println("***********************");
		System.out.println("Welcome to Mastermind!");
		System.out.println("***********************");
		System.out.println();

		while (true) { /*while is set true in order to output the content below*/
			System.out.println("Would you like to play? (Yes/No)");
			java.util.Scanner input = new java.util.Scanner(System.in); //Set scanner to check for user input off keyboard
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
				break; //end
			}

		}
	}
}
