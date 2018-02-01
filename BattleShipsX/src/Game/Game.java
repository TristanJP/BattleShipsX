package Game;

import java.util.Random;

import Grid.Grid;
import Players.AI;
import Ships.BattleShip;

public class Game {

	//fields
	Grid aiGrid;
	Grid playerGrid;
	AI ai;

	//Below will hold the array of battleShips for the PLAYER
	BattleShip[] playerBattleShipsList;

	//Below will hold the array of battleShips for the AI
	BattleShip[] aiBattleShipsList;

	public Game() {

		playerBattleShipsList = new BattleShip[5];
		aiBattleShipsList = new BattleShip[5];

		//creates and fills the Player and AI battleships
		makeShips();

		//make grids and set them
		setAiGrid();
		setPlayerGrid();


		//create AI object
		setAi();

		//show ship locations for testing
		showShips(aiGrid, playerGrid);
	}

	//fires at the AI's gird with the specified coordinates. returns false true unless you have already fired there
	public boolean playerFire(int x, int y)
	{
		int result = aiGrid.fireAt(x, y);
		if (result == 0) {
			//miss
			System.out.println("\nMISS\n");
			return false;
		}
		else {
			if (result == 1) {
				//hit
				System.out.println("\nHIT\n");
			}
			else {
				//Already fired there
				System.out.println("Already fired here.");
			}
			return true;
		}
	}
	
	public int guiFireAt(int x, int y) {
		return aiGrid.fireAt(x, y);
	}

	//set the player grid 
	//Two 
	public void setPlayerGrid(){
		playerGrid = new Grid(true);
		setShips(playerGrid,playerBattleShipsList,0);//2
	}

	//sets the ai grid
	//One
	public void setAiGrid() {
		aiGrid = new Grid(false);
		setShips(aiGrid,aiBattleShipsList,0);//1
	}

	//sets the ai
	public void setAi() {
		ai = new AI(playerGrid, aiGrid);
	}

	private void showShips(Grid aiGrid, Grid playerGrid){
		//states where Enemy ships are
		int count = 1;
		for(int i = 0; i < 12; i++){
			for(int a = 0; a < 12; a++){
				boolean hasShip = aiGrid.getGrid()[i][a].hasShip();
				if(hasShip == true){
					System.out.println(count + ": " + i + ", " + a + " Has Enemy " + aiGrid.getGrid()[i][a].getShip().getName());
					count += 1;
					//System.out.print("|X");
				} //else System.out.print("| ");
			}
			//System.out.println("|");
		}
		
		System.out.println();

		//states where Player ships are
		int countP = 1;
		for(int i = 0; i < 12; i++){
			for(int a = 0; a < 12; a++){
				boolean hasShip = playerGrid.getGrid()[i][a].hasShip();
				if(hasShip == true){
					System.out.println(countP + ": " + i + ", " + a + " Has Player " + playerGrid.getGrid()[i][a].getShip().getName());
					countP += 1;
					//System.out.print("|X");
				} //else System.out.print("| ");
			}
			//System.out.println("|");
		}
		//states where Enemy ships are
		for(int i = 0; i < 12; i++){
			for(int a = 0; a < 12; a++){
				boolean hasShip = aiGrid.getGrid()[i][a].hasShip();
				if(hasShip == true)System.out.print("|X");
				else System.out.print("| ");
			}
			System.out.println("|");
		}
		
		System.out.println();

		//states where Player ships are
		for(int i = 0; i < 12; i++){
			for(int a = 0; a < 12; a++){
				boolean hasShip = playerGrid.getGrid()[i][a].hasShip();
				if(hasShip == true)System.out.print("|X");
				else System.out.print("| ");
			}
			System.out.println("|");
		}
	}

	//This method will create the array's of the PLAYER AND AI battleshipss
	public void makeShips()
	{
		//The below is firstly to create the five ships 
		int[] shipSizes= {2,3,3,4,5};

		//### Creating battleship to be put in the playerBattleShipsList
		for (int x = 0; x < shipSizes.length; x ++) 
		{
			//This creates a new battleship of size X from index's of shipSizes
			BattleShip newPlayerBattleShip = new BattleShip(shipSizes[x]);

			//This add the new battleship of size x (above) to a part in the array
			playerBattleShipsList[x] = newPlayerBattleShip;
		}

		//### Creating battleship to be put in the aiBattleShipsList

		for (int y = 0; y < shipSizes.length; y ++) 
		{
			//This creates a new battleship of size X from index's of shipSizes
			BattleShip newAIBattleShip = new BattleShip(shipSizes[y]);

			//This add the new battleship of size x (above) to a part in the array
			aiBattleShipsList[y] = newAIBattleShip;
		}

	}

	public boolean checkIfAllSunk(int PlayerOrAI)
	{	
		//PlayerOrAI is 1 for player, 0 for AI

		//IMPORTANT  THE FOR IS SET TO LOOP TO 2 FOR TESTIGN PURPOSES

		// PROBLEM: The patches don't reset so when you play again, it will say 'Already fired here'
		//	PROBLEM: need to work out how to EITHER create a fresh new game from create a fresh controller OR need to learn how to reset the patch is sunk/has ship/ health in the positive.


		int numberOfAIorPlayerShipsSunk = 0;
		//System.out.println("got to place ONE");
		

		if (PlayerOrAI == 1)
		{
			for(int x = 0; x < 2; x++) //aiBattleShipsList.length
			{
				if(aiBattleShipsList[x].getIsSunk())
				{
					numberOfAIorPlayerShipsSunk++;
					//System.out.println("2");
				}
			}

			if(numberOfAIorPlayerShipsSunk == 2) 
			{
				System.out.println("The Player has won!");
				return true;
			}
			else
			{
				return false;
			}

		}
		else if (PlayerOrAI == 0)
		{
			for(int x = 0; x < playerBattleShipsList.length; x++)
			{	
				if(playerBattleShipsList[x].getIsSunk()){
					numberOfAIorPlayerShipsSunk++;
				}
			}

			if(numberOfAIorPlayerShipsSunk == 5) 
			{
				System.out.println("The AI has won, unlucky");
				return true;
			}
			else
			{
				return false;
			}
		}
		else
		{
			return false;
		}
	}

	public String getStatus(){
		String status = "";
		String playersShip = "Player Ships : \n";
		String enemyShip = "Enemy Ships: \n";

		int shipsSunk = 0;
		int safeShips = 0;

		status = status + "Player: \n";

		for(BattleShip ship : playerBattleShipsList){
			playersShip = playersShip + ship.getSize()+ " health: "+ (ship.getHealth()+ "\n");

			if(ship.getIsSunk()){
				shipsSunk++;
			}
			if(ship.getHealth() == ship.getSize()){
				safeShips++;
			}
		}
		status = status + "Safe Ships: " + safeShips + "\n";
		status = status + "Destroyed Ships: " + shipsSunk + "\n";

		int shipsSunkEnemy = 0;
		int safeShipsEnemy = 0;

		status = status + "Enemy: \n";

		for(BattleShip ship : aiBattleShipsList){
			enemyShip = enemyShip + ship.getSize()+ " health: "+ (ship.getHealth() + "\n");

			if(ship.getIsSunk()){
				shipsSunkEnemy++;
			}
			if(ship.getHealth() == ship.getSize()){
				safeShipsEnemy++;
			}
		}

		status = status + "Safe Ships: " + safeShipsEnemy + "\n";
		status = status + "Destroyed Ships: " + shipsSunkEnemy + "\n";

		return "\n" + playersShip + "\n" + enemyShip + "\n" + status;
	}


	private void setShips(Grid grid, BattleShip[] ships, int test)
	{
		// set up sizes of the ships to be made - length of array determines amount of ships to be made
		//int[] shipSizes = {2, 3, 3, 4, 5};
		BattleShip[] battleShips = ships;//use given array of ships
		Random r = new Random();
		// Loop over ships
		for(int i = 0; i < battleShips.length; i++){
			// initiate a ship
			BattleShip ship = battleShips[i];			
			// get orientation of ship
			int rOrient = r.nextInt(2);	// 0 == Y	1 == X
			// boolean for the loop to use - Can Place?
			boolean canPlace = false;
			int randX; 
			int randY;
			if(rOrient == 0){ // y Axis
				while(canPlace == false){
					// get random locations
					randX = r.nextInt(11);
					randY = r.nextInt(11);
					int tempRandX = randX;
					int tempRandY = randY;
					
					// loop over each segment of the ship (its size)
					for(int a = 0; a < battleShips[i].getSize(); a++){
						// if the patch has a ship already on it, the new ship in its entirety cannot be placed there
						if(grid.getGridPatch(tempRandX,tempRandY) != null){
							if(grid.getGridPatch(tempRandX,tempRandY).hasShip()){
								canPlace = false;
								break;
							} else canPlace = true;
						}
						tempRandY += 1;
						if(tempRandY > 11) canPlace = false;
					}
					// if the check above passed, proceed with placing the ship
					if(canPlace == true){
						// loop over each segment of the ship (its size)
						for(int a = 0; a < battleShips[i].getSize(); a++){
							// set the ship
							grid.getGridPatch(randX,randY).setShip(ship);
							randY += 1;
						}
					}

				}

			} else { // Exact same logic as above but on X axis		

				while(canPlace == false){
					randX = r.nextInt(11);
					randY = r.nextInt(11);
					int tempRandX = randX;
					int tempRandY = randY;
					
					for(int a = 0; a < battleShips[i].getSize(); a++){
						if(grid.getGridPatch(tempRandX,tempRandY) != null){
							if(grid.getGridPatch(tempRandX,tempRandY).hasShip()){
								canPlace = false;
								break;
							} else canPlace = true;
						}
						tempRandX += 1;
						if(tempRandX > 11) canPlace = false;
					}
					if(canPlace == true){
						for(int a = 0; a < battleShips[i].getSize(); a++){
							grid.getGridPatch(randX,randY).setShip(ship);
							randX += 1;
						}
					}
				}
			}
		}
	}
}
