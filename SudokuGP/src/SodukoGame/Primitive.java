package SodukoGame;

import java.util.Dictionary;

public class Primitive extends TerminalOrPrimitive {
	
	
	Node<TerminalOrPrimitive> left=null;
	Node<TerminalOrPrimitive> right=null;
	
	public Primitive(String operationName) {
		super("Primitive",operationName);
	}
	
	
	// set the pointer to the left son
	public void setLeft(Node<TerminalOrPrimitive> left) {
		this.left = left;
	}
	// set the pointer to the right son
	public void setRight(Node<TerminalOrPrimitive> right) {
		this.right = right;
	}


	// Recursion function that call calculate the operator result in a  Recursion way
	@Override
	double Run(int row, int col, int key, int[][] board,Dictionary <Integer,Double>[][] gradeboard) {
		
		switch (this.operationName){
		case "Plus":
			return 	Plus(left.getValue().Run(row, col, key,board,gradeboard)
					,right.getValue().Run(row, col, key,board,gradeboard));
			
		case "Minus":
			return Minus(left.getValue().Run(row, col, key,board,gradeboard)
					,right.getValue().Run(row, col, key,board,gradeboard));

		case "Multi":
			return Multi(left.getValue().Run(row, col, key,board,gradeboard)
					,right.getValue().Run(row, col, key,board,gradeboard));
			
		case "div":
			return div(left.getValue().Run(row, col, key,board,gradeboard)
					,right.getValue().Run(row, col, key,board,gradeboard));
			
		case "Mod":
			return Mod(left.getValue().Run(row, col, key,board,gradeboard)
					,right.getValue().Run(row, col, key,board,gradeboard));
			
			
		case "Maximum":
			return Maximum(left.getValue().Run(row, col, key,board,gradeboard)
					,right.getValue().Run(row, col, key,board,gradeboard));
			
		case "Minimum":
			return Minimum(left.getValue().Run(row, col, key,board,gradeboard)
					,right.getValue().Run(row, col, key,board,gradeboard));
			
		case "Random":
			return Minimum(left.getValue().Run(row, col, key,board,gradeboard)
					,right.getValue().Run(row, col, key,board,gradeboard));
			
		default :
			System.out.println("Somthing Wrong in the Primitives");
			System.exit(0);
			return 0;
		}
	}

	//**********************************************
	// all our operators that we support
	// plus operator
	double Plus(double leftValue,double rightValue)
	{
		return leftValue+rightValue;
	}
	// minus operator
	double Minus(double leftValue,double rightValue)
	{
		return Math.abs(leftValue-rightValue);
	}
	// multi operator
	double Multi(double leftValue,double rightValue)
	{
		return leftValue*rightValue;
	}
	// div operator
	double div(double leftValue,double rightValue)
	{
		if(rightValue!=0)
			return leftValue/rightValue;
		else
			return leftValue;
	}
	// mod operator
	double Mod(double leftValue,double rightValue)
	{
		if(rightValue!=0)
			return leftValue % rightValue;
		else
			return leftValue;
	}
	// maxumim operator
	double Maximum(double leftValue,double rightValue)
	{
		if(leftValue>=rightValue)
			return leftValue;
		else
			return rightValue;
	}
	// Minimum operator
	double Minimum(double leftValue,double rightValue)
	{
		if(leftValue<=rightValue)
			return leftValue;
		else
			return rightValue;
	}
}