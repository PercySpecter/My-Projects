import java.util.*;


public class Main 
{
	
	/**
	 * to print the move number for each cell as a guide
	 */
	public static void printGuide()
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
	}
	
	static void playGame()
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("TIC - TAC - TOE\n~~~~~~~~~~~~~~~~~~~");
		printGuide();
		boolean play = true;
		while(play)
		{
			System.out.println();
			Game game = new Game();
			game.playGame();
			
			play = false;
			System.out.println("\nPlay again? (Y / N)");
			char c = sc.next().charAt(0);
			if(c == 'y' || c == 'Y')
				play = true;
		}
		
		System.out.println("Thank You for playing!!");
		
		sc.close();
	}
	
	public static void main(String[] args) 
	{
		playGame();
	}

	
}

class Game 
{
	int grid[][];	//grid to store moves
	Player p1 , p2; //players of this game
	
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
		 createPlayers();
	}
	
	/**
	 * Names of players are input and players are created
	 */
	public void createPlayers()
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter name of 1st player(no spaces):");
		p1 = new HumanPlayer(sc.next() , 1);
		System.out.println("Enter name of 2nd player(no spaces):");
		p2 = new HumanPlayer(sc.next() , 2);
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
	int makeMove(int turn)
	{
		int move;
		if(turn == 1)
			move = p1.makeMove();
		else
			move = p2.makeMove();
		return move;
	}
	
	public void playGame()
	{
		int isOver = isOver();
		int turn = 1;
		int move;
		while(isOver == 0)
		{
			System.out.println();
			printGrid();
			move = makeMove(turn);
			while(!isValidMove(move))
			{
				System.out.println("Invalid move!!");
				move = makeMove(turn);
			}
			placeMove(turn , move);
			isOver = isOver();
			turn = 3 - turn;	//toggle turn between 1 and 2
		}
		
		printGrid();
		if(isOver == 1)
			System.out.println(p1.name + " wins!!");
		else if(isOver == 2)
			System.out.println(p2.name + " wins!!");
		else
			System.out.println("Game Draw!!");
	}
}

class HumanPlayer extends Player
{
	public HumanPlayer(String name , int id)
	{
		super(name , id);
	}
	
	
	/**
	 * move is input and returned 
	 * @return move : an integer from 1 to 9 representing cell number
	 */
	@Override
	public int makeMove()	//takes an index as input and return it
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("\n" + name + "\'s turn");
		System.out.println("Enter a valid move:");
		int move = sc.nextInt();
		return move;
	}
}

class Player
{
	public String name;	//player name
	public int id;	//1 for first player , 2 for second player
	
	public Player(String name , int id)
	{
		this.name = name;
		this.id = id;
	}
	
	public int makeMove() {return 0;} 
}



