package assignment10;

import java.util.LinkedList;
import java.util.Queue;

import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Used to find the shortest path from S to G in a maze given the requirements
 * of the assignment
 * 
 * @author Harrison Smith
 * @author Ben Malohi
 * @date December 5, 2019
 *
 */
public class PathFinder {

	/*
	 * Instance variables
	 */
	private static final int NOT_SOLVABLE = -1;
	private static int distance;

	/**
	 * This method will find the shortest path from S to G in the txt input files.
	 * The output file, should contain the exact same graph, however, with marks of
	 * the shortest path
	 * 
	 * @param inputFile  - the file path to the input maze
	 * @param outputFile - the file path to the output maze
	 */
	@SuppressWarnings("resource")
	public static int solveMaze(String inputFile, String outputFile) {
		SimpleWriter writer = new SimpleWriter1L(outputFile);
		SimpleReader reader = new SimpleReader1L(inputFile);

		// retrieves dimension from first line
		String[] dimensions = reader.nextLine().split(" ");
		int height = Integer.parseInt(dimensions[0]);
		int width = Integer.parseInt(dimensions[1]);
		distance = 1;

		// instantiate the graph object
		Graph solvingGraph = new Graph(height, width);

		// used to keep record of where start is on graph
		int beginRow = -1, beginCol = -1;

		int currentRow = 0;

		while (!reader.atEOS()) {
			char[] line = reader.nextLine().toCharArray();

			int currentCol = 0;

			for (char val : line) {
				// location of node
				if (val == 'S') {
					beginRow = currentRow;
					beginCol = currentCol;
				}

				// add node to graph
				solvingGraph.setNode(currentRow, currentCol, new Node(val));
				currentCol++;
			}
			currentRow++;
		}

		// catch if no 'S'
		if (beginRow == -1) {
			System.out.println("No start node, cannot solve maze.");

			writer.print(solvingGraph.toString());

			reader.close();
			writer.close();
			return NOT_SOLVABLE;
		}

		// searches with bfs for shortest path
		boolean solvable = bfSearch(solvingGraph, beginRow, beginCol);

		if (solvable) {
			writer.print(solvingGraph.toString());

			reader.close();
			writer.close();
			return distance;
		}
		return NOT_SOLVABLE;
	}

	/**
	 * Helper method which will be a the search for the path
	 * 
	 * @param graph    - takes in the graph to search through
	 * @param beginRow - starting row
	 * @param beginCol - starting column
	 * @modifies graph - iff there is a path from S to G
	 */
	private static boolean bfSearch(Graph graph, int beginRow, int beginCol) {
		Queue<Node> que = new LinkedList<Node>();

		// get's first node
		Node start = graph.getNode(beginRow, beginCol);
		start.visted = true;
		que.offer(start);

		Node current = null;

		// work through the Queue until empty
		while (!que.isEmpty()) {
			// returns front of queue
			current = que.poll();

			// base case, goal found
			if (current.value == 'G')
				break;

			// if neighbors aren't walls and haven't been visited, we will set call enqueue
			// on the nodes
			Node left = graph.getLNeighbor(current);
			if (left.value != 'X' && !left.visted) {
				enqueue(left, current, que);
			}
			Node right = graph.getRNeighbor(current);
			if (right.value != 'X' && !right.visted) {
				enqueue(right, current, que);
			}
			Node up = graph.getUNeighbor(current);
			if (up.value != 'X' && !up.visted) {
				enqueue(up, current, que);
			}
			Node down = graph.getDNeighbor(current);
			if (down.value != 'X' && !down.visted) {
				enqueue(down, current, que);
			}
		}

		// if goal was not found, we don't change graph
		if (current.value == 'G') {
			// work backwards changing the bath iteratively
			for (Node node = current; node != null; node = node.prev) {
				if (node.value == ' ') {
					node.value = '.';
					distance++;
				}
			}
			return true;
		}
		// returns false it it isn't solvable
		return false;
	}

	/**
	 * Helper method, that will set visited to true and update the previous for the
	 * current node
	 * 
	 * @param node    - node in the direction we will go
	 * @param current - current node to be looked at
	 * @param que     - takes in a Queue of Nodes
	 * @modifies que
	 */
	private static void enqueue(Node node, Node current, Queue<Node> que) {
		node.prev = current;
		node.visted = true;
		que.offer(node);
	}

}