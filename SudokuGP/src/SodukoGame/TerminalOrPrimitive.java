package SodukoGame;

import java.util.Dictionary;

public abstract class TerminalOrPrimitive {
	
	protected final String nodeTybe; // Nodetybe is a string that set if the node is a Terminal Or Primitive 
	protected final String operationName; // the function or operator that should run
	abstract double Run(int row,int col,int value, int[][] board,Dictionary <Integer,Double>[][] gradeboard); // this function is doing the cal of the tree in the future
	public TerminalOrPrimitive(String nodeTybe,String operationName) {
		this.nodeTybe = nodeTybe;	
		this.operationName=operationName;
	}
	public String getNodeTybe() {
		return nodeTybe;
	}
	public String getOperationName() {
		return operationName;
	}
	

}
