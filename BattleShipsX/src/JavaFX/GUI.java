package JavaFX;

import java.util.ArrayList;

import Game.Controller;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class GUI extends Application {

	private static final int TILE_SIZE = 20; //number of tiles
	private static final int W = 240; //the width of the grid
	private static final int H = 240; //the height of the grid

	private static final int X_TILES = 12; //number of tiles in horizontal direction
	private static final int Y_TILES = 12; //number of tiles in vertical direction

	private static Tile[][] grid = new Tile[X_TILES][Y_TILES]; //defines the tile as a 2D array
	private Scene scene;
	private static Controller cont;
	
	private Tile[][] gridAI = new Tile[X_TILES][Y_TILES]; //defines the tile as a 2D array

	/*
	 * cont.game.ai.shoot();
	if (cont.game.checkIfAllSunk(0)){
		cont.play = false;
	}
	 */

	private Parent createContent() {
		AnchorPane root = new AnchorPane(); //returning the root of the application
		Pane playerPane = new Pane();
		Pane AIPane = new Pane();
		root.setPrefSize(540, 300); //height and width of our grid

		for (int y = 11; y >= 0; y--) { //creates the tiles vertically. 
			for (int x = 0; x < X_TILES; x++) {
				Tile tile = new Tile(x, y, false);
				tile.setHandler(cont);

				grid[x][y] = tile; //assign tile to the grid
				playerPane.getChildren().add(tile); //tiles added to the root
			}
		}

		for (int y = 11; y >= 0; y--) { //creates the tiles vertically. 
			for (int x = 0; x < X_TILES; x++) {
				Tile tile = new Tile(x, y, true);
				tile.setHandler(cont);

				gridAI[x][y] = tile; //assign tile to the grid
				AIPane.getChildren().add(tile); //tiles added to the root
			}
		}
		
		root.getChildren().addAll(playerPane, AIPane);
		AnchorPane.setLeftAnchor(AIPane, 5.0);
		AnchorPane.setLeftAnchor(playerPane, 275.0);		
		System.out.println("made tiles");
		return root;
	}
	
	public void setCont(Controller cont) {
		this.cont = cont;
		System.out.println("setCont method");
	}	
	
	@Override
	public void start(Stage stage) throws Exception {
		scene = new Scene(createContent());
		stage.setScene(scene);
		stage.show();
		
		//Rectangle border.setFill(Color.RED);
		
		/*
		Tile tile = new Tile(3, 3);
		tile.AIHandler(cont.guiFireAI(cont));
		*/
		
		/*
        Stage stageAI = new Stage();
		sceneAI = new Scene(createContent(gridAI));
		stageAI.setScene(sceneAI);
        stageAI.show();
        */
	}
	
	protected static void AIGo(){
		ArrayList<Integer> result = new ArrayList<Integer>();
		result = cont.guiFireAI(cont);
		for(int i = 0; i < result.size(); i += 3){
			
			System.out.println(result.get(i) + ", " + result.get(i+1) + " - " + result.get(i+2));
			Tile tile = grid[result.get(i)][result.get(i+1)];
			tile.AIHandler(result.get(i+2));			
		}		
	}

	public static void main(String[] args) {
		cont = cont.getCont();
		cont.setGame();
		launch(args);
	}

	public void run(String[] args) {
		launch(args);
	}
}
