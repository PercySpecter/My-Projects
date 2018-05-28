import java.util.*;

public class HumanPlayer extends Player
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
