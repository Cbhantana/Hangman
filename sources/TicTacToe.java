package games;
import java.util.*;

public class TicTacToe
{
	Array board=new Array(7, 7);
	int counter;

	public TicTacToe()
	{
		String even=" %-% %-% %-% ";
		String odd="|% %|% %|% %|";
		String []lines={even, odd, even, odd, even, odd, even};
		board.Initialize(lines, "%");

		counter=0;
	}

	public void PrintBoard()
	{
		board.Print();
	}

	public boolean GameOver()
	{
		for (int i=1; i<7; i+=2)
		{
			//if (board.Row(i)[0].equals(board.Row(i)[1]) && board.Row(i)[1].equals(board.Row(i)[2]))
			String row=board.myArray[i][1]+board.myArray[i][3]+board.myArray[i][5];
			System.out.println("row="+row);
			if (row.equals("XXX") || row.equals("000"))
			{
				System.out.println("Horizontal!");
				return true;
			}
		}
		for (int j=1; j<7; j+=2)
		{
			//if (board.Col(j)[0].equals(board.Col(j)[1]) && board.Col(j)[1].equals(board.Col(j)[2]))
			String col=board.myArray[1][j]+board.myArray[3][j]+board.myArray[5][j];
			System.out.println("col="+col);
			if (col.equals("XXX") || col.equals("000"))
			{
				System.out.println("Vertical!");
				return true;
			}
		}
		String diagonal=board.myArray[1][1]+board.myArray[3][3]+board.myArray[5][5];
		if (diagonal.equals("XXX") || diagonal.equals("000"))
		{
				System.out.println("Diagonal!");
				return true;
		}
		diagonal=board.myArray[1][5]+board.myArray[3][3]+board.myArray[5][1];
		if (diagonal.equals("XXX") || diagonal.equals("000"))
		{
				System.out.println("Diagonal!");
				return true;
		}
		if (counter==9)
		{
			System.out.println("Tie!");
			return true;
		}
	return false;
	}

	public class FullSquare extends Exception 
	{
		public FullSquare(String message)
		{
			super(message);
		}
	}

	public void Turn(int player)
	{
		Scanner keyboard=new Scanner(System.in);
		System.out.println("Enter position [row=1/3/5 column=1/3/5]: ");
		String input=keyboard.nextLine();
		String []coordinates=input.split(" ");
		int x=Integer.parseInt(coordinates[0]);
		int y=Integer.parseInt(coordinates[1]);

		String symbol="*";
		switch (player)
		{
		case 0:
			symbol="X";
			break;
		case 1:
			symbol="0";
			break;
		default:
			break;
		}

		try
		{
			if (!board.myArray[x][y].equals(" "))
				throw new FullSquare("Invalid game play.");
			board.Update(x, y, symbol);
			counter++;
			PrintBoard();
		}
		catch (FullSquare exception)
		{
			System.out.println("Square is full, please try again!");
			Turn(player);
		}

	}

	public void Play()
	{
		String []players={"X", "0"};
		int next=0;
		do
		{
			Turn(next);
			next=(next+1)%2;
		}
		while (GameOver()==false); 
		if (counter!=9)
			System.out.printf("Player %s wins!\n", players[next%2]);
	}

	public static void main(String []args)
	{
		TicTacToe game=new TicTacToe();
		game.PrintBoard();
		game.Play();
	}
}
