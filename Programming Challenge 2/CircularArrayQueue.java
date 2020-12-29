package lab07;

import java.util.Arrays;

/**
 * {@code Queue} represented as a java array with wrap around indexes.
 *
 * @author Harrison Smith
 * @date October 4, 2019
 * 
 * @param <E> type of Queue elements
 *
 */
public class CircularArrayQueue<E> implements Queue<E> {
	/**
	 * Initial capacity of the array.
	 */
	private static final int INITIAL_CAPACITY = 10;

	/**
	 * Representation of {@code this} as an array of {@code E}s.
	 */
	private E[] rep;

	/**
	 * Indexes of the (pre-)front and rear element in {@code this}.
	 */
	private int preFront, rear;

	/**
	 * The logical size of {@code this}.
	 */
	private int currentSize;

	/**
	 * Expands the array 2x and places the elements starting at index 0.
	 */
	@SuppressWarnings("unchecked")
	private void expandArray() {
		E[] expandedArray = (E[]) new Object[rep.length * 2];
		int oldFront = preFront + 1;
		int newLocation = 0;
		while (newLocation < this.size()) {
			expandedArray[newLocation] = rep[oldFront];
			newLocation++;
			oldFront = incrementWithWrapAround(oldFront);
		}
		preFront = -1;
		rear = currentSize - 1;
		rep = expandedArray;
	}

	/**
	 * Internal method to increment the {@code index} with wrap-around.
	 *
	 * @param index index to be incremented
	 * @return {@code x+1} or {@code 0} if {@code x} is at the end of the array
	 */
	private int incrementWithWrapAround(int index) {
		return (index + 1) % rep.length;
	}

	/*
	 * Constructors -----------
	 */

	/**
	 * No-argument constructor - sets the private fields of the instance to the
	 * default values.
	 */
	@SuppressWarnings("unchecked")
	public CircularArrayQueue() {
		this.rep = (E[]) new Object[INITIAL_CAPACITY];
		this.preFront = this.rear = -1;
	}

	/*
	 * Queue methods -----------
	 */

	/**
	 * Adds an element to the queue and modify the size
	 */
	@Override
	public void enqueue(E x) {
		assert x != null : "Violation of: x is not null";
		rear = incrementWithWrapAround(rear);
		currentSize++;
		if (size() + 1 > rep.length)
			expandArray();
		rep[rear] = x;
	}

	/**
	 * Removes an element from the queue and modifies the size
	 */
	@Override
	public E dequeue() {
		assert this.size() > 0 : "Violation of: this is not empty";
		preFront = incrementWithWrapAround(preFront);
		E front = rep[preFront];
		currentSize--;
		return front;
	}

	/**
	 * Returns the size of the queue
	 */
	@Override
	public int size() {
		return this.currentSize;
	}

	/**
	 * Returns the element at the front of the queue
	 */
	@Override
	public E front() {
		assert this.size() > 0 : "Violation of: this is not empty";
		return rep[preFront + 1];
	}

	/**
	 * Will convert the CircularArrayQueue in a String
	 */
	@Override
	public String toString() {
		StringBuilder resultString = new StringBuilder();
		resultString.append("<");
		int front = preFront;
		while (front < rear) {
			front = incrementWithWrapAround(front);
			E element = this.rep[front];
			resultString.append(element.toString());
			if (front < rear) {
				resultString.append(",");
			}
		}
		resultString.append(">");
		return resultString.toString();
	}
}
