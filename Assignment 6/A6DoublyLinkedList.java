package assignment06;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import components.list.DoublyLinkedList;

/**
 *
 * @author Samuel Hancock
 * @author Harrison Smith
 *
 * @param <E>
 */
public class A6DoublyLinkedList<E> extends DoublyLinkedList<E> {

	private Node<E> head;

	private static final int NOT_FOUND = -1;

	private int sizeOfList;

	private int changes = 0;

	private Node<E> tail;

	/**
	 * Class to be used within the A6DoublyLinkedList to reference each data
	 * location and what is stored there
	 * 
	 * @author Harrison Smith
	 * @author Samuel Hancock
	 * @param <E>
	 */
	private static class Node<E> {
		public Node() {
			next = null;
			prev = null;
		}

		public Node(E x) {
			next = null;
			prev = null;
			data = x;
		}

		// instance variables
		private Node<E> prev;
		private Node<E> next;
		private E data;
		// end of instance variables
	}

	private class DoublyLinkedListIterator implements Iterator<E> {

		// instance variables
		private Node<E> current;

		private int expectedModCount;
		// end of instance variables

		// sets values and creates an iterator
		DoublyLinkedListIterator() {
			current = head.next;
			expectedModCount = changes;
		}

		/**
		 * Determines if there is more in the list
		 */
		@Override
		public boolean hasNext() {
			if (expectedModCount != changes) {
				throw new ConcurrentModificationException();
			}
			return current != tail;
		}

		/**
		 * Moves to the next index in the list
		 */
		@Override
		public E next() {
			if (!this.hasNext())
				throw new NoSuchElementException();
			E hold = current.data;
			current = current.next;
			return hold;
		}

	}

	// clears the list, setting it up
	A6DoublyLinkedList() {
		clear();

	}

	/**
	 * Finds the index of an element
	 */
	@Override
	public int indexOf(E x) {
		assert x != null : "Violation of: x is not null";

		Node<E> p = head.next;
		int result = NOT_FOUND;
		int i = 0;
		while (p != tail) {
			if (p.data.equals(x)) {
				result = i;
				break;
			}
			i++;
			p = p.next;
		}
		return result;
	}

	/**
	 * Clears the list, making the pointers of head and tail point to each other
	 */
	@Override
	public void clear() {

		head = new Node<>();
		tail = new Node<>();
		head.next = tail;
		tail.prev = head;

		head.prev = null;
		tail.next = null;

		sizeOfList = 0;
		changes++;

	}

	/**
	 * Helper method to improve remove - removes if it is on the first half of the
	 * list
	 * 
	 * @param index of what will be excluded
	 * @return the element removed
	 */
	private E removeFromFrontHalf(int index) {
		Node<E> p = head;
		for (int i = 0; i < index; i++)
			p = p.next;
		Node<E> hold = p.next;
		p.next = hold.next;
		hold.next.prev = p;

		sizeOfList--;
		changes++;
		return hold.data;
	}

	/**
	 * Helper method to remove from the second half of the list, greater than size/2
	 * 
	 * @param index of element to be excluded
	 * @return the element excluded
	 */
	private E removeFromLastHalf(int index) {
		Node<E> p = tail;
		for (int i = this.size(); i > index + 1; i--)
			p = p.prev;
		Node<E> hold = p.prev;
		p.prev = hold.prev;
		hold.prev.next = p;

		sizeOfList--;
		changes++;
		return hold.data;
	}

	/**
	 * Removes an element from the list at the {@code index}
	 * 
	 * @returns the element that was removed at index
	 */
	@Override
	public E remove(int index) {
		assert 0 <= index : "Violation of: 0 <= index";
		assert index < this.size() : "Violation of: index < this.size()";

		if (index > this.size() / 2)
			return removeFromLastHalf(index);
		else
			return removeFromFrontHalf(index);
	}

	@Override
	/**
	 * returns the size of the list
	 */
	public int size() {
		return sizeOfList;
	}

	@Override
	/**
	 * Iterator to be used to loop through the list
	 */
	public Iterator<E> iterator() {
		return new DoublyLinkedListIterator();
	}

	@Override
	/**
	 * Gets the element at an index and returns it
	 * 
	 * @return element at index
	 */
	public E get(int index) {
		assert 0 <= index : "Violation of: 0 <= index";
		assert index < this.size() : "Violation of: index < this.size()";

		Node<E> p = head.next;
		for (int i = 0; i < index; i++)
			p = p.next;

		return p.data;
	}

	/**
	 * Helper method to add if it is on left side of size/2
	 * 
	 * @param index to add to
	 * @param x     element to be added
	 * @param n     node reference
	 */
	private void addLessMiddle(int index, E x, Node<E> n) {
		Node<E> p = head;
		for (int i = 0; i < index; i++)
			p = p.next;

		n.prev = p;
		n.next = p.next;

		p.next.prev = n;
		p.next = n;

		sizeOfList++;
		changes++;
	}

	/**
	 * Helper method to add if it is on right side of size/2
	 * 
	 * @param index to add to
	 * @param x     element to be added
	 * @param n     node reference
	 */
	private void addMiddleOrGreater(int index, E x, Node<E> n) {
		Node<E> p = tail;
		for (int i = this.size(); i > index; i--)
			p = p.prev;

		n.next = p;
		n.prev = p.prev;

		p.prev.next = n;
		p.prev = n;

		sizeOfList++;
		changes++;
	}

	@Override
	/**
	 * Adds an element to the list
	 * 
	 * @param x     element to be added to list
	 * @param index where to add element
	 */
	public void add(int index, E x) {
		assert 0 <= index : "Violation of: 0 <= index";
		assert index <= this.size() : "Violation of: index < this.size()";
		assert x != null : "Violation of: x is not null";

		Node<E> n = new Node<>(x);

		// determines which method to use
		if (index < this.size() / 2)
			addLessMiddle(index, x, n);

		if (index >= this.size() / 2)
			addMiddleOrGreater(index, x, n);
	}

	@Override
	/**
	 * Overrides so it will use this method's remove
	 */
	public void add(E x) {
		assert x != null : "Violation of: x is not null";

		this.add(this.size(), x);
	}

	@Override
	/**
	 * Converts the list to a String
	 */
	public String toString() {
		Iterator<E> i = this.iterator();
		StringBuilder s = new StringBuilder();
		s.append("[");

		while (i.hasNext()) {
			s.append(i.next() + ",");
		}

		if (this.size() > 0)
			s.deleteCharAt(s.length() - 1);

		s.append("]");

		return s.toString();

	}

	@Override
	/**
	 * Inverts the string so the tail data is where it will start and head is where
	 * it will end while keeping head and tail in approaprate locations
	 */
	public void reverse() {

		Node<E> forwardIndex = head.next;
		Node<E> revIndex = tail.prev;

		for (int i = 0; i < this.size() / 2; i++) {

			E front = forwardIndex.data;
			E back = revIndex.data;

			forwardIndex.data = back;
			revIndex.data = front;

			forwardIndex = forwardIndex.next;
			revIndex = revIndex.prev;

		}

	}

}
