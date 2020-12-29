package assignment05;

import java.util.List;

/**
 * Implements insertion sort using the {@code Sorter} interface.
 * 
 * This class does not implement the {@code setThreshold} method.
 * 
 * @author Samuel Hancock
 * @author Harrison Smith
 *
 * @param <T> type of the element of the list this sorter can sort
 */
public class InsertionSort<T extends Comparable<? super T>> extends AbstractSorter<T> {

	private int threshold;

	public InsertionSort() {
		this.name = "Insertion Sort";
		this.complexity = ComplexityClass.NSQUARED;
	}

	// it was necessary to make a custom sort for insertion
	// Since values could not be argued to it in the same fashion
	@Override
	public void sort(List<T> list) {
		assert list != null : "Violation of: list is not null";

		insertionSort(list);
	}

	/**
	 * Sorts a list using the insertion method
	 * 
	 * @param list - list to be sorted
	 */
	private void insertionSort(List<T> list) {
		for (int i = 1; i < list.size(); i++) {
			// Pull next element to be compared
			T element = list.get(i);
			for (int j = i - 1; j >= 0; j--) {
				// Pull and compare all elements from behind the partition
				T sorted = list.get(j);
				checkAndSwap(list, sorted, element, j);
			}
		}
	}

	/**
	 * Performs the swap
	 * 
	 * @param list    - list to be sorted
	 * @param sorted  - element being swapped to
	 * @param element - element being swapped in (previously unsorted)
	 * @param index   - the position where element needs to be added
	 */
	private void checkAndSwap(List<T> list, T sorted, T element, int index) {
		if (sorted.compareTo(element) < 0) {
			list.set(index, element);
			list.set(index + 1, sorted);
		}

	}

	@Override
	public int threshold(){
		return this.threshold;

	}
	
	@Override
	public void setThreshold(int threshold) {
		this.threshold = threshold;
	}

}
