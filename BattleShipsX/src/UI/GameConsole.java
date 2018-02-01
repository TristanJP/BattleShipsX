package UI;

import java.util.Scanner;

import Players.AI;

public class GameConsole {
	
	private Scanner keyboard;
	
	public GameConsole(){
		/**
		 * Setup Scanner to be able to read in keyboard input
		 */
		keyboard = new Scanner(System.in);
	}
	
	/**
	 * Option Menu for user to select what to do
	 */
	public int[] getGameInput(){
		//store info on players choices in an array
		int[] choice = {0,0,0};
		
		//change choice based on chosen option
		String input = getChosenPlayInput();
		if (input.contains("fire") || input.contains("f")) {
			
			choice = fireChoice(choice, input);
			return choice;
		}
		else {
			switch(input){
			case "status" 	: //2
				choice[0] = 2;
				break;
			case "restart" 	: //3
				choice[0] = 3;
				break;
			case "r" 		: //3
				choice[0] = 3;
				break;
			case "exit" 	: //4
				choice[0] = 4;
				break;
			case "e":		  //4
				choice[0] = 4;
				break;
			case "s"		: //5
				choice[0] = 5;
				break;
			}
			return choice;
		}
	}

	public int getMenuInput(){
		int choice = 0;

		//change choice based on chosen option
		String input = getChosenMenuInput();
		switch(input){
		case "play" 	: //1
			choice = 1;
			break;
		case "exit" 	: //2
			choice = 2;
			break;
		case "p" 		: //1
			choice= 1;
			break;
		case "e" 	: 	  //2
			choice = 2;
			break;
		case "":		  //1
			choice = 1;
			break;
		}
		return choice;
	}
	
	private String getChosenMenuInput(){
		boolean run = true;
		String option = null;
		while (run) {
			/**
			 * Options Array; Easily expandable due to the for loop output of option below
			 */
			String[] options = {"play", "exit", "e", "p", ""};
			System.out.println("\nMenu:\n");
			for(int i = 0; i < options.length-3; i++)	System.out.println(" - " + options[i] + ".");
			/**
			 * Read in user input
			 */
			option = keyboard.nextLine();
			/**
			 * Quick check to see if entered option is valid
			 */
			boolean correctOpt = false;
			for(String opt : options) if(option.contains(opt)) correctOpt = true;
			if(correctOpt == false){
				System.out.println("\nInvalid Option.\n");
				/**
				 * Loop back up to top of function if invalid
				 */
			}
			else {
				//successfully got chosen input
				System.out.println("\nSelected Option: " + option);
				run = false;
			}
		}
		return option;
	}
	
	private String getChosenPlayInput() {
		boolean run = true;
		String option = null;
		while (run) {
			/**
			 * Options Array; Easily expandable due to the for loop output of option below
			 */
			String[] options = {"fire", "status", "restart", "exit", "s", "e", "f", "r"};
			System.out.println("What do you want to do?\n");
			for(int i = 0; i < options.length-4; i++)	System.out.println(" - " + options[i] + ".");
			/**
			 * Read in user input
			 */
			option = keyboard.nextLine();
			/**
			 * Quick check to see if entered option is valid
			 */
			boolean correctOpt = false;
			for(String opt : options) if(option.contains(opt)) correctOpt = true;
			if(correctOpt == false){
				System.out.println("\nInvalid Option.\n");
				/**
				 * Loop back up to top of function if invalid
				 */
			}
			else {
				//successfully got chosen input
				System.out.println("\nSelected Option: " + option);
				run = false;
			}
		}
		return option;
	}
	
	private int[] fireChoice(int[] choice, String input) {
		String[] split = input.split(" ");
		if (split[0].equals("fire") || split[0].equals("f")) {
			choice[0] = 1;
			choice[1] = Integer.parseInt(split[1]);
			choice[2] = Integer.parseInt(split[2]);
		}
		return choice;
	}
	
}
