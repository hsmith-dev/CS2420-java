package assignment07;

/**
 * A binary search tree implementation from scratch.
 * 
 * @author Harrison Smith
 * @author Ben Malohi
 * @date October 31, 2019
 *
 */
public class BinarySearchTreeOfStrings {
	/**
	 * Node class that will be used specifically and only for
	 * BinarySearchTreeOfStrings
	 * 
	 * @author Harrison Smith - u0901395
	 * @author Ben Malohi - u1115837
	 * @date October 31, 2019
	 *
	 * @param <E>
	 */
	private static class Node<E> {
		/**
		 * No argument constructor
		 */
		public Node() {
			data = "";
			right = null;
			left = null;
		}

		/**
		 * Constructor that creates the data within the node
		 * 
		 * @param x
		 */
		public Node(String x) {
			data = x;
			right = null;
			left = null;
		}

		/**
		 * Instance variables
		 */
		private String data;
		private Node<String> left;
		private Node<String> right;
	}

	/**
	 * Instance variables
	 */
	private int size;
	private Node<String> root;

	/**
	 * No argument constructor.
	 */
	public BinarySearchTreeOfStrings() {
		clear();
	}

	/**
	 * Removes all the nodes in this tree.
	 * 
	 * @modifies this tree
	 */
	public void clear() {
		root = new Node<>();
		size = 0;
	}

	/**
	 * Inserts the element {@code x} at the appropriate position in this tree.
	 * 
	 * @param x element to be inserted
	 * @modifies this tree
	 */
	public void insert(String x) {
		// takes care of base case
		if (size == 0) {
			root = new Node<>(x);
			size++;
		}
		// if not base case, will move to helper to add
		else if (contains(x))
			return;
		else
			insertion(x, root);
	}

	/**
	 * Removes the element {@code x} from this tree.
	 * 
	 * @param x element to be removed
	 * @modifies this tree
	 * @requires {@code x} is in {@code this}
	 */
	public void remove(String x) {

		// Stores Node of {@code x}
		Node<String> nodeRem = containsSearch(x, root);

		// Does not exist in tree
		if (nodeRem == null)
			return;

		// stores num of children of node
		int numChildren = 0;
		if (nodeRem.left != null)
			numChildren++;
		if (nodeRem.right != null)
			numChildren++;

		// based on num of children, it will choose the type of deletion
		switch (numChildren) {
		case 0:
			removeNoChild(nodeRem);
			break;
		case 1:
			removeWithOneChild(nodeRem);
			break;
		case 2:
			removeWithTwoChildren(nodeRem);
			break;
		}

		// remove(x, root);
		size--;
	}

	/**
	 * Reports the number of nodes in this tree.
	 * 
	 * @return size of {@code this}
	 */
	public int size() {
		return size;
	}

	/**
	 * Reports whether this tree contains {@code x}.
	 * 
	 * @param x element to search
	 * @return true iff this contains x
	 */
	public boolean contains(String x) {
		// base case
		if (size == 0)
			return false;
		// creates new node, and uses private to check
		Node<String> node = containsSearch(x, root);
		// checks to see if the node passed {@code x} exists
		return node != null;
	}

	/**
	 * Returns the root of this tree.
	 * 
	 * @return root of this tree
	 * @requires this is not empty
	 */
	public String root() {
		return root.data;
	}

	@Override
	public String toString() {
		StringBuilder sB = new StringBuilder();
		// calls helper method
		inOrder(root, sB);
		return "[" + sB.toString() + "]";
	}

	/*
	 * Helper methods
	 */

	/**
	 * The node to delete is a child; we can remove the reference from it's parent
	 * to delete it
	 * 
	 * @param node - the node that is a child node
	 */
	private void removeNoChild(Node<String> node) {
		node.data = null;
	}

	/**
	 * The node to delete has 1 child, we must replace it with the 1 child; will
	 * move the child and all it's children up
	 * 
	 * @param node - the node to remove
	 */
	private void removeWithOneChild(Node<String> node) {
		// needs to be handled differently since there is no parent, and it is root node
		if (node == root) {
			if (node.left != null) {
				root = root.left;
			} else {
				root = root.right;
			}
		}

		else {
			// remove with a left child
			if (node.left != null) {
				node.data = null;
				node = node.left;
			}
			// remove with right child, no left child
			else if (node.right != null) {
				// remove node with right child, but right child does not have left child
				if (node.right.left == null) {
					node.data = null;
					node = node.right;
				}
				// remove node with right child node, and right child node has left child node
				else if (node.right.left != null) {
					// finds the replacement within the right subtree, all the way left
					Node<String> replace = findReplacement(node);
					// deletes the data in node
					node.data = null;
					// removes the right subtree's all the way left node
					remove(replace.data);
					// replaces the node data 
					node = replace;
				}
			}
		}
	}

	/**
	 * THe node to delete has 2 children. We find the replacement then replace and
	 * delete.
	 * 
	 * @param node - to delete
	 */
	private void removeWithTwoChildren(Node<String> node) {
		// gets replacement and calls helper
		Node<String> replacement = findReplacement(node);
		// replaces {@code node} data with {@code replacement} data
		node.data = replacement.data;

		// delete the replacement's original location
		if (replacement.right == null)
			// delete replacement if no child
			removeNoChild(replacement);
		// delete replacement if 1 child
		else
			removeWithOneChild(replacement);

	}

	/**
	 * Returns from right subtree, the left most node of {@code node}
	 * 
	 * @param node - to find it's replacement that will be replaced
	 * @return the node that fits the above criteria
	 */
	private Node<String> findReplacement(Node<String> node) {
		Node<String> replacement = node.right;
		while (replacement.left != null)
			replacement = replacement.left;
		return replacement;
	}

	/**
	 * Recursive helper method that takes a node as an argument and performs the in
	 * order algorithm recursively.
	 * 
	 * @param Node e - Some node to perform the recursive algorithm at
	 */
	private void inOrder(Node<String> node, StringBuilder sB) {
		if (node == null) {
			return;
		}

		if (node.left != null)
			inOrder(node.left, sB);

		if (node.data != null) {
			// checks for the first time it adds something, so there isn't a comma at the
			// start
			if (sB.length() == 0) {
				sB.append(node.data);
			} else {
				sB.append("," + node.data);
			}
		}

		if (node.right != null)
			inOrder(node.right, sB);
	}

	/**
	 * Recursive helper method that inserts the {@code x} starting to look at
	 * {@code node}
	 * 
	 * @param x    - String to insert
	 * @param node - Node to start at to find location
	 */
	private void insertion(String x, Node<String> node) {
		// if {@code x} is less than {@code node.data} then it will check the left
		if (x.compareTo(node.data) < 0) {
			// if {@code node.left} has a left it will recursively call this method
			if (node.left != null)
				insertion(x, node.left);
			// then we found were {@code x} is to be inserted, changes the size, and updates
			// the {@code node} references
			else {
				node.left = new Node<>(x);
				size++;
			}
		}
		// if {@code x} is greater than {@code node.data} then it will check the right
		if (x.compareTo(node.data) > 0) {
			// if {@code node.right} has a right it will recursively call this method
			if (node.right != null)
				insertion(x, node.right);
			// then we found were {@code x} is to be inserted, changes the size, and updates
			// the {@code node} references
			else {
				node.right = new Node<>(x);
				size++;
			}
		}
	}

	/**
	 * Recursive helper method that searches to see if the BST contains that
	 * {@code x} starting at {@code node}
	 * 
	 * @param x    - String to find
	 * @param node - Node to start at
	 * @return if {@code x} is found below {@code node}
	 */
	private Node<String> containsSearch(String x, Node<String> node) {
		// checks the bode to the left
		if (x.compareTo(node.data) < 0) {
			// recursively calls this method if {@code x} is less
			if (node.left != null)
				return containsSearch(x, node.left);
			// {@code x} is not within tree
			else
				return null;
		}

		// checks the node to the right
		if (x.compareTo(node.data) > 0) {
			// recursively calls this method if {@code x} is greater
			if (node.right != null)
				return containsSearch(x, node.right);
			// {@code x} is not within tree
			else
				return null;
		}

		// if current node if it is contains at that node
		return node;
	}

}
