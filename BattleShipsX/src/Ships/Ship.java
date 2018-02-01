package Ships;

//This ship class will hold methods that all subclasses will use or need t  implement their own custom version if necessary
public abstract class Ship 
{
	//The following are all generic attributes that all concrete classes of this ship abstract class will need to implement
	int healthbar;
	int size;
	boolean isSunk;
	
	public Ship(int size){
		this.size = size;
		healthbar = size;
	}
	
	//The below is the generic method for a 'hit' that all the subclasses  must implement
	//As we can see it decreases the health bar, and if the health bar is equal to 0, it's sunk. 
	public int hit()
	{
		healthbar -= 1;
		
		if (healthbar == 0)
		{
			isSunk = true;
			System.out.println("Battleship size " + size + " has been sunk");
			return 1;
		}
		return 0;
	}
	
	//The below is the generic method for getting the size of a ship that all the subclasses must implement
	//It simply returns the size
	public int getSize()
	{
		return size;
	}
	
	
	//The below is the generic method for getting the health of a ship that all the subclasses  must implement
	//It simply returns the health of said ship
	public int getHealth()
	{
		return healthbar;
	}
	
	//The below is the generic method for checking whether or not a ship has been sunk - boolean: a method that all the subclasses  must implement
	public boolean getIsSunk()
	{
		return isSunk;
	}
	
	public String getName() {
		return "BattleShip"+size;
	}
	

}
