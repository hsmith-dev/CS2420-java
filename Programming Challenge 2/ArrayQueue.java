package lab07;

/**
 * {@code Queue} represented as a java array.
 *
 * @author Harrison Smith
 * @date October 4, 2019
 * 
 * @param <E> type of Queue elements
 *
 */
public class ArrayQueue<E> implements Queue<E> {
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
	 * Expands the array 2x.
	 */
	@SuppressWarnings("unchecked")
	private void expandArray() {
		E[] temp = this.rep;
		this.rep = (E[]) new Object[temp.length * 2];
		for (int i = 0; i < temp.length; i++) {
			this.rep[i] = temp[i];
		}
	}

	/*
	 * Constructors -----------
	 */

	/**
	 * No-argument constructor.
	 */
	@SuppressWarnings("unchecked")
	public ArrayQueue() {
		this.rep = (E[]) new Object[INITIAL_CAPACITY];
		this.preFront = this.rear = -1;
	}

	/*
	 * Primary methods -----------
	 */

	@Override
	public void enqueue(E x) {
		assert x != null : "Violation of: x is not null";

		this.rear++;
		if (this.rear >= this.rep.length) {
			expandArray();
		}
		this.rep[this.rear] = x;
	}

	@Override
	public E dequeue() {
		assert this.size() > 0 : "Violation of: this is not empty";

		this.preFront++;
		return this.rep[this.preFront];
	}

	@Override
	public int size() {
		return rear - preFront;
	}

	@Override
	public E front() {
		assert this.size() > 0 : "Violation of: this is not empty";

		return this.rep[this.preFront + 1];
	}

	/*
	 * Other inherited methods
	 */
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append("<");
		for (int current = preFront + 1; current <= rear; current++) {
			E element = this.rep[current];
			result.append(element.toString());
			if (current < rear) {
				result.append(",");
			}
		}
		result.append(">");
		return result.toString();
	}

}
