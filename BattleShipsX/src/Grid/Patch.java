package Grid;

import Ships.BattleShip;

public class Patch {
	
	//Fields
	private boolean hasShip, hasShot;
	private BattleShip ship;
	
	/**Constructor for Patch object
	 * Sets booleans to false and ship to null
	 */
	public Patch()
	{
		hasShip = false; // Whether there is a ship in this patch/cell
		hasShot = false; // Whether a shot has been fired at this patch/cell
		ship = null; // The BattleShip that is contained in this patch/cell
	}

	//Getter methods
	/**
	 * Gets the ship the ship which currently occupies the cell,
	 * returns null if there is no ship in the cell
	 * 
	 * @return ship The ship which the cell is linked too
	 */
	public BattleShip getShip()
	{
		return ship;
	}
	
	/**
	 * Gets the boolean for whether a ship is in this location
	 * 
	 * @return hasShip True if ship at this location
	 */
	public Boolean hasShip()
	{
		return hasShip;
	}
	
	/**
	 * Gets the boolean for whether this cell has been shot at already
	 * 
	 * @return hasShot True is the cell has been fired at already
	 */
	public Boolean hasShot()
	{
		return hasShot;
	}
	
	//Setter methods
	/**
	 * Sets the ship field to contain the BattleShip that is placed there as well as setting the hasShip boolean to true
	 * 
	 * @param ship The BattleShip that will occupy this cell
	 */
	public void setShip(BattleShip ship)
	{
		this.ship = ship;
		setHasShip(true);
	}
	
	/**
	 * Sets whether this cell has a BattleShip in it
	 * 
	 * @param hasShip True if a ship will occupy this cell
	 */
	private void setHasShip(Boolean hasShip)
	{
		this.hasShip = hasShip;
	}
	
	/**
	 * Sets whether this cell has been shot at
	 * 
	 * @param hasShot True if this cell has been shot at
	 */
	public void setHasShot(Boolean hasShot)
	{
		this.hasShot = hasShot;
	}
}
