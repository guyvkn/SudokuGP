package sodukoGame;

import java.util.Hashtable;

public abstract class TerminalOrPrimitive implements Cloneable{
	
	protected final String nodeTybe; // Nodetybe is a string that set if the node is a Terminal Or Primitive 
	protected String operationName; // the function or operator that should run
	
	// this function is doing the cal of the tree in the future
	abstract double Run(final int row,final int col,final int key, final int[][] board,final Hashtable<Integer,Double> [][]  gradeboard); 
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
	
	@Override
	protected TerminalOrPrimitive clone() {
		try {
			return (TerminalOrPrimitive) super.clone();
		} catch (CloneNotSupportedException e) {
			throw new Error("Unexpected error", e);
		}
	}
	public boolean isTerminal()
	{
		return this.nodeTybe=="Terminal";
	}
	public boolean isPrimitive()
	{
		return this.nodeTybe=="Primitive";
	}
	
	

}
