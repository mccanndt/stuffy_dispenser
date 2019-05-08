package ui;

import business.Stuffy;
import util.Console;

public class StuffyDispenserApp {

	public static void main(String[] args) {
		System.out.println("Welcome to the Stuffy Dispenser App\n");
		
		System.out.println("This app will mimic the functionality of a stuffy dispensing machine.\n"
				+ "The user will be able to select a Stuffy and hopefully the app will return one!\n");
		
		// Initialize a list of stuffies
		Stuffy[] stuffies = new Stuffy[10];
		stuffies[0] = new Stuffy(1, "Dog", "Blue", "Large", 4);
		stuffies[1] = new Stuffy(2, "Cat", "Green", "Small", 4);
		stuffies[2] = new Stuffy(3, "Dragon", "Purple", "Medium", 6);
		stuffies[3] = new Stuffy(4, "Snail", "Yellow", "X-Large", 1);
		stuffies[4] = new Stuffy(5, "Platypus", "Blue", "Medium", 4);
		stuffies[5] = new Stuffy(6, "Octopus", "Purple", "Large", 8);
		stuffies[6] = new Stuffy(7, "Squirrel", "Brown", "Small", 4);
		stuffies[7] = new Stuffy(8, "Starfish", "Pink", "X-Large", 5);
		stuffies[8] = new Stuffy(9, "Lobster", "Red", "Large", 12);
		stuffies[9] = new Stuffy(10, "Spider", "Clear", "Small", 8);
		
		// Print the list of stuffies so user knows
		for (int i = 0; i < stuffies.length; i++) {
			System.out.println("Stuffy " + stuffies[i].getId() + ": " + stuffies[i].getType());
		}
		
		// Loop until user wants to quit
		String choice = "y";
		while (choice.equalsIgnoreCase("y")) {
			// Inside loop
			// 1. Prompt for user input ("Pick a stuffy")
			int selectionNbr = Console.getInt("\nPick a stuffy by number (1-10): ", 0, 11);
			
			// 2. Do business logic (retrieve a stuffy from the list)
			Stuffy selectedStuffy = stuffies[selectionNbr - 1];
			
			// 3. Display the selected stuffy
			System.out.println("\nCongratulations, you got a " + selectedStuffy.getSize() + ", "
					+ selectedStuffy.getColor() + " " + selectedStuffy.getType() + " stuffy!!!");
			
			// 4. Prompt to continue
			choice = Console.getString("\nContinue? (y/n): ", "y", "n");
		}
		
		
		System.out.println("Bye!");
	}

}
