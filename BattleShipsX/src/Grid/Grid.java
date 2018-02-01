package Grid;

import java.util.Random;
import Ships.BattleShip;

public class Grid {
	
	int height = 12, length = 12;
	private Patch[][] grid;
	private boolean gridType;
	private int shipsSunk;
	
	/**
	 * Constructor for Grid object
	 */
	public Grid(boolean type)
	{
		this.grid = makeGrid(type);
		setGridType(type);
 
	}
	
	public int getShipsSunk()
	{
		return shipsSunk;
	}
	
	//checking the cell given
	public int checkAt(int x, int y) {
		int action = 0;
		Patch selectedPatch = grid[x][y];//Patch chosen according to parameters
		if(!selectedPatch.hasShot()) {
			if(selectedPatch.hasShip()) 
			{
				action = 1;
			}
			selectedPatch.setHasShot(true);
	    }
		else {			
			action = 2;
		}
		return action;
	}
	
	/**
	 * 
	 * Choose location to fire at
	 * @return  0 = miss, 1 = hit, 2 = already shot at
	 */
	public int fireAt(int x, int y) {
		int action = 0;
		Patch selectedPatch = grid[x][y];//Patch chosen according to parameters
		
		//check if chosen patch has not already been shot at 
		if(!selectedPatch.hasShot()) {
			if(selectedPatch.hasShip()) 
			{
				shipsSunk += selectedPatch.getShip().hit();//reduce life of ship
				action = 1;
			}
			selectedPatch.setHasShot(true);
	    }
		else {			
			action = 2;
		}
		return action;
	}
	
	public boolean getCellShot(int x, int y) {
		return grid[x][y].hasShot();
	}
	
	public boolean getCellSunk(int x,int y) {
		return grid[x][y].getShip().getIsSunk();
	}
	

	
	public Patch[][] getGrid(){
		
		return grid;
	}
	
	public Patch getGridPatch(int x, int y) {
		if ((x <= length - 1) && (y <= height - 1)) {
			return grid[x][y];
		} else {
			return null;
		}
	}
	
	
	/**
	 * makes a multi-dimentional arrray of either Home or Enemy Patches
	 * 
	 * @param type Whether the Grid is Enemy or Home
	 */
	private Patch[][] makeGrid(boolean type)
	{
		Patch[][] grid = new Patch[height][length];
		
		for (int i = 0; i < height; i++)
		{
			for (int j = 0; j < length; j++)
			{
				if (type == true)
					grid[i][j] = new Patch();
				else
				{
					grid[i][j] = new Patch();
				}
			}
		}
		return grid;
	}

	public boolean getGridType() {
		return gridType;
	}
	public void setGridType(boolean gridType) {
		this.gridType = gridType;
	}

}
