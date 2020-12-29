package assignment10;

/**
 * Node Object which holds info for Graph Object Class, will be used in
 * PathFinder.java's BFS method
 * 
 * @author Harrison Smith - u0901395
 * @author Ben Malohi - u1115837
 * @date December 5, 2019
 *
 */
public class Node {

	/**
	 * Instance Variables
	 */
	public boolean visted;
	public Node prev;
	public char value;
	protected int row, col;

	/**
	 * No-argument Constructor, sets variable to empty char
	 */
	public Node() {
		this(' ');
	}

	/**
	 * Argument Constructor
	 * 
	 * @param value - value being set when instantiated
	 */
	public Node(char value) {
		visted = false;
		prev = null;
		this.value = value;
	}
}
