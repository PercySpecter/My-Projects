

import javafx.application.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.image.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.stage.*;



public class Main extends Application
{
	
	static Stage window;
	static Scene scene;
	static Button button[] = new Button[9];
	static Game game;
	static BorderPane border;
	static Text Player1 , Player2;

	
	/**
	 * to print the move number for each cell as a guide
	 */
	/*public static void printGuide() 
	{
		System.out.println("Input cell number as follows during your turn to make a move:");
		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j < 3; j++)
			{
				if(j == 2)
					System.out.println(" " + (3 * i + j + 1));
				else
					System.out.print(" " + (3 * i + j + 1) + " |");
			}
			if(i != 2)
				System.out.println("-----------");
			else
				System.out.println();
		}
	}*/
	
	/**
	 * The game starts here
	 */
	/*public static void playGame()
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("TIC - TAC - TOE\n~~~~~~~~~~~~~~~~~~~");
		printGuide();
		boolean play = true;
		while(play)
		{
			System.out.println();
			game = new Game();
			//game.playGame();
			
			play = false;
			System.out.println("\nPlay again? (Y / N)");
			char c = sc.next().charAt(0);
			if(c == 'y' || c == 'Y')
				play = true;
		}
		
		System.out.println("Thank You for playing!!");
		
		sc.close();
	}*/
	
	public static void main(String[] args) 
	{
		game = new Game();
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception 
	{
		window = primaryStage;
		window.setTitle("Tic-Tac-Toe");
		
		Player1 = new Text("Player 1's turn");
		Player2 = new Text("Player 2's turn");
		Player1.setFont(Font.font("Gothic" , FontWeight.BOLD , 24));
		Player2.setFont(Font.font("Gothic" , FontWeight.BOLD , 24));
		
		initialiseScene();
	}
	
	
	
	private static void initialiseScene()
	{
		border = new BorderPane();
		border.setTop(Player1);
		BorderPane.setAlignment(Player1 , Pos.CENTER);
		
		for(int i = 0; i < 9; i++)
		{
			final int move = i + 1;
			button[i] = new Button();
			//Image iconImage = new Image("./icon/blank.png");
			Image iconImage = new Image("/icons/blank.png");
			ImageView icon = new ImageView(iconImage);
			button[i].setGraphic(icon);
			button[i].setOnAction(e -> onMove(move));
		}
		

		border.setCenter(makeGrid());
		
		scene = new Scene(border , 400 , 400);
		
		window.setScene(scene);
		window.show();
	}
	
	private static GridPane makeGrid()
	{
		GridPane grid = new GridPane();
		grid.setHgap(10);
	    grid.setVgap(10);
	    
	    for(int i = 0; i < 9; i++)
	    {
	    	grid.add(button[i] , i % 3 , i / 3);
	    }
		
		return grid;
	}
	
	private static void onMove(int move)
	{
		int turn = game.turn;
		//Image iconImage = new Image("./icon/" + (turn == 1 ? "cross.png" : "circle.png"));
		Image iconImage = new Image(turn == 1 ? "/icons/cross.png" : "/icons/circle.png");
		
		ImageView icon = new ImageView(iconImage);
		button[move - 1].setGraphic(icon);
		game.playGame(move);
		
		if(turn == 1)
		{
			border.setTop(Player2);
			BorderPane.setAlignment(Player2 , Pos.CENTER);
		}
		else if(turn == 2)
		{
			border.setTop(Player1);
			BorderPane.setAlignment(Player1 , Pos.CENTER);
		}
	}
	
	public static void reset()
	{
		initialiseScene();
		game = new Game();
	}
}
