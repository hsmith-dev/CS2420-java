package assignment10;

/**
 * Graph Object that stores Nodes of various types to be used in
 * PathFinder.java.
 * 
 * @author Harrison Smith
 * @author Ben Malohi
 * @date December 5, 2019
 *
 */
public class Graph {
	private Node data[][];
	public final int height;
	public final int width;

	/**
	 * Argument Constructor, filled with Node of ' '
	 * 
	 * @param rows - Num of rows
	 * @param cols - Num of columns
	 */
	public Graph(int rows, int cols) {
		data = new Node[rows][cols];

		height = rows;
		width = cols;

		// fills data with empty nodes
		for (int r = 0; r < rows; r++)
			for (int c = 0; c < cols; c++) {
				Node node = new Node(' ');
				node.row = r;
				node.col = c;
				data[r][c] = node;
			}
	}

	/**
	 * Replaced the Node {@code row, col} with 'node'
	 * 
	 * @param row  Row of Node to replace
	 * @param col  column of Node to replace
	 * @param node Node that will replace Node {@code row, col}
	 */
	public void setNode(int row, int col, Node node) {
		data[row][col] = node;
		node.row = row;
		node.col = col;
	}

	/**
	 * Returns Node at location of {@code row, col}
	 * 
	 * @param row Row of Node to retrieve
	 * @param col Col of Node to retrieve
	 * @return node at location
	 */
	public Node getNode(int row, int col) {
		return data[row][col];
	}

	/**
	 * Returns neighbor that is above Node
	 * 
	 * @param node - Node Object
	 * @return up node
	 */
	public Node getUNeighbor(Node node) {
		if (node.row - 1 < 0 || node.row - 1 > data.length - 1)
			throw new IndexOutOfBoundsException();
		return data[node.row - 1][node.col];
	}

	/**
	 * Returns neighbor that is below Node
	 * 
	 * @param node - Node Object
	 * @return down node
	 */
	public Node getDNeighbor(Node node) {
		if (node.row + 1 < 0 || node.row + 1 > data.length - 1)
			throw new IndexOutOfBoundsException();
		return data[node.row + 1][node.col];
	}

	/**
	 * Returns neighbor that is left of Node
	 * 
	 * @param node - Node Object
	 * @return left node
	 */
	public Node getLNeighbor(Node node) {
		if (node.col - 1 < 0 || node.col - 1 > data[0].length - 1)
			throw new IndexOutOfBoundsException();
		return data[node.row][node.col - 1];
	}

	/**
	 * Returns neighbor that is right of Node
	 * 
	 * @param node - Node Object
	 * @return right node
	 */
	public Node getRNeighbor(Node node) {
		if (node.col + 1 < 0 || node.col + 1 > data[0].length - 1)
			throw new IndexOutOfBoundsException();
		return data[node.row][node.col + 1];
	}

	/**
	 * Converts the graph to a string
	 */
	@Override
	public String toString() {
		String s = height + " " + width + "\n";
		for (int row = 0; row < data.length; row++) {
			for (int col = 0; col < data[0].length; col++) {
				s += data[row][col].value;
			}
			s += "\n";
		}
		return s;
	}

	/**
	 * Returns the data stored in Graph, used only for testing
	 * 
	 * @return data
	 */
	protected Node[][] toArray() {
		return data;
	}
}
