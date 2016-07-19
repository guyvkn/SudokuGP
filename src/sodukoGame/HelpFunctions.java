package sodukoGame;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Random;

public class HelpFunctions {
	
	/*
	 * this class contain only static helping function for our application.
	 * FUNCTION like couning empty cells in a soudko board, printing board ,
	 * creating soducko board and filling from soduckos file, functions that fill the list of our terminal 
	 * and primitive , convert primitive from function name to operator . and other helping function.
	 */
	
	// static functions : 
	static void fillFunctions(ArrayList<String> functions)
	{

		functions.add("countEmptyCellInRow");
		functions.add("countEmptyCellInCol");
		functions.add("countEmptyCellInSquare");
		functions.add("NumOfOptionsInCell");
		functions.add("numOfOptionsToAppearInBoard");

		
		functions.add("countEmptyCellsInRowsContainsNum");
		functions.add("countEmptyCellsInColsContainsNum");
		functions.add("countEmptyCellsInSquareContainsNum");
		
		// under testing :
		functions.add("countEmptyCellsInRows_ThatNotContainsNum");
		functions.add("countEmptyCellsInCols_ThatNotContainsNum");
		functions.add("countEmptyCellsInSquare_ThatNotContainsNum");
		
	}
	static void Filloperators(ArrayList<String> operators)
	{
		operators.add("Plus");
		operators.add("Minus");
		operators.add("Multi");
		operators.add("div");
		operators.add("Mod");
		operators.add("Maximum");
		operators.add("Minimum");
		
	}
	static String ConvertFromFunctionToOperator(String operationName)
	{
		switch (operationName){
		case "Plus":
			return 	"+";

		case "Minus":
			return "-";

		case "Multi":
			return "*";

		case "div":
			return "/";

		case "Mod":
			return "%";

		case "Maximum":
			return "Max";

		case "Minimum":
			return "Min";

		default :
			return operationName;
		}
	}
	
	/*This function geting a randomaly board from our database 
	 * of boards to solve.
	 * The RandomNum will be the number of the board in the file
	 */
	public static int [][] loadSudokuBoardFromFile(int randomNum) {
		final int  size=9;
		int [][] borad=new int[size][size];
		int flag=1,NumOfBoardInFile=0,read;
		try {
			File f = new File("board.txt");
			FileReader r =new FileReader(f);
			@SuppressWarnings("resource")
			BufferedReader br = new BufferedReader(r); 
			String str;
			while((str=br.readLine())!=null  && flag==1)
			{
				if(str.startsWith("G"))
				{
					if(randomNum==NumOfBoardInFile)
					{
						for(int i=0;i<size;i++)
						{
							for(int j=0;j<size;j++)
							{
								read=br.read();
								borad[i][j]=Character.getNumericValue(read);
							}	
						}
						flag=0;
					}
					else
						NumOfBoardInFile++;	
				}
			}
		}
		catch (IOException ex){
			System.out.println("File not found");
		}
		return borad;
	}
	
	
	static void waitFor(int second)
	{
		System.out.println("We will start the process after ( "+ second+" ) seconds ,please be patient:");
		for (int i = 1; i <= second; i++) {
			System.out.print(i+" ");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println();
	}
	
	static int randomNumber(int min,int max)
	{
		// if you need to generate numbers from min to max (including both)
		Random random = new Random();
		return random.nextInt(max - min + 1) + min;
		
	}
	static int randomNumber(int max)
	{
		// if you need to generate numbers from min to max (including both)
		Random random = new Random();
		return random.nextInt(max);
		
	}
	
	
	/*
	 * This gives you a random number in between min (inclusive) and max (exclusive) .
	 * this function is give a number in range [min,max) .
	 * And it act in strange way , if you send run it with (1,1) argument it's retrun : 1 
	 * and if you run it with (1,2) argument it's retrun : 1
	 * and if you run it with (1,0) argument it's retrun : 1 
	 * and if you run it with (1,3) argument it's retrun : 1 or 2
	 * it's return a random number of foucsing on min. 
	 * and we using it in the mution , it's very importatnt method.
	 */
	static int randomNumberFromInclusiveToExclusive(int min,int max)
	{
		return min+(int)(Math.random()*(max-min));
	}
	
	static int[][] fillTheMatrix1()
	{
		// this the soducko that we fill it in the beganing
		final int size=9;
		int[][] board=new int [size][size]; 
		
		board[0][3]=8;
		board[0][4]=4;
		board[0][8]=9;
		//
		board[1][2]=1;
		board[1][8]=5;
		//
		board[2][0]=8;
		board[2][4]=2;
		board[2][5]=1;
		board[2][6]=4;
		board[2][7]=6;
		//
		board[3][0]=7;
		board[3][2]=8;
		board[3][7]=9;
		//
		board[5][1]=5;
		board[5][6]=3;
		board[5][8]=1;
		//
		board[6][1]=2;
		board[6][2]=4;
		board[6][3]=9;
		board[6][4]=1;
		board[6][8]=7;
		//
		board[7][0]=9;
		board[7][6]=5;
		//
		board[8][0]=3;
		board[8][4]=8;
		board[8][5]=4;
		return board;

	}
	static int[][] fillTheMatrix2()
	{
		// very hard soducko
		final int size=9;
		int[][] board=new int [size][size];
		
		board[0][0]=6;
		board[0][2]=3;
		board[0][4]=2;
		board[0][6]=9;
		//
		board[1][1]=7;
		board[1][3]=6;
		//
		board[2][1]=2;
		board[2][3]=1;
		board[2][6]=4;
		//
		board[3][3]=7;
		board[3][5]=1;
		board[3][7]=2;
		board[3][8]=5;
		//
		board[5][0]=8;
		board[5][1]=4;
		board[5][3]=2;
		board[5][5]=3;
		//
		board[6][2]=9;
		board[6][5]=5;
		board[6][7]=8;
		//
		board[7][5]=6;
		board[7][7]=7;
		//
		board[8][2]=4;
		board[8][4]=7;
		board[8][6]=3;
		board[8][8]=1;
		
		return board;
	}
	static int[][] fillTheMatrix3()
	{
		//very difficult and requires the use of advanced techniques
		final int size=9;
		int[][] board=new int [size][size];
		
		board[0][2]=1;
		board[0][3]=4;
		board[0][7]=6;
		board[0][8]=3;
		//
		board[1][4]=5;
		//
		board[2][4]=6;
		board[2][6]=2;
		board[2][7]=7;
		board[2][8]=1;
		//
		board[3][1]=6;
		board[3][2]=9;
		board[3][5]=1;
		board[3][6]=8;
		board[3][7]=3;
		//
		board[5][1]=8;
		board[5][2]=5;
		board[5][3]=2;
		board[5][6]=1;
		board[5][7]=4;
		//
		board[6][0]=3;
		board[6][1]=9;
		board[6][2]=2;
		board[6][4]=8;
		//
		board[7][4]=2;
		//
		board[8][0]=6;
		board[8][1]=4;
		board[8][5]=9;
		board[8][6]=7;
		
		return board;
	}
	static int[][] fillTheMatrix4()
	{
		//World's hardest sudoku
		final int size=9;
		int[][] board=new int [size][size];
		
		board[0][0]=8;
		//
		board[1][2]=3;
		board[1][3]=6;
		//
		board[2][1]=7;
		board[2][4]=9;
		board[2][6]=2;
		//
		board[3][1]=5;
		board[3][5]=7;
		//
		board[4][4]=4;
		board[4][5]=5;
		board[4][6]=7;
		//
		board[5][3]=1;
		board[5][7]=3;
		//
		board[6][2]=1;
		board[6][7]=6;
		board[6][8]=8;
		//
		board[7][2]=8;
		board[7][3]=5;
		board[7][7]=1;
		//
		board[8][1]=9;
		board[8][6]=4;
		
		return board;
	}
	static int countEmptyCellInSudoku(int[][] board )
	{
		int counter=0;
		for(int i=0;i<board.length;i++)
		{
			for(int j=0;j<board[i].length;j++)
			{
				if(board[i][j]==0)
					counter++;
			}
		}
		return counter;	
	}

	static void printSudokuBoard(int[][] board )
	{
		System.out.println("Empty cell : "+countEmptyCellInSudoku(board));
		for(int i=0;i<board.length;i++)
		{
			for(int j=0;j<board[i].length;j++)
			{
				System.out.print(board[i][j]+" ");
				if((j+1)%3==0)
					System.out.print(" ");
			}
			System.out.println();
			if((i+1)%3==0)
				System.out.println();
		}
	}


	static void printGradeBoard(Hashtable<Integer, Double>[][] gradeboard )
	{
		// must see later again
		// we use this function just in printing , we don't use it for our application
		for(int i=0;i<gradeboard.length;i++)
		{
			for(int j=0;j<gradeboard[i].length;j++)
			{
				System.out.print("Index :"+ "["+(i)+"]" + "["+(j)+"]"+" : " );
				System.out.println(gradeboard[i][j]);
			}
		}
		for(int i=0;i<gradeboard.length;i++)
		{
			for(int j=0;j<gradeboard[i].length;j++)
			{
				if(gradeboard[i][j].contains(0.0) || gradeboard[i][j].containsValue(0.0))
				{
					System.out.println("there are a zero");
				}
			}
		}
		double min=Double.MAX_VALUE;
		for(int i=0;i<gradeboard.length;i++)
		{
			for(int j=0;j<gradeboard[i].length;j++)
			{
				Enumeration<Double> values = gradeboard[i][j].elements();
				while (values.hasMoreElements()) 
				{
					double value = (double) values.nextElement();
					if(min>value)
						min=value;
				}
			}
		}
		System.out.println("Min values : ");
		for(int i=0;i<gradeboard.length;i++)
		{
			for(int j=0;j<gradeboard[i].length;j++)
			{
				if(gradeboard[i][j].contains(min) || gradeboard[i][j].containsValue(min) )
				{
					System.out.print("Index :"+ "["+(i)+"]" + "["+(j)+"]"+" : " );
					System.out.println(gradeboard[i][j]);
				}
			}
		}
	}
		

}

