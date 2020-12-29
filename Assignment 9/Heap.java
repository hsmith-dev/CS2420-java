package assignment09;

import java.util.Comparator;

import components.list.List;
import components.list.ListOnJavaArrayList;

/**
 * Implementation of a Binary Heap on array.
 * 
 * @author Harrison Smith and Ben Malohi
 * @date November 21, 2019
 *
 * @param <E> type of the element of the heap
 */
public class Heap<E> {
	private static final int INITIAL_CAPACITY = 1_500_000;

	private E[] rep;
	private Comparator<E> order;
	private int end;

	/**
	 * No argument constructor, builds an empty heap.
	 * 
	 * @ensures this is a heap
	 */
	public Heap() {
		clear();
		inferOrder();
	}

	/**
	 * Constructor from a comparator, builds an empty heap with the given ordering.
	 * 
	 * @param cmp comparator for the new heap
	 * @ensures this is a heap
	 */
	public Heap(Comparator<E> cmp) {
		clear();
		this.order = cmp;
	}

	/**
	 * Constructor from an array, builds a heap from the given array elements.
	 * 
	 * @param args elements to put in this heap
	 * @ensures this is a heap
	 */
	public Heap(E[] args) {
		this();
		for (int i = 0; i < args.length; i++) {
			rep[i] = args[i];
			end++;
		}
		heapify(0);
	}

	/**
	 * Infers the order based on the type of the elements, {@code E}. If
	 * {@code E extends Comparable}, it gets the order from that relation,
	 * otherwise, it tries to infer it by comparing the hashcodes of the two
	 * elements.
	 */
	private void inferOrder() {
		order = new Comparator<E>() {

			@SuppressWarnings("unchecked")
			@Override
			public int compare(E x, E y) {
				try {
					return ((Comparable<E>) x).compareTo(y);
				} catch (ClassCastException e) {
					return Integer.compare(x.hashCode(), y.hashCode());
				}
			}
		};
	}

	/**
	 * Converts the given complete binary tree into a heap.
	 * 
	 * @param start index of the root in the underlying array representation
	 * @ensures the rep is a heap
	 * @requires the rep is a complete binary tree
	 * @modifies this
	 */
	private void heapify(int start) {
		if (start >= end) {
			return;
		}

		heapify(start * 2 + 1);
		heapify(start * 2 + 2);
		siftDown(start);
	}

	/**
	 * Sifts the root of the tree down appropriately so that the resulting tree is a
	 * heap.
	 * 
	 * @param start index of the root in the underlying array representation
	 * @requires both left and right subtrees are heaps
	 * @ensures the rep is a heap
	 * @modifies this
	 */
	private void siftDown(int start) {
		if (rep[start] == null || start >= end) {
			return;
		}
		E parent = rep[start];
		int leftIndex = (start * 2) + 1;
		int rightIndex = (start * 2) + 2;
		if (leftIndex >= end)
			return;

		if (rightIndex < end) {
			// if parent is <= both children return
			if (order.compare(parent, rep[leftIndex]) <= 0 && order.compare(parent, rep[rightIndex]) <= 0)
				return;
			// if right > left
			else if (order.compare(rep[rightIndex], rep[leftIndex]) > 0) {
				swap(start, leftIndex);
				siftDown(leftIndex);
			}
			// if left > right
			else {
				swap(start, rightIndex);
				siftDown(rightIndex);
			}
		}
		// if right is null, but left is valid
		else if (order.compare(parent, rep[leftIndex]) > 0)
			swap(start, leftIndex);
	}

	private void siftUp(int start) {
		if (rep[start] == null)
			return;

		int parentIndex = (start - 1) / 2;

		if (order.compare(rep[start], rep[parentIndex]) < 0) {
			swap(start, parentIndex);
			siftUp(parentIndex);
		}
	}

	/**
	 * Helper method that swaps the {@code first} with {@code second}, resulting in
	 * second being on top
	 * 
	 * @param first  - index of item to move down
	 * @param second - index of item to move up
	 */
	private void swap(int first, int second) {
		E temp = this.rep[first];
		this.rep[first] = rep[second];
		this.rep[second] = temp;
	}

	/**
	 * Adds {@code x} to this maintaining the heap property.
	 * 
	 * @param x element to be added
	 * @modifies this
	 */
	public void add(E x) {
		rep[end] = x;
		siftUp(end);
		end++;
	}

	/**
	 * Removes and returns the first element from the heap.
	 * 
	 * @return the first element of this
	 * @requires this is not empty
	 * @modifies this
	 */
	public E removeFirst() {
		E temp = rep[0];
		delete(0);
		return temp;
	}

	/**
	 * Swaps the elements, deletes the element from the list, and adjusts the end
	 * and then calls heapify
	 * 
	 * @param i - index to look at
	 * @requires this is not empty
	 * @modify this
	 */
	private void delete(int i) {
		swap(0, end - 1);
		rep[end - 1] = null;
		end--;
		heapify(0);
	}

	/**
	 * Reports the number of elements in this heap.
	 * 
	 * @return number of elements in this
	 */
	public int size() {
		return end;
	}

	/**
	 * Change the order of this heap to the given one.
	 * 
	 * @param cmp new ordering relation
	 * @modifies this
	 */
	public void changeOrder(Comparator<E> cmp) {
		this.order = cmp;
		this.heapify(0);
	}

	/**
	 * Changes the heap to be empty with no items
	 */
	@SuppressWarnings("unchecked")
	public void clear() {
		rep = (E[]) new Object[INITIAL_CAPACITY];
		end = 0;
	}

	/**
	 * Puts the contents of this heap in a list in a sorted order and returns it,
	 * emptying the heap in the process.
	 * 
	 * @return contents of the heap in a sorted order
	 * @modifies this
	 */
	public List<E> sort() {
		List<E> list = new ListOnJavaArrayList<E>();
		// adds all the elements to the list
		for (int i = 0; i < end; i++) {
			list.add(removeFirst());
		}
		// returns sorted list
		return list;
	}

	/*
	 * Covers the Heap of type array to a String to be readable and printable
	 */
	@Override
	public String toString() {
		String str = "";
		// loops through array and adds it to a string
		for (int i = 0; i <= end; i++) {
			if (rep[i] != null)
				str += rep[i] + ",";
		}
		// removes the last comma
		if (str.length() > 0)
			str = str.substring(0, str.length() - 1);
		// returns str with brackets
		return "[" + str + "]";
	}

}
