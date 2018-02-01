package JavaFX;

import Game.Controller;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Tile extends StackPane {
	private final int x, y;
	private Rectangle border = new Rectangle(18, 18); //creates the grid
	private Controller cont;
	private Boolean canBeClicked;

	public Tile(int x, int y, Boolean canBeClicked) {
		this.x = x;
		this.y = y;
		this.canBeClicked = canBeClicked;
		
		border.setFill(Color.GREY);
		border.setStroke(Color.LIGHTGRAY); //defines a basic set of rendering attributes for the outlines of graphics primitive

		getChildren().addAll(border);

		setTranslateX(x * 20); //position the tiles
		setTranslateY(y * 20);
	}
	
	public void setHandler (Controller cont) {
		this.cont = cont;
		if(canBeClicked){
			setOnMouseClicked(handler -> {
				int yV = getY();
				int xV = getX();
				int result = 2;
				result = cont.guiFire(xV, yV, cont);
				
				if (result == 0) {
					border.setFill(Color.BLACK);
					GUI.AIGo();
				}
				else if (result == 1) {
					border.setFill(Color.RED);
				}
				System.out.println("loc: " + getX() + ", " + getY() + " Result: " + result);
			});
		}
	}
	
	public void AIHandler (int result) {
		if (result == 0) {
			border.setFill(Color.BLACK);
		}
		else if (result == 1) {
			border.setFill(Color.RED);
		}
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
}
