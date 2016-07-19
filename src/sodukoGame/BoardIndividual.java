package sodukoGame;

import java.util.Enumeration;
import java.util.Hashtable;


public class BoardIndividual extends Individual {

	/*
	 * orginalSoducko : reference is copied during clone - shallow copy
	 * it's very imprtant to NOT change the reference while the Evolution process ,
	 * becase we using in clone function while creating the new Generation ,exucly before the crosover and mution.
	 */
	final int[][] orginalSoducko ;	
	final int size=9;
	int[][] board ;				// in clone function we deep copy this board
	Hashtable<Integer,Double> [][]  gradeboard;	// in clone we creating from scratch

	// constructor
	@SuppressWarnings("unchecked")
	public BoardIndividual(int hight , int[][] board ) {
		super(hight);
		this.orginalSoducko=board;
		this.board=new int[size][size];
		this.gradeboard=new Hashtable [size][size];
		
		for(int i=0;i<size;i++)
		{
			for(int j=0;j<size;j++)
			{
				this.board[i][j]=board[i][j];
				this.gradeboard[i][j]=new Hashtable<Integer,Double>();
			}
		}
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
	int play()
	{
		initializeGradeboard();
		int fitness=countEmptyCellInSudoku();
		while(isForward())
		{
			evaluateGradeboard();

			double min=Double.MAX_VALUE;
			int x=-1,y=-1,Minkey=0;

			for(int i=0;i<size;i++)
			{
				for(int j=0;j<size;j++)
				{
					if(!gradeboard[i][j].isEmpty()  && board[i][j]==0)
					{
						Enumeration<Integer> keys = this.gradeboard[i][j].keys();
						while (keys.hasMoreElements()) 
						{
							int key =  keys.nextElement();
							double tmpMin =this.gradeboard[i][j].get(key);
							if(tmpMin<min)
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
				gradeboard[x][y].clear();
				fitness--;
			}
			initializeGradeboard();
		}
		return fitness;
	}
	void evaluateGradeboard()
	{
		for(int i=0;i<size;i++)
		{
			for(int j=0;j<size;j++)
			{
				Enumeration<Integer> keys = this.gradeboard[i][j].keys();
				while (keys.hasMoreElements()) 
				{
					int key =  keys.nextElement();
					double value=Run(i, j, key, board, gradeboard);
					this.gradeboard[i][j].put(key, value);
				}
			}
		}
	}
	void initializeGradeboard()
	{
		for(int i=0;i<size;i++)
		{
			for(int j=0;j<size;j++)
			{
				if(this.board[i][j]==0)
				{
					for(int k=1;k<=this.size;k++)
					{
						this.gradeboard[i][j].put(k,Double.NaN);
					}
				}
			}
		}
		for(int i=0;i<size;i++)
		{
			for(int j=0;j<size;j++)
			{
				if(!(gradeboard[i][j].isEmpty()))
				{
					for(int k=1;k<=this.size;k++)
					{
						if(existInRowColSquare(i,j,k))
							this.gradeboard[i][j].remove(k);
					}
				}
			}
		}
	}
	
	boolean existInRowColSquare(int row,int col,int num)
	{
		return Terminal.existInRowAndColAndSquare(row, col, num, this.board);
	}
	void printSudokuBoard()
	{
		HelpFunctions.printSudokuBoard(this.board);
	}
	void printGradeBoard()
	{
		HelpFunctions.printGradeBoard( this.gradeboard);
	}
	int countEmptyCellInSudoku()
	{
		return HelpFunctions.countEmptyCellInSudoku(this.board);
	}
	int countEmptyCellInOrginalSudoku()
	{
		return HelpFunctions.countEmptyCellInSudoku(this.orginalSoducko);
	}
	public int[][] getBoard() {
		return board;
	}
	
	@Override
	protected int evaluate() {
		return play();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public BoardIndividual clone() {
		BoardIndividual copy = (BoardIndividual) super.clone();
		copy.board=new int[copy.size][copy.size];
		copy.gradeboard=new Hashtable [copy.size][copy.size];
		for(int i=0;i<copy.size;i++)
		{
			for(int j=0;j<copy.size;j++)
			{
				copy.board[i][j]=copy.orginalSoducko[i][j];
				copy.gradeboard[i][j]=new Hashtable<Integer,Double>();
			}
		}
		return copy;
	}
	
	@Override
	public Individual mutate() {
		BoardIndividual copy = clone();
		int treeHight=copy.tree.findHight();
		int changeInDeep=HelpFunctions.randomNumberFromInclusiveToExclusive(1,treeHight);
		int deep;
		Node<TerminalOrPrimitive> mover=copy.tree;
		Node<TerminalOrPrimitive> parent=mover;
		
		for (deep = 0; deep < changeInDeep && (mover.getValue().isPrimitive()); deep++) {
			parent=mover;
			if(Math.random()<0.5)
				mover=mover.getLeft();
			else
				mover=mover.getRight();
		}
		creatFullTree(treeHight-deep,mover);
		((Primitive)parent.getValue()).setLeft(parent.getLeft());
		((Primitive)parent.getValue()).setRight(parent.getRight());
		return copy;
	}
	
	@Override
	public Individual crossover(Individual object) {
		BoardIndividual copy = clone();
		BoardIndividual other=(BoardIndividual) object;
		if(Math.random()<0.5)
		{
			if(Math.random()<0.5)
				copy.tree.setRight(copyFullTree(other.tree.getRight()));
			else
				copy.tree.setRight(copyFullTree(other.tree.getLeft()));
			
			((Primitive)copy.tree.getValue()).setRight(copy.tree.getRight());
		}
		else
		{
			if(Math.random()<0.5)
				copy.tree.setLeft(copyFullTree(other.tree.getRight()));
			else
				copy.tree.setLeft(copyFullTree(other.tree.getLeft()));
			
			((Primitive)copy.tree.getValue()).setLeft(copy.tree.getLeft());
		}
		
		copy.setHight(copy.findHight());
		return copy;
	}
	
	
	/*
	// another way to do crossover
	@Override
	public Individual crossover(Individual object) {
		BoardIndividual copy = clone();
		BoardIndividual other=(BoardIndividual) object;
		
		copy.tree.setRight(copyFullTree(other.tree.getRight()));
		((Primitive)copy.tree.getValue()).setRight(copy.tree.getRight());
		
		copy.setHight(copy.findHight());
		return copy;
	}
	*/
	@Override
	public String toString() {
		StringBuilder buf = new StringBuilder("Individual : \n\n");
		
		int orginalEmptyCell=countEmptyCellInOrginalSudoku();
		int currentEmptyCell=countEmptyCellInSudoku();
		if(orginalEmptyCell==currentEmptyCell)
			buf.append("this indvidual property not played\n\n");
		else
		{
			if(currentEmptyCell<orginalEmptyCell)
			{
				buf.append("Solve = "+(orginalEmptyCell-currentEmptyCell)+" / "+orginalEmptyCell+"\n");
				buf.append("Left = " + currentEmptyCell+"\n\n");
			}
			else
				buf.append("Somthing Wrong in playing function\n");
		}
		for(int i=0;i<board.length;i++)
		{
			for(int j=0;j<board[i].length;j++)
			{
				buf.append(board[i][j]+" ");
				if((j+1)%3==0)
					buf.append("  ");
			}
			buf.append("\n");
			if((i+1)%3==0)
				buf.append("\n");
		}
		buf.append(super.toString());
		return buf.toString();
	}

}