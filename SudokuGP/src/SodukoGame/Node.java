package SodukoGame;

class Node<T> {
    private Node<T> left;
    private Node<T> right;
    private T  value;

    
	public Node(Node<T> left, Node<T> right, T value) {
		super();
		this.left = left;
		this.right = right;
		this.value = value;
	}
	public Node(T value) {
		super();
		this.left = null;
		this.right = null;
		this.value = value;
	}
	public Node(){
		super();
	}
	
	
	public Node<T> getLeft() {
		return left;
	}
	public void setLeft(Node<T> left) {
		this.left = left;
	}
	public Node<T> getRight() {
		return right;
	}
	public void setRight(Node<T> right) {
		this.right = right;
	}
	public T getValue() {
		return value;
	}
	public void setValue(T value) {
		this.value = value;
	}

	////////////////////////////////////////
	public Node<T> creatLeft(T leftValue) {
		this.left=new Node<T>(leftValue);
		return this.left;
	}
	public Node<T> creatRight(T RightValue) {
		this.right=new Node<T>(RightValue);
		return this.right;
	}

	

	
    
}
