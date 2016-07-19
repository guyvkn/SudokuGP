package SodukoGame;

import java.util.Dictionary;

public class Terminal extends TerminalOrPrimitive {
	//private final String functionName;
	
	final private int size=9;
	
	
	public Terminal(String operationName ){
		super("Terminal",operationName);
	}
	

	@Override
	double Run(int row,int col,int key, int[][] board,Dictionary <Integer,Double>[][] gradeboard) {
		switch (this.operationName){
		case "countEmptyCellInRow":
			return (double)countEmptyCellInRow(row,board);
			
		case "countEmptyCellInCol":
			return (double)countEmptyCellInCol(col,board);
			
		case "countEmptyCellInSquare":
			return (double)countEmptyCellInSquare(row,col,board);
		
		case "NumOfOptionsInCell":
			return (double)NumOfOptionsInCell(row,col,gradeboard);
			
		case "numOfOptionsToAppearInBoard":
			return (double)numOfOptionsToAppearInBoard(key,board);
			
		case "fun1":
			return (double)fun1(key,board);
		
		case "fun2":
			return (double)fun2(key, board);
			
		case "fun3":
			return (double)fun3(key, board);
			
		//////////////
		//under testing : 
		
		case "fun4":
			return (double)fun4(key,board);
		
		case "fun5":
			return (double)fun5(key, board);
			
		case "fun6":
			return (double)fun6(key, board);
			
		default :
			System.out.println("\nSomthing Wrong in the terminals");
			System.exit(0);
			return 0;
		}
	}

	
	
	//-----------------Function for terminals----------------//
		/*Variable: 
		 * row- the row number that we check
		 * col- the column number that we check
		 * VAL- the number in the cell
		//-------------------------------------------------------//
		/*This function will check the number of the empty cells in the row.
		 * The function using the board of the soduko game and it will be
		 * a part of the individual terminal.
		 */
		int countEmptyCellInRow(int row, int[][] board){
			int countOfEmptyRow=0;
			for(int i=0;i<this.size;i++)
			{
				/*Here we will check if the cells  in our row
				 * is empty (0),and we count how many empty cells  we got in the row 
				 */
				if(board[row][i]==0)
					countOfEmptyRow++;
			}
			
			return countOfEmptyRow;
			
		}
		/*This function will check the number of the empty cells in the column.
		 * The function using the board of the soduko game and it will be
		 * a part of the individual terminal.
		 */
		int countEmptyCellInCol(int col, int[][] board){
			int countOfEmptyColumn=0;
			for(int i=0;i<this.size;i++)
			{
				if(board[i][col]==0)
				{
					countOfEmptyColumn++;
				}
			}
			return countOfEmptyColumn;
		}
		/*This function will check how many empty cells  there is in the squres
		 * of our number (key) 
	 	*/
		int countEmptyCellInSquare(int row,int col, int[][] board){
			if (row==0 || row==3 || row==6)
			{
				if (col==0 || col== 3 || col==6)
				{
					return(CountEmptyCell(row,col,board));
				}
				if (col==1 || col== 4 || col==7)
				{
					return(CountEmptyCell(row,col-1,board));
				}
				if (col==2 || col== 5 || col==8)
				{
					return(CountEmptyCell(row,col-2,board));
				}
			}
			if (row==1 || row==4 || row==7)
			{
				if (col==0 || col== 3 || col==6)
				{
					return (CountEmptyCell(row-1,col,board));
				}
				if (col==1 || col== 4 || col==7)
				{
					return(CountEmptyCell(row-1,col-1,board));
				}
				if (col==2 || col== 5 || col==8)
				{
					return(CountEmptyCell(row-1,col-2,board));
					
				}
			}
			if (row==2 || row==5 || row==8)
			{
				if (col==0 || col== 3 || col==6)
				{
					return(CountEmptyCell(row-2,col,board));
				}
				if (col==1 || col== 4 || col==7)
				{
					return(CountEmptyCell(row-2,col-1,board));
				}
				if (col==2 || col== 5 || col==8)
				{
					return(CountEmptyCell(row-2,col-2,board));
				}
			}
			
			System.out.println("Somthing Wrong in countEmptyCellInSquare function in Terminal class ");
			System.exit(0);
			return 0;
		}
		/*This fuction get the base row and col for the number that we whant to check
		 * the function return thr number of empty cell in the squre
		 * 
		 */
		int CountEmptyCell(int row,int col, int[][] board)
		{
			int numOf=0;
			final int SizeOfSqure=3;
			for(int i=row;i<row+SizeOfSqure;i++)
				for(int j=col;j<col+SizeOfSqure;j++)
				{
					if(board[i][j]==0)
					{
						numOf++;
					}
				}

			return numOf;
		}

		
		/*
		 * This function receve index of soudko matrix and count the number of the 
		 * the legal option that we can insert in this index
		 */
		int NumOfOptionsInCell(int row,int col,Dictionary <Integer,Double>[][] gradeboard){
			
			return (gradeboard[row][col].size());
			
		}
		
		/*
		 * in every soduko every number can apper exacly 9 times 
		 * so this function recive a number and count how mutch time it can apper yet
		 */
		int numOfOptionsToAppearInBoard(int key, int[][] board){
			int num=0;
			for(int i=0;i<this.size;i++)
				for(int j=0;j<this.size;j++)
				{
					if(board[i][j]==key)
						num++;
				}
			return size-num;
		}
		
		
		int fun1(int key, int[][] board){
			int numOfEmptyCell=0;
			for(int i=0;i<this.size;i++)
			{
				if(existInRow(key,board,i))
				{
					for(int j=0;j<this.size;j++)
					{
						if(board[i][j]==0)
							numOfEmptyCell++;
					}
				}
			}
			return numOfEmptyCell;
		}
		int fun2(int key, int[][] board){
			int numOfEmptyCell=0;
			for(int i=0;i<this.size;i++)
			{
				if(existInCol(key,board,i))
				{
					for(int j=0;j<this.size;j++)
					{
						if(board[j][i]==0)
							numOfEmptyCell++;
					}
				}
			}
			return numOfEmptyCell;
		}
		int fun3(int key, int[][] board)
		{
			int numOfEmptyCell=0;
			for(int i=0;i<this.size;i+=3)
			{
				for(int j=0;j<this.size;j+=3)
				{
					if( existInSquare(i, j, key, board))
					{
						numOfEmptyCell+=CountEmptyCell(i,j,board);
					}
				}
			}
			return numOfEmptyCell;
			
		}
		
		// ------------------------static function----------------------------------//
		
		public static boolean existInSquare(int row,int col,int key, int[][] board)
		{
			final int SizeOfSqure=3;
			for(int i=row;i<row+SizeOfSqure;i++)
				for(int j=col;j<col+SizeOfSqure;j++)
				{
					if(board[i][j]==key)
					{
						return true;
					}
				}
			return false;
		}
		public static boolean existInRow(int num, int[][] board,int row)
		{
			for(int i=0;i<board[row].length;i++)
			{
				if(board[row][i]==num)
					return true;
			}
			return false;	
		}
		public static boolean existInCol(int num, int[][] board,int col)
		{
			for(int i=0;i<board.length;i++)
			{
				if(board[i][col]==num)
					return true;
			}
			return false;
		}
		
		
		//---------------------Under testing ----------------------------

		int fun4(int key, int[][] board){
			int numOfEmptyCell=0;
			for(int i=0;i<this.size;i++)
			{
				if(!existInRow(key,board,i))
				{
					for(int j=0;j<this.size;j++)
					{
						if(board[i][j]==0)
							numOfEmptyCell++;
					}
				}
			}
			return numOfEmptyCell;
		}
		int fun5(int key, int[][] board){
			int numOfEmptyCell=0;
			for(int i=0;i<this.size;i++)
			{
				if(!existInCol(key,board,i))
				{
					for(int j=0;j<this.size;j++)
					{
						if(board[j][i]==0)
							numOfEmptyCell++;
					}
				}
			}
			return numOfEmptyCell;
		}
		int fun6(int key, int[][] board)
		{
			int numOfEmptyCell=0;
			for(int i=0;i<this.size;i+=3)
			{
				for(int j=0;j<this.size;j+=3)
				{
					if(!existInSquare(i, j, key, board))
					{
						numOfEmptyCell+=CountEmptyCell(i,j,board);
					}
				}
			}
			return numOfEmptyCell;
			
		}
}