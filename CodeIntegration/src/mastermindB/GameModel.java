package mastermindB;

import java.util.Random;

public class GameModel {
	private int[] solutionArray = new int[4]; //an integer solution array
	private char[] colourCode = new char[4]; //a char solution array (colours)

	//1st constructor to create solution for VB
	public GameModel(){
		
		//Class to create random numbers
		Random randNum = new Random();
		
		//Create array of random numbers
		for (int i = 0; i < solutionArray.length ; i++){
			solutionArray[i] = (int)(randNum.nextInt(6));
		}
		//This is putting the random colours in the array using colourCode method
		colourCode[0] = getColourAt(0);
		colourCode[1] = getColourAt(1);
		colourCode[2] = getColourAt(2);
		colourCode[3] = getColourAt(3);
	}
	//2nd constructor for Normal
	public GameModel(String a){
		//Class to create random numbers
		Random randNum = new Random();

		//Create array of random numbers
		for (int i = 0; i < solutionArray.length ; i++){
			solutionArray[i] = (int)(randNum.nextInt(6));
		}
		//while loop for comparing values in loop i and j using random array
		while (noDuplicates(solutionArray) == false){
			for (int i = 0; i < solutionArray.length ; i++){
				for (int j = 0; j < solutionArray.length; j++){
					if(solutionArray[i] == solutionArray[j] && i != j) /*checks solution array i and solution array j while the two positions are not the same*/ {
						solutionArray[i] = (int)(randNum.nextInt(6));
					}
				}
			}
		}
		//This is putting the random colours in the array using getAt method
		colourCode[0] = getColourAt(0);
		colourCode[1] = getColourAt(1);
		colourCode[2] = getColourAt(2);
		colourCode[3] = getColourAt(3);
	}
	//Boolean method to check for solution array duplicates
	public boolean noDuplicates(int[] array){
		for (int i = 0; i < array.length ; i++){
			for (int j = 0; j < array.length; j++){
				if(array[i] == array[j] && i != j){
					return false; //return false if duplicates
				}
			}
		}
		return true;
	}
	//char array getSolution() 
	public char[] getSolution(){
		//returns solution
		return colourCode;
	}
	//Takes random number and returns a colour
	public char getColourAt(int i){
		char colour = 'x';
		if (solutionArray[i]==1){
			colour = 'r' ;
		}
		else if (solutionArray[i]==2){
			colour = 'o';
		}
		else if (solutionArray[i]==3){
			colour = 'y'; 
		}
		else if (solutionArray[i]==4){
			colour = 'g'; 
		}
		else if (solutionArray[i]==5){
			colour = 'b'; 
		}
		else if (solutionArray[i]==0){
			colour = 'p'; 
		}	
		return colour; //returns part of the colour
	}
}
