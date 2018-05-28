
public class Game 
{
	int grid[][];	//grid to store moves
	Player p1 , p2; //players of this game
	public int turn;
	
	/**
	 * grid elements are  initialized to zero
	 * Players are created by calling createPlayers() 
	 */
	public Game()
	{
		 grid = new int[3][3];
		 //initializing grid elements to zero
		 for(int i = 0; i < 3; i++)
			 for(int j = 0; j < 3; j++)
				 grid [i][j] = 0;
		 turn = 1;
		 createPlayers();
	}
	
	/**
	 * Names of players are input and players are created
	 */
	public void createPlayers()
	{
		p1 = new HumanPlayer("Player 1" , 1);
		p2 = new HumanPlayer("Player 2" , 2);
	}
	
	
	/**
	 * places the move in the grid
	 * @param id : id of player making the move
	 * @param move : the move played by the player
	 */
	void placeMove(int id , int move)
	{
		grid[(move - 1) / 3][(move - 1) % 3] = id;
	}
	
	
	/**
	 * Checks if the game is over
	 * @return id of the winning Player , 0 if game is not over , -1 if draw
	 */
	 int isOver()	
	{
		int checker = 1;	//used to check for winner 
		int i , j;	//looping variables
		
		/*
		 * Checking if any single row has all three cells occupied by the same player
		 * If id 1 occupies 3 cells, they multiply up to 1 and if 2 occupies they multiply up to 8
		 */
		for(i = 0; i < 3; i++)
		{
			checker = 1;
			for(j = 0; j < 3; j++)
				checker *= grid[i][j];
			if(checker == 1)
				return 1;
			if(checker == 8)
				return 2;
		}
		
		/*
		 * Checking if any single column has all three cells occupied by the same player
		 * If id 1 occupies 3 cells, they multiply up to 1 and if 2 occupies they multiply up to 8
		 */
		for(i = 0; i < 3; i++)
		{
			checker = 1;
			for(j = 0; j < 3; j++)
				checker *= grid[j][i];
			if(checker == 1)
				return 1;
			if(checker == 8)
				return 2;
		}
		
		/*
		 * Checking if any diagonal has all three cells occupied by the same player
		 * If id 1 occupies 3 cells, they multiply up to 1 and if 2 occupies they multiply up to 8
		 */
		
		if(grid[0][0] * grid[1][1] * grid[2][2] == 1 || grid[0][2] * grid[1][1] * grid[2][0] == 1)
			return 1;
		if(grid[0][0] * grid[1][1] * grid[2][2] == 8 || grid[0][2] * grid[1][1] * grid[2][0] == 8)
			return 2;
		
		/*if any empty cell is present, the game is not over*/
		for(i = 0; i < 3; i++)
			for(j = 0; j < 3; j++)
				if(grid[i][j] == 0)
					return 0;
		
		/*if grid is full and there is no winner , game is draw*/
		return -1;
		
	}
	
	
	/**
	 * Prints the current grid
	 * player id 1 is 'X' and player id 2 is 'O'
	 */
	void printGrid()
	{
		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j < 3; j++)
			{
				if(j == 2)
					System.out.println(" " + (grid[i][j] == 1 ? 'X' : (grid[i][j] == 2 ? 'O' : " ")));
				else
					System.out.print(" " + (grid[i][j] == 1 ? 'X' : (grid[i][j] == 2 ? 'O' : " ")) + " |");
			}
			if(i != 2)
				System.out.println("------------");
			else
				System.out.println();
		}
	}
	
	
	/**
	 * Checks if the move is valid 
	 * @param move : the move entered by the player
	 * @return true if move is valid false if invalid
	 */
	boolean isValidMove(int move)
	{
		if(grid[(move - 1) / 3][(move - 1) % 3] == 0)
			return true;
		return false;
	}
	
	/**
	 * makes move of current player
	 * @param turn : indicating the current player
	 * @return move : the input move
	 */
	int makeMove()
	{
		int move;
		if(turn == 1)
			move = p1.makeMove();
		else
			move = p2.makeMove();
		return move;
	}
	
	/**
	 * 
	 * @param move : the input move
	 * @return whether current game is over or not
	 */
	public boolean playGame(int move)
	{
		boolean playAgain = false;
		placeMove(turn , move);
		int isOver = isOver();
		turn = 3 - turn;	//toggle turn between 1 and 2											
		
		if(isOver == 1)
			playAgain = Result.display("Game Over" , p1.name + " WINS!");
		else if(isOver == 2)
			playAgain = Result.display("Game Over" , p2.name + " WINS!");
		else if(isOver == -1)
			playAgain = Result.display("Game Over" , "GAME DRAWN!");
		if(isOver != 0)
		{
			if(playAgain)
			{
				Main.reset();
				return true;
			}
			else
			{
				System.exit(0); 
			}
		}
		return false;
	}
}
