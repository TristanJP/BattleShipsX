package Players;

import Grid.Grid;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Random;

public class AI {
	
	private Grid playerGrid;
	private Random rand;
	private ArrayList<CoordinateObject> targets;
	private boolean AIturn;
	
	//constructor
	public AI(Grid playerGrid, Grid enemyGrid)
	{
		this.playerGrid = playerGrid;
		rand = new Random();
		targets = new ArrayList<CoordinateObject>();
		AIturn = false;
	}
	
	//fire at grid
	public ArrayList<Integer> shoot()
	{
		ArrayList<Integer> result = new ArrayList<Integer>();
		
		String output = "AI's turn: ";
		AIturn = true;
		while (AIturn)
		{
			if (hasTarget())
			{
				result.addAll(targetedShoot());
			}
			else
			{
				result.addAll(randomShoot());
			}
		}
		output += "\nTarget List: ";
		for (CoordinateObject c : targets){
			output += "(" + c.getX() + ", " + c.getY() +") ";
		}
		output += "\n";
		System.out.println(output);
		return result;
	}
	
	//picks the first of the known targets and fires near it
	private ArrayList<Integer> targetedShoot()
	{
		ArrayList<Integer> resultIntArr = new ArrayList<Integer>();
		boolean shooting = true;
		String output = "\nTargeted Shot";
		while (shooting)
		{
			
			//grabs the value of the first target location in the queue
			int targetX = targets.get(0).getX();
			int targetY = targets.get(0).getY();
			
			int result = playerGrid.fireAt(targetX, targetY);

			resultIntArr.add(targetX);
			resultIntArr.add(targetY);
			resultIntArr.add(result);
			
			if (result != 2){
				output += "\nShip fired at (" + targetX + ", " + targetY + "), Result: " + result;
			}
			
			if (result == 1){
				if (playerGrid.getCellSunk(targetX, targetY)){
					shooting = false;
				} else {
					shooting = true;
				}
				output += " HIT";
				addValidTargets(targetX, targetY);
				
			}
			else if (result == 0) {
				shooting = false;
				output += " MISS";
				AIturn = false;
			} else {
				//output += " - Already fired here";
			}
			if (!hasTarget()) {
				shooting = false;
			}
		}
		System.out.println(output);
		return resultIntArr;
	}
	
	/* Checks the list of successful hits for targets.
	 * Does this by first clearing the list of sunk ships,
	 * then it checks again if the list contains any unsunk ships.
	*/
	private boolean hasTarget()
	{
		ArrayList<CoordinateObject> valid = new ArrayList<CoordinateObject>();
		for (CoordinateObject c : targets)
		{
			if (!playerGrid.getCellShot(c.getX(), c.getY()))
			{
				valid.add(c);
			}
		}
		targets = valid;
		if (targets.size() > 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	//fire randomly at grid
	private ArrayList<Integer> randomShoot()
	{
		ArrayList<Integer> resultIntArr = new ArrayList<Integer>();
		
		boolean shooting = true;
		String output = "\nRandom Shot";
		while (shooting)
		{
			int x = rand.nextInt(12);
			int y = rand.nextInt(12);
			
			int result = playerGrid.fireAt(x, y);

			resultIntArr.add(x);
			resultIntArr.add(y);
			resultIntArr.add(result);
			
			if (result != 2)
			{
				output += "\nShip fired at (" + x + ", " + y + "), Result: " + result;
			}
			
			if (result == 1)
			{
				shooting = false;
				output += " HIT";
				addValidTargets(x, y);
				
			}
			else if (result == 0)
			{
				shooting = false;
				output += " MISS";
				AIturn = false;
			}
			else
			{
				//output += " - Already fired here";
			}
		}
		System.out.println(output);
		return resultIntArr;
	}

	private void addValidTargets(int x, int y) {
		
		boolean remove;
		int count = 0;
		ArrayList<CoordinateObject> valid = new ArrayList<CoordinateObject>();
		
		//add all directions to a list of possible targets
		CoordinateObject[] possibleTargets = {
		new CoordinateObject(x+1,y),
		new CoordinateObject(x,y+1),
		new CoordinateObject(x-1,y),
		new CoordinateObject(x,y-1)};
		
		//remove all non valid targets
		for (CoordinateObject cord : possibleTargets)
		{
			remove = false;
			//check that it is on the grid
			if ((cord.getX() > 11) || (cord.getX() < 0) || (cord.getY() > 11) || (cord.getY() < 0))
			{
				remove = true;
			}
			else if (playerGrid.getCellShot(cord.getX(), cord.getY())) { //check that the location hasn't already been fired at
				remove = true;
			}
			
			//adds cord to a list to be added if it passes the checks
			if (!remove)
			{
				valid.add(possibleTargets[count]);
			}
			count++;
		}
		
		//update the target list with only those that are valid
		for (CoordinateObject vCord : valid)
		{
			targets.add(vCord);
		}
		
	}
	
}