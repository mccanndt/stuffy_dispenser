package ui;

import java.util.List;

import business.Stuffy;
import db.DAO;
import db.StuffyDB;
import util.Console;
import util.StringUtils;

public class StuffyDispenserApp {

	private static DAO<Stuffy> stuffyDAO = new StuffyDB();

	public static void main(String[] args) {
		System.out.println("Welcome to the Stuffy Dispenser App\n");

		System.out.println("This app will mimic the functionality of a stuffy dispensing machine.\n"
				+ "The user will be able to select a Stuffy and hopefully the app will return one!\n");

		// Display menu
		displayMenu();

		String action = "";
		while (!action.equalsIgnoreCase("exit")) {
			// Get input from user
			action = Console.getString("Enter a command: ");
			System.out.println();

			if (action.equalsIgnoreCase("list")) {
				displayAllProducts();
			} else if (action.equalsIgnoreCase("add")) {
				addProduct();
			} else if (action.equalsIgnoreCase("del")) {
				deleteProduct();
			} else if (action.equalsIgnoreCase("help")) {
				displayMenu();
			} else if (!action.equalsIgnoreCase("exit")) {
				System.out.println("Error! Not a valid command.\n");
			}
		}

		System.out.println("Bye!");

	}

	private static void displayMenu() {
		System.out.println("COMMAND MENU");
		System.out.println("list    - List all stuffies");
		System.out.println("add     - Add a stuffy");
		System.out.println("del     - Delete a stuffy");
		System.out.println("help    - Show this menu");
		System.out.println("exit    - Exit this application\n");
	}

	public static void displayAllProducts() {
		System.out.println("STUFFY LIST\n");

		List<Stuffy> stuffies = stuffyDAO.getAll();
		StringBuilder sb = new StringBuilder();
		for (Stuffy s : stuffies) {
			sb.append(StringUtils.padWithSpaces(Integer.toString(s.getId()), 3));
			sb.append(StringUtils.padWithSpaces(s.getType(), 20));
			sb.append(StringUtils.padWithSpaces(s.getColor(), 10));
			sb.append(StringUtils.padWithSpaces(s.getSize(), 10));
			sb.append(s.getLimbs());
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}

	public static void addProduct() {
		String type = Console.getString("Enter stuffy type: ");
		String color = Console.getString("Enter stuffy color: ");
		String size = Console.getString("Enter stuffy size: ");
		int limbs = Console.getInt("Enter number of limbs: ");

		Stuffy s = new Stuffy(type, color, size, limbs);
		if (stuffyDAO.add(s)) {
			System.out.println("Product " + s.getColor() + " " + s.getType() + " successfully added.");
		} else {
			System.out.println("Error adding product.");
		}

	}

	public static void deleteProduct() {
		int id = Console.getInt("Enter stuffy ID to delete: ", 0, Integer.MAX_VALUE);

		Stuffy s = stuffyDAO.get(id);
		if (s != null) {
			if (stuffyDAO.delete(s)) {
				System.out.println(s.getColor() + " " + s.getType() + " has been deleted.");
			} else {
				System.out.println("Error deleting stuffy.");
			}
		} else {
			System.out.println("No stuffy matches that code.\n");
		}
	}

}
