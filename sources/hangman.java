package games;
import java.util.*;
import java.lang.*;
import java.io.*;
public class hangman

{
    public static final String word=input();
    public static String word2=new String(new char[word.length()]).replace("\0", "-");
    public static StringBuilder guess;
    public static int missedChances = 0;
    public static char [][] board = new char[7][7];
    

    public static String input() {
        Scanner file = null;
        try {
            file = new Scanner(new FileInputStream("C:/cygwin64/home/HP/CS195/final/resources/words.txt"));

        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] word = new String[20];

        for(int i=0;i<15;i++){
            word[i]=file.nextLine();

        }
        Random input = new Random();
        int num = input.nextInt(20);
        String Word1=word[num];


        return(Word1.toLowerCase());
    }
 public static boolean GameOver(StringBuilder g)
    {
        if (missedChances==10)
        {
            System.out.println("You lose!");
            return true;
        }
        else if (guess.indexOf("-")<0)

        {
            System.out.println("You win!");
            return true;
        }
        return false;
    }

    public static void PrintBoard(char [][]b)
    {
        for (int i=0; i<7; i++)

        {
            for (int j=0; j<7; j++)
                System.out.print(b[i][j]);
            System.out.println();
        }
    }
    public static void Guess(char gletter, StringBuilder garray)
    {
        boolean correct=false;
        for (int i=0; i<word.length(); i++)
        {
            if (gletter==word.charAt(i))
            {
                garray.setCharAt(i, gletter);
                correct=true;
            }
        }
        if (correct==false){
            missedChances++;
            System.out.println("Sorry! Your guess was wrong");
            switch (missedChances){
                case 1:
                    board[2][3] = '(';
                    break;
                case 2:
                    board[2][5] = ')';
                    break;
                case 3:
                    board[3][3] = '/';
                    break;
                case 4:
                    board[3][5]='\\';
                    break;
                case 5:
                    board[3][4]='|';
                    break;
                case 6:
                    board[4][4]='|';
                    break;
                case 7:
                    board[5][3]='/';
                    break;
                case 8:
                    board[5][5]='\\';
                    break;
                case 9:
                    board[5][2]='_';

                    break;
                case 10:
                    board[5][6]='_';
                    break;
            }
            PrintBoard(board);
        }
    }
   
    public static void main (String []args)
    {
        System.out.println("Welcome this is Hangman game\n"+
                "Should we start now?\n"+
                "Try guessing the word.");
        guess=new StringBuilder(word2);
        IBoard(board);
        PrintBoard(board);

        Scanner keyboard=new Scanner(System.in);
        missedChances=0;
        while (GameOver(guess)==false)
        {
            char letter;

            System.out.println("Guess a letter: ");
            letter=keyboard.next().toLowerCase().charAt(0);

            Guess(letter, guess);
            System.out.println(guess);
        }
        System.out.println("Correct word was: "+word);
    }
	public static void IBoard(char [][]b)
    {
        for (int i=0; i<7; i++)
        {
            for (int j=0; j<7; j++)
                b[i][j]=' ';
        }
        b[1][4]='|';
        for (int j=0; j<=6; j++)
            b[0][j]='-';
        for (int i=1; i<=6; i++)
            b[i][0]='|';
    }
}
