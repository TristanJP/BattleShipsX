package Game;

import java.util.ArrayList;

import Grid.Grid;
import JavaFX.GUI;
import Players.AI;
import UI.GameConsole;

public class Controller {

	//Singleton controller
	private static Controller cont = null;

	//fields
	private Game game;
	private GameConsole console;
	private boolean run, play;

	//Singleton constructor
	private Controller() {
		//game loop boolean
		run = true;
		play = false;
	}

	public static Controller getCont() {
		if (cont == null){
			cont = new Controller();
		}
		return cont;
	}

	public static void main(String[] args) {
		//Default args console
		String[] uiChoice = {"0"};
		if (args.length != 0){
			uiChoice[0] = args[0];
		}

		//Make controller instance
		getCont();

		//open interface
		switch(uiChoice[0]){
		case "0" : cont.console = new GameConsole(); break;
		case "1" : //GUI
		}
		if (uiChoice[0].equals("0")) {
			//Menu Loop
			while (cont.run)
			{
				int choice = cont.console.getMenuInput();
				switch(choice) {
				case(1):
					//play
					cont.play = true;
				//make game and console
				cont.setGame();
				break;
				case(2):
					//exit
					System.out.println("Goodbye.");
				cont.run = false;
				System.exit(0);
				break;
				}

				//Main game loop
				while (cont.play)
				{
					boolean playersTurn = true, aisTurn = true;

					//Players turn
					while (playersTurn) {
						int[] choice2 = cont.console.getGameInput();
						switch(choice2[0]) {
						case(1):
							//fire
							playersTurn = cont.game.playerFire(choice2[1],choice2[2]);
						if (cont.game.checkIfAllSunk(1)){
							playersTurn = false;
							aisTurn = false;
							cont.play = false;
						}
						break;
						case(2):
							//status
							System.out.println("Status: \n"+cont.game.getStatus());
						break;
						case(3):
							//restart
							System.out.println("Restarting Game\n");
						cont.setGame();
						break;
						case(4):
							//exit
							System.out.println("Goodbye.");
						System.exit(0);
						break;
						case(5):
							//skip
							System.out.println("Skipping turn\n");
						playersTurn = false;
						break;
						}
					}

					//AI's turn
					if (aisTurn){
						System.out.println(cont.game.ai.shoot());
						if (cont.game.checkIfAllSunk(0)){
							cont.play = false;
						}
					}
				}
			}
		}
		else if (uiChoice[0].equals("1")) {
			//GUI
		}
	}

	//make the game
	public void setGame() {
		game = new Game();
	}

	//JavaFX
	public int guiFire(int x, int y, Controller cont) {
		return cont.game.guiFireAt(x, y);
	}
	
	public ArrayList<Integer> guiFireAI(Controller cont) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		result = cont.game.ai.shoot();
		if (cont.game.checkIfAllSunk(0)){
			cont.play = false;
		}
		return result;
	}
}
