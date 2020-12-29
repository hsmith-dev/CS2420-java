package lab07;

/**
 * First-in-first-out (FIFO) queue component. (Note: by package-wide convention,
 * all references are non-null.)
 *
 * @author Harrison Smith
 * @date October 4, 2019
 * 
 * @param <E> type of Queue elements
 *
 */
public interface Queue<E> {
    /**
     * Adds {@code x} to the end of {@code this}.
     *
     * @param x the entry to be added
     * @modifies {@code this}
     */
    void enqueue(E x);

    /**
     * Removes and returns the entry at the front of {@code this}.
     *
     * @return the entry removed
     * @modifies {@code this}
     * @requires {@code this} is not empty
     */
    E dequeue();

    /**
     * Reports the number of elements in {@code this}.
     *
     * @return the number of elements in {@code this}
     */
    int size();

    /**
     * Reports the front element of {@code this}.
     *
     * @return the front entry of {@code this}
     * @requires {@code this} is not empty
     */
    E front();
}
