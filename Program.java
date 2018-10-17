import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;
import java.lang.*;

	// Colors.
  /*BLACK = "\u001B[30m";
	RED = "\u001B[31m";
	GREEN = "\u001B[32m";
	YELLOW = "\u001B[33m";
	BLUE = "\u001B[34m";
	PURPLE = "\u001B[35m";
	CYAN = "\u001B[36m";
	WHITE = "\u001B[37m";**/

public class Program
{
	public static void main(String[] args)
	{
		// Dimension size of the board.
		int yDim = 10;
		int xDim = 20;
		// Reading data from .txt file.
		int[][] grid = new int[yDim][xDim];
		int turns;
		while (true)
		{
			try
			{
				System.out.print("Enter the level.txt filename : ");
				Scanner level = new Scanner(System.in);
				level = new Scanner(new File(level.nextLine() + ".txt"));

				
				for(int i = 0; i < yDim; i++)
				{
					for(int j = 0; j < xDim; j++)
					{
						grid[i][j] = level.nextInt();
					}
				}

				turns = level.nextInt();
				break;
			}

			catch (Exception e)
			{
				System.out.println("Invalid file name.");
			}
		}
		// Scanner for user input.
		Scanner keyboard = new Scanner(System.in);
		String input;
		// Player block.
		Block player = new Block("\u001B[36m", "@", 100, 0, turns);
		// Creating a list of all other blocks.
		ArrayList<Block> bList = new ArrayList<Block>();
		bList.add( new Block("\u001B[37m", "-", 0, 0, -1) );     // Empty block      , bList.get(0)
		bList.add( new Block("\u001B[37m", "#", 1, 0, -1) );     // Wall block       , bList.get(1)
		bList.add( new Block("\u001B[31m", "1", 2, -1, -1) );    // 1 Damage block   , bList.get(2)
		bList.add( new Block("\u001B[31m", "2", 3, -2, -1) );    // 2 Damage block   , bList.get(3)
		bList.add( new Block("\u001B[31m", "3", 4, -3, -1) );    // 3 Damage block   , bList.get(4)
		bList.add( new Block("\u001B[32m", "1", 5, 1, -1) );     // 1 Heal block     , bList.get(5)
		bList.add( new Block("\u001B[32m", "2", 6, 2, -1) );     // 2 Heal block     , bList.get(6)
		bList.add( new Block("\u001B[32m", "3", 7, 3, -1) );     // 3 Heal block     , bList.get(7)
		bList.add( new Block("\u001B[33m", "O", 8, 0, 1000) );   // Goal block       , bList.get(8)

		// Creating board.
		Block[][] board = new Block[yDim][xDim];
		// Setting the player's starting location.
		player.setLocation(1, 1);
		initBoard(board, grid, bList, yDim, xDim, player);
		runBoard(board);
		System.out.println("'w, a, s, d' then 'enter' to move. 'exit' to quit.");
		// Creating a list that will store the id of the block
		// the player is currently standing on.
		ArrayList<Integer> temp = new ArrayList<Integer>();
		temp.add(0);
		// Main loop.
		// This loop reads user input and runs a corresponding movement method.
		while (true)
		{
			player.showStatus();
			System.out.println("Your location: " + player.getX() + ", " + player.getY());
			player.checkStatus();
			input = keyboard.nextLine();
			if ("d".equals(input)){
				moveX(1 , board, bList, player, temp);
				runBoard(board);
			}
			else if ("a".equals(input)){
				moveX(-1 , board, bList, player, temp);
				runBoard(board);
			}
			else if ("w".equals(input)){
				moveY(-1 , board, bList, player, temp);
				runBoard(board);
			}
			else if ("s".equals(input)){
				moveY(1 , board, bList, player, temp);
				runBoard(board);
			}
			else if ("loc".equals(input)){
				System.out.println(player.getX() + ", " + player.getY());
			}
			else if ("exit".equals(input)){
				break;
			}
		}
		
	}
	// Level initialization.
	public static void initBoard(Block[][] board, int[][] grid, ArrayList<Block> bList, int y, int x, Block player)
	{
		for (int i = 0; i < y; i++)
		{
			for (int j = 0; j < x; j++)
			{
				board[i][j] = bList.get(grid[i][j]);
			}
		}
		board[player.getY()][player.getX()] = player;
	}
	// Refresh the board when player moves.
	public static void runBoard(Block[][] board)
	{
		System.out.print("\033[H\033[2J");
		
		for(Block[] innerArray : board)
		{
			for (Block block : innerArray)
			{
				System.out.print(block.getTexture());
			}

			System.out.println("");
		}
	}
	// Player moves on x-axis.
	public static void moveX(int direction, Block[][] board, ArrayList<Block> bList, Block player, ArrayList<Integer> temp)
	{
		if (board[player.getY()][player.getX() + direction] != bList.get(1))
		{
			board[player.getY()][player.getX()] = bList.get(temp.get(0));
			player.takeDamage(board[player.getY()][player.getX() + direction].getDamage());
			player.changeTurns(board[player.getY()][player.getX() + direction].getTurn());
			temp.set(0, board[player.getY()][player.getX() + direction].getId());
			board[player.getY()][player.getX() + direction] = player;
			player.setLocation(player.getX() + direction, player.getY());
		}
		else
		{

		}
	}
	// Player moves on y-axis.
	public static void moveY(int direction, Block[][] board, ArrayList<Block> bList, Block player, ArrayList<Integer> temp)
	{
		if (board[player.getY() + direction][player.getX()] != bList.get(1))
		{
			board[player.getY()][player.getX()] = bList.get(temp.get(0));
			player.takeDamage(board[player.getY() + direction][player.getX()].getDamage());
			player.changeTurns(board[player.getY() + direction][player.getX()].getTurn());
			temp.set(0, board[player.getY() + direction][player.getX()].getId());
			board[player.getY() + direction][player.getX()] = player;
			player.setLocation(player.getX(), player.getY() + direction);
		}
		else
		{

		}
	}



}