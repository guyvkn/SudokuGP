package SodukoGame;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Random;

public class BoardIndividual extends Individual {

	Node<TerminalOrPrimitive> individualTree;
	final int size=9;
	int hight;
	int[][] board ;
	Dictionary <Integer,Double>[][]  gradeboard;


	// static variables : 
	static ArrayList<String> functionNames= new  ArrayList<String>();
	static ArrayList<String> Operators=new  ArrayList<String>();
	static Random random = new Random();


	// static functions : 
	static void FillFunctionName()
	{
		functionNames.add("countEmptyCellInRow");
		functionNames.add("countEmptyCellInCol");
		functionNames.add("countEmptyCellInSquare");
		functionNames.add("NumOfOptionsInCell");
		
		
		functionNames.add("numOfOptionsToAppearInBoard");
		
		/*
		functionNames.add("fun1");
		functionNames.add("fun2");
		functionNames.add("fun3");
		/*
		functionNames.add("fun4");
		functionNames.add("fun5");
		functionNames.add("fun6");
		*/
		
		
	}
	static void FillOperators()
	{
		Operators.add("Plus");
		Operators.add("Minus");
		Operators.add("Multi");
		Operators.add("div");
		Operators.add("Mod");
		Operators.add("Maximum");
		Operators.add("Minimum");


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
	static int countEmptyCell(int[][] board )
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
	static void fillTheMatrix(int[][] board )
	{
		for(int i=0;i<board.length;i++)
			for(int j=0;j<board[i].length;j++)
				board[i][j]=0;

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
		
		for(int i=0;i<board.length;i++)
		{
			for(int j=0;j<board[i].length;j++)
			{
				System.out.print(board[i][j]+"  ");
				if((j+1)%3==0)
					System.out.print(" ");
			}
			System.out.println();
			if((i+1)%3==0)
				System.out.println();
		}

	}
	static void fillTheMatrix1(int[][] board )
	{
		// very hard soducko
		for(int i=0;i<board.length;i++)
			for(int j=0;j<board[i].length;j++)
				board[i][j]=0;

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
		
		for(int i=0;i<board.length;i++)
		{
			for(int j=0;j<board[i].length;j++)
			{
				System.out.print(board[i][j]+"  ");
				if((j+1)%3==0)
					System.out.print(" ");
			}
			System.out.println();
			if((i+1)%3==0)
				System.out.println();
		}

	}
	static void fillTheMatrix2(int[][] board )
	{
		for(int i=0;i<board.length;i++)
			for(int j=0;j<board[i].length;j++)
				board[i][j]=0;

		board[0][1]=2;
		board[0][3]=1;
		board[0][4]=7;
		board[0][5]=8;
		board[0][7]=3;
		//
		board[1][1]=4;
		board[1][3]=3;
		board[1][5]=2;
		board[1][7]=9;
		//
		board[2][0]=1;
		board[2][8]=6;
		//
		board[3][2]=8;
		board[3][3]=6;
		board[3][5]=3;
		board[3][6]=5;
		//
		board[4][0]=3;
		board[4][8]=4;
		//
		board[5][2]=6;
		board[5][3]=7;
		board[5][5]=9;
		board[5][6]=2;
		//
		//
		board[6][0]=9;
		board[6][8]=2;
		//
		board[7][1]=8;
		board[7][3]=9;
		board[7][5]=1;
		board[7][7]=6;
		//
		board[8][1]=1;
		board[8][3]=4;
		board[8][4]=3;
		board[8][5]=6;
		board[8][7]=5;
		
		for(int i=0;i<board.length;i++)
		{
			for(int j=0;j<board[i].length;j++)
			{
				System.out.print(board[i][j]+"  ");
				if((j+1)%3==0)
					System.out.print(" ");
			}
			System.out.println();
			if((i+1)%3==0)
				System.out.println();
		}

	}
	static void fillTheMatrix3(int[][] board )
	{
		for(int i=0;i<board.length;i++)
			for(int j=0;j<board[i].length;j++)
				board[i][j]=0;

		board[0][2]=3;
		board[0][3]=9;
		board[0][7]=5;
		board[0][8]=1;
		//
		board[1][0]=5;
		board[1][1]=4;
		board[1][2]=6;
		board[1][4]=1;
		board[1][5]=8;
		board[1][6]=3;
		//
		board[2][5]=7;
		board[2][6]=4;
		board[2][7]=2;
		//
		board[3][2]=9;
		board[3][4]=5;
		board[3][7]=3;
		//
		board[4][0]=2;
		board[4][3]=6;
		board[4][5]=3;
		board[4][8]=4;
		//
		board[5][1]=8;
		board[5][4]=7;
		board[5][6]=2;
		//
		board[6][1]=9;
		board[6][2]=7;
		board[6][3]=3;
		//
		board[7][2]=1;
		board[7][3]=8;
		board[7][4]=2;
		board[7][6]=9;
		board[7][7]=4;
		board[7][8]=7;
		//
		board[8][0]=8;
		board[8][1]=5;
		board[8][5]=4;
		board[8][6]=6;
		
		for(int i=0;i<board.length;i++)
		{
			for(int j=0;j<board[i].length;j++)
			{
				System.out.print(board[i][j]+"  ");
				if((j+1)%3==0)
					System.out.print(" ");
			}
			System.out.println();
			if((i+1)%3==0)
				System.out.println();
		}

	}


	public void CreatFullTree(int hight , Node<TerminalOrPrimitive> node ) {
		int randNum;
		if(hight>1)
		{
			randNum=random.nextInt(Operators.size());
			node.creatLeft(new Primitive(Operators.get(randNum)));
			((Primitive)node.getValue()).setLeft(node.getLeft());

			CreatFullTree(hight-1 , node.getLeft());

			randNum=random.nextInt(Operators.size());
			node.creatRight(new Primitive(Operators.get(randNum)));
			((Primitive)node.getValue()).setRight(node.getRight());

			CreatFullTree(hight-1 , node.getRight());
		}
		else
		{
			if( hight==1)
			{
				// insert left value in random choos from the array list
				randNum=random.nextInt(functionNames.size());
				node.creatLeft(new Terminal(functionNames.get(randNum)));
				((Primitive)node.getValue()).setLeft(node.getLeft());

				randNum=random.nextInt(functionNames.size());
				node.creatRight(new Terminal(functionNames.get(randNum)));
				((Primitive)node.getValue()).setRight(node.getRight());

			}
		}

	}
	void printAsFunction (Node<TerminalOrPrimitive> node)
	{

		System.out.print( ""+node.getValue().getOperationName() + "");

		if (node.getLeft() != null)
		{
			System.out.print("( ");
			printAsFunction(node.getLeft());
			System.out.print( " , ");
		}


		if (node.getRight() != null)
		{
			printAsFunction(node.getRight());
			System.out.print(" )");
		}	
	}
	void printAsSequence(Node<TerminalOrPrimitive> node)
	{

		if (node.getLeft() != null)
		{
			System.out.print("(");
			printAsSequence(node.getLeft());	
		}

		System.out.print( " "+ConvertFromFunctionToOperator(node.getValue().getOperationName()) + " ");

		if (node.getRight() != null)
		{
			printAsSequence(node.getRight());
			System.out.print(")");
		}	
	}

	// constructor
	@SuppressWarnings("unchecked")
	public BoardIndividual(int hight , int[][] board ) {
		this.board=new int[size][size];
		this.gradeboard=new Dictionary [size][size];


		for(int i=0;i<size;i++)
		{
			for(int j=0;j<size;j++)
			{
				this.board[i][j]=board[i][j];
				this.gradeboard[i][j]=new Hashtable<Integer,Double>();
			}
		}

		this.hight = hight;
		individualTree=new Node<TerminalOrPrimitive>(new Primitive("Plus"));

		CreatFullTree(hight,individualTree );

		System.out.println("Print as a functions :");
		printAsFunction(individualTree);
		System.out.println("\n\nPrint as a sequence :");
		printAsSequence(individualTree);


		//System.out.println("\n\nthe result :  " + individualTree.getValue().Run(0, 0, 0, board, gradeboard));
		
		buldGradeboard();
		evaluateBoard();
		//printGradeBoard();
		
		play();
		printBoard();
	}
	@SuppressWarnings("unchecked")
	public void runWithOtherSoudoko(int[][] board )
	{
		this.board=new int[size][size];
		this.gradeboard=new Dictionary [size][size];


		for(int i=0;i<size;i++)
		{
			for(int j=0;j<size;j++)
			{
				this.board[i][j]=board[i][j];
				this.gradeboard[i][j]=new Hashtable<Integer,Double>();
			}
		}
		
		buldGradeboard();
		evaluateBoard();
		//printGradeBoard();
		
		play();
		printBoard();
		
	}

	boolean isForward()
	{
		for(int i=0;i<size;i++)
		{
			for(int j=0;j<size;j++)
			{
				if(!gradeboard[i][j].isEmpty())
					return true;
			}
		}
		return false;	
	}
	void play()
	{
		int fitness=0;
		while(isForward())
		{
			System.out.println( "i :  "+fitness);
			System.out.println("Empty cell : " + countEmptyCell(this.board));
			buldGradeboard();
			evaluateBoard();
			printBoard();
			printGradeBoard();
			
				double min=Double.MAX_VALUE;
				int x=-1,y=-1,Minkey=0;
				for(int i=0;i<size;i++)
				{
					for(int j=0;j<size;j++)
					{
						if(this.board[i][j]==0)
						{
							Enumeration<Integer> keys = this.gradeboard[i][j].keys();
							while (keys.hasMoreElements()) 
							{
								int key =  keys.nextElement();
								double tmpMin =this.gradeboard[i][j].get(key);
								if(tmpMin<min )
								{
									Minkey=key;
									x=i;
									y=j;
									min=tmpMin;

								}
							}
						}

					}
				}

				if(x!=-1 && y!=-1 && Minkey!=0){
					this.board[x][y]=Minkey;
					((Hashtable<Integer,Double>)gradeboard[x][y]).clear();
					fitness++;
					System.out.println("inserted : "+"["+x+"]"+"["+y+"]"+"="+Minkey);
				}
				
		}
		System.out.println("\n\nIn the End : ");
		System.out.println("the fitness : "+ fitness);
		System.out.println("Empty cell : " + countEmptyCell(this.board));
	}
	void printBoard()
	{
		for(int i=0;i<size;i++)
		{
			for(int j=0;j<size;j++)
			{
				System.out.print(board[i][j]+"  ");
				if((j+1)%3==0)
					System.out.print(" ");
			}
			System.out.println();
			if((i+1)%3==0)
				System.out.println();
		}
	}
	
	void printGradeBoard()
	{
		for(int i=0;i<size;i++)
		{
			for(int j=0;j<size;j++)
			{
				System.out.print("Index :"+ "["+(i)+"]" + "["+(j)+"]"+" : " );
				System.out.println(((Hashtable<Integer,Double>)gradeboard[i][j]));
			}
		}
		for(int i=0;i<size;i++)
		{
			for(int j=0;j<size;j++)
			{
				if(((Hashtable<Integer,Double>)gradeboard[i][j]).contains(0.0) 
						|| ((Hashtable<Integer,Double>)gradeboard[i][j]).containsValue(0.0))
				{
					System.out.println("there are a zero");
				}
			}
		}
		double min=Double.MAX_VALUE;
		for(int i=0;i<size;i++)
		{
			for(int j=0;j<size;j++)
			{
				Enumeration<Double> values = this.gradeboard[i][j].elements();
				while (values.hasMoreElements()) 
				{
					double value = (double) values.nextElement();
					if(min>value && value!=0)
						min=value;
				}
			}
		}
		System.out.println("Min values : ");
		for(int i=0;i<size;i++)
		{
			for(int j=0;j<size;j++)
			{
				if(((Hashtable<Integer,Double>)gradeboard[i][j]).contains(min) 
						|| ((Hashtable<Integer,Double>)gradeboard[i][j]).containsValue(min))
				{
					System.out.print("Index :"+ "["+(i)+"]" + "["+(j)+"]"+" : " );
					System.out.println(((Hashtable<Integer,Double>)gradeboard[i][j]));
				}
			}
		}
		
		
	}

	void evaluateBoard()
	{
		System.out.println("test");
		for(int i=0;i<size;i++)
		{
			for(int j=0;j<size;j++)
			{
				Enumeration<Integer> keys = this.gradeboard[i][j].keys();
				while (keys.hasMoreElements()) 
				{
					int key =  keys.nextElement();
					double value=this.individualTree.getValue().Run(i, j, key, board, gradeboard);
					this.gradeboard[i][j].put(key, value);
				}
			}
		}
	}
	void buldGradeboard()
	{
		for(int i=0;i<size;i++)
		{
			for(int j=0;j<size;j++)
			{
				if(this.board[i][j]==0)
				{
					this.gradeboard[i][j].put(1, Double.NaN);
					this.gradeboard[i][j].put(2, Double.NaN);
					this.gradeboard[i][j].put(3, Double.NaN);
					this.gradeboard[i][j].put(4, Double.NaN);
					this.gradeboard[i][j].put(5, Double.NaN);
					this.gradeboard[i][j].put(6, Double.NaN);
					this.gradeboard[i][j].put(7, Double.NaN);
					this.gradeboard[i][j].put(8, Double.NaN);
					this.gradeboard[i][j].put(9, Double.NaN);
				}
			}
		}
		for(int i=0;i<size;i++)
		{
			for(int j=0;j<size;j++)
			{
				for(int k=1;k<=this.size;k++)
				{
					if(CheckIfExist(i,j,k))
					{
						this.gradeboard[i][j].remove(k);
					}
				}
			}
		}
	}
	

	
	boolean CheckIfExist(int row,int col,int value)
	{
		for(int i=0;i<size;i++)
		{
			if(this.board[row][i]==value)
				return true;
		}
		for(int i=0;i<size;i++)
		{
			if(this.board[i][col]==value)
				return true;
		}
		if (row==0 || row==3 || row==6)
		{
			if (col==0 || col== 3 || col==6)
			{
				return(CheckIfExistInSmallSquare(row,col,value));
			}
			if (col==1 || col== 4 || col==7)
			{
				return(CheckIfExistInSmallSquare(row,col-1,value));
			}
			if (col==2 || col== 5 || col==8)
			{
				return(CheckIfExistInSmallSquare(row,col-2,value));
			}
		}
		if (row==1 || row==4 || row==7)
		{
			if (col==0 || col== 3 || col==6)
			{
				return(CheckIfExistInSmallSquare(row-1,col,value));
			}
			if (col==1 || col== 4 || col==7)
			{
				return(CheckIfExistInSmallSquare(row-1,col-1,value));
			}
			if (col==2 || col== 5 || col==8)
			{
				return(CheckIfExistInSmallSquare(row-1,col-2,value));
			}
		}
		if (row==2 || row==5 || row==8)
		{
			if (col==0 || col== 3 || col==6)
			{
				return(CheckIfExistInSmallSquare(row-2,col,value));
			}
			if (col==1 || col== 4 || col==7)
			{
				return(CheckIfExistInSmallSquare(row-2,col-1,value));
			}
			if (col==2 || col== 5 || col==8)
			{
				return(CheckIfExistInSmallSquare(row-2,col-2,value));
			}
		}
		
		System.out.println("Somthing Wrong in CheckIfExist");
		System.exit(0);
		return false;
	}
	boolean CheckIfExistInSmallSquare(int row ,int col,int value )
	{
		for(int i=row;i<row+3;i++)
		{
			for(int j=col;j<col+3;j++)
			{
				if(board[i][j]==value)
					return true;
			}
		}
		return false;
	}
	





	public static void main(String[] args) throws InterruptedException {

		GetBoard g=new GetBoard(0);
		g.PrintArray();
		/*
		// create function names in the array list		
		FillFunctionName();
		FillOperators();
		
	
		
		BoardIndividual mybord=null;
		int [][]board=new int[9][9];
		int [][]tmpBoard= new int [9][9];
		
		
		int emptyCell=-1,counter=0;
		while (emptyCell !=0 && counter<1000)
		{			
			fillTheMatrix1(board);
			mybord= new BoardIndividual(4,board);

			System.out.println("\nthe orginal : ");
			fillTheMatrix1(tmpBoard);
			System.out.println("num of empty cell : " + countEmptyCell(tmpBoard));

			emptyCell = countEmptyCell(mybord.getBoard());
			counter++;
		}
		if(counter>=1000)
		{
			System.out.println("cant solve this soudoko");
			System.exit(0);
		}

		System.out.println("succeced to solve after :"+ counter);
		System.out.println("\n\nNow we run the same indvidual on other soduko");
		for(int i=0;i<10;i++)
		{
			System.out.print(i+ " ");
			Thread.sleep(1000);
		}
		System.out.println();
		fillTheMatrix2(board);
		mybord.runWithOtherSoudoko(board);

		System.out.println("\nthe orginal : ");
		fillTheMatrix2(tmpBoard);
		System.out.println("num of empty cell : " + countEmptyCell(tmpBoard));
		emptyCell=countEmptyCell(mybord.getBoard());
		
		///////////////////////////////////////////////////////////////////////
		if(emptyCell==0)
		{
			System.out.println("succeced to solve the second soduko :");

			for(int i=0;i<20;i++)
			{
				System.out.print(i+ " ");
				Thread.sleep(1000);
			}
			System.out.println();
			fillTheMatrix(board);
			mybord.runWithOtherSoudoko(board);

			System.out.println("\nthe orginal : ");
			fillTheMatrix(tmpBoard);
			emptyCell=countEmptyCell(tmpBoard);
			System.out.println("num of empty cell : " + emptyCell);
		}*/
	}



	public int[][] getBoard() {
		return board;
	}

	@Override
	protected double evaluate() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public BoardIndividual mutate() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public BoardIndividual crossover(BoardIndividual other) {
		// TODO Auto-generated method stub
		return null;
	}





}