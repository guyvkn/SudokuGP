package sodukoGame;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Random;

public abstract class Individual implements Cloneable, Comparable<Object>, Variable {

	private static final int IDEAL_FITNESS = 0;
	private static final int NOT_PLAYED_YET =-1;

	private int fitness;
	protected Node<TerminalOrPrimitive> tree;
	private int hight;

	// static variables : 
	protected static ArrayList<String> functions= new  ArrayList<String>();
	protected static ArrayList<String> operators=new  ArrayList<String>();
	protected static Random random = new Random();
	
	
	protected abstract int evaluate();
	
	public Individual(int hight) {
		this.fitness =NOT_PLAYED_YET;
		setHight(hight);
		generateFullTree();
	}
	public void generateFullTree()
	{
		this.tree= new Node<TerminalOrPrimitive>();
		creatFullTree(this.hight, this.tree);
	}
	
	public void Re_generateFullTree(int hight)
	{
		setHight(hight);
		generateFullTree();
	}
	
	public void setHight(int hight) {
		if(hight<1)
			throw new Error("Somthing Wrong with the tree hight, cant's be less than 1");
		else
			this.hight = hight;
	}
	public int getHight() {
		return hight;
	}

	String treeAsPrefixExpression()
	{
		return ConvertTreeToPrefixExpression(this.tree);
	}
	String treeAsInfixExpression()
	{
		return ConvertTreeToInfixExpression(this.tree);
	}
	
	double Run(final int row,final int col,final int key, final int[][] board,final Hashtable<Integer,Double> [][]  gradeboard)
	{
		return tree.getValue().Run(row, col, key, board, gradeboard);
	}
	public int getFitness() {
		if (fitness==NOT_PLAYED_YET)
			fitness = evaluate();
		return fitness;
	}
	
	public boolean isIdeal() {
		return getFitness() == IDEAL_FITNESS;
	}


	@Override
	public int compareTo(Object obj) {
		Individual other = (Individual) obj;
		return new Integer( getFitness()).compareTo(new Integer(other.getFitness()));
	}
	@Override
	public String toString() {
		StringBuilder buf = new StringBuilder();
		buf.append("The tree as Prefix Sequence : \n");
		buf.append(treeAsPrefixExpression());
		buf.append("\n\n");
		//
		buf.append("The tree as Infix sequence : \n");
		buf.append(treeAsInfixExpression());
		return buf.toString();
	}

	public Node<TerminalOrPrimitive> getTree() {
		return tree;
	}
	Node<TerminalOrPrimitive> cloneFullTree()
	{
		return copyFullTree(this.tree);
	}
	
	@Override
	public Individual clone() {
		try {
			Individual copy = (Individual) super.clone();
			copy.setHight(hight);
			copy.tree=cloneFullTree();
			copy.fitness = NOT_PLAYED_YET;
			return copy;
			
		} catch (CloneNotSupportedException e) {
			throw new Error("Unexpected error While copying the indvidual", e);
		}
	}
	
	/*
	 * Be careful before using this function , this function is is calucate the tree hight
	 * in recourse way , we use when we do crossover between two tree 
	 * and in runtime we can calucate the hight of the new tree to update hight field
	 * this function is | experience |, we use it only when we do crossover.
	 */
	public int findHight()
	{
		return tree.findHight();
	}
	
	
	
	/*
	 * All   helping static function , all of them are recourse functions except 'copyFullTree' & 'creatFullTree'
	 * that run recourse functions , because it MUST create a root of tree BEFORE run 'copySubTree' , 
	 * and set a value for the root  BEFORE run 'creatFullTree' .
	 * 'copySubTree' & 'creatFullTree' is recourse functions .
	 * We use all those functions as a help function to deal with the indvidual tree.
	 */
	//------------------------------------static function --------------------------------------------

	
	/*
	 * 'creatSubTree' is recourse function that receive a hight and generate a full tree ,
	 * in recourse way , the tree is the invidual tree.
	 */
	public static void creatSubTree(int hight , Node<TerminalOrPrimitive> node ) {
		int randNum;
		if(hight>1)
		{
			randNum=random.nextInt(operators.size());
			node.creatLeft(new Primitive(operators.get(randNum)));
			((Primitive)node.getValue()).setLeft(node.getLeft());

			creatSubTree(hight-1 , node.getLeft());

			randNum=random.nextInt(operators.size());
			node.creatRight(new Primitive(operators.get(randNum)));
			((Primitive)node.getValue()).setRight(node.getRight());

			creatSubTree(hight-1 , node.getRight());
		}
		else
		{
			if( hight==1)
			{
				// insert left value in random choos from the array list
				randNum=random.nextInt(functions.size());
				node.creatLeft(new Terminal(functions.get(randNum)));
				((Primitive)node.getValue()).setLeft(node.getLeft());

				randNum=random.nextInt(functions.size());
				node.creatRight(new Terminal(functions.get(randNum)));
				((Primitive)node.getValue()).setRight(node.getRight());

			}
		}
	}
	
	
	/*
	 * 'ConvertTreeToOperationsSequence' function recive a root of tree and return the tree as
	 * a  | Prefix | expression in string variable , that can be printed.
	 */
	static String ConvertTreeToPrefixExpression(Node<TerminalOrPrimitive> node)
	{
		StringBuilder st = new StringBuilder(node.getValue().getOperationName());		

		if (node.getLeft() != null)
		{
			st.append("( ");
			st.append(ConvertTreeToPrefixExpression(node.getLeft()));
			//st.append(" , ");
			st.append("  ");
		}

		if (node.getRight() != null)
		{
			st.append(ConvertTreeToPrefixExpression(node.getRight()));
			st.append(" )");
		}
		return st.toString();
	}
	
	
	/*
	 * 'ConvertTreeToOperationsSequence' function recive a root of tree and return the tree as
	 * a  | Infix | expression in string variable , that can be printed.
	 */
	public static String ConvertTreeToInfixExpression(Node<TerminalOrPrimitive> node)
	{
		StringBuilder st = new StringBuilder();
		if (node.getLeft() != null)
		{
			st.append("(");
			st.append(ConvertTreeToInfixExpression(node.getLeft()));	
		}

		st.append(" "+HelpFunctions.ConvertFromFunctionToOperator(node.getValue().getOperationName()) + " ");

		if (node.getRight() != null)
		{
			st.append(ConvertTreeToInfixExpression(node.getRight()));
			st.append(")");
		}
		return st.toString();
	}
	
	
	
	/*
	 * 'copySubTree' is recourse function ,that recive source root and decision root .
	 * And copy all the source tree to the decision root in deep copying .
	 * if the decision root has a tree , the function will throw out all the tree except the root node 
	 */
	public static void copySubTree(final Node<TerminalOrPrimitive> sourceNode,Node<TerminalOrPrimitive> decisionNode ) {
		if(sourceNode!=null)
		{
			if(sourceNode.getLeft()!=null)
			{
				decisionNode.creatLeft(sourceNode.getLeft().getValue().clone());
				((Primitive)decisionNode.getValue()).setLeft(decisionNode.getLeft());
				
				copySubTree(sourceNode.getLeft(),decisionNode.getLeft());
			}
			
			if(sourceNode.getRight()!=null)
			{
				decisionNode.creatRight(sourceNode.getRight().getValue().clone());
				((Primitive)decisionNode.getValue()).setRight(decisionNode.getRight());
				
				copySubTree(sourceNode.getRight(),decisionNode.getRight());
			}
		}
	}
	
	
	/*
	 * 'copyFullTree' recive a source root and return a clone of the source root,
	 * the clone is cloned in a deep copy way.
	 * This function use 'copySubTree' recourse function .
	 * And we use this function in other method that called 'cloneFullTree()', that we using it in clone method
	 */
	public static Node<TerminalOrPrimitive> copyFullTree( Node<TerminalOrPrimitive> sourceNode) {
		if(sourceNode!=null)
		{
			Node<TerminalOrPrimitive>  decisionNode = new Node<TerminalOrPrimitive>(sourceNode.getValue().clone());
			copySubTree(sourceNode,decisionNode);
			return decisionNode;
		}
		else
			return null;	
	}
	
	
	/*
	 * 'creatFullTree' recive a root node and hight , must be NOT NULL , and generate random value for the root ,
	 * and then run 'creatSubTree' with root node and hight as a arguments .
	 * This function use 'creatFullTree' recourse function .
	 * And we use this function in other method that called 'generateFullTree()', and we using it for mution methon in 
	 * BoardIndividual and in other methods.
	 */
	public static void creatFullTree(int hight , Node<TerminalOrPrimitive> node ) {
		if(hight>0)
		{
			int randNum=random.nextInt(operators.size());
			node.setValue((new Primitive(operators.get(randNum))));
			// run recourse function .
			creatSubTree(hight, node);
		}
		else
		{
			if(hight==0)
			{
				int randNum=random.nextInt(functions.size());
				node.setValue(new Terminal(functions.get(randNum)));
				node.setLeft(null);
				node.setRight(null);
				System.out.println("they craate terminal here");
			}
			else
				throw new Error("You cant's create a tree with NEGATIVE hight");
		}
	}
	//--------------------------------------------------------------------------------------------------------------
	
}