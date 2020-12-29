package assignment05;

import java.util.List;

/**
 * Implementation of common methods for all quick-sort variants.
 * 
 * @author Sam Hancock - u0966409
 * @author Harrison Smith - u0901395
 *
 * @param <T> type of elements of the collection to be sorted
 */
public abstract class AbstractQuickSort<T extends Comparable<? super T>> extends AbstractSorter<T> {
	
	private int threshold;
	
	/**
	 * Returns the pivot around which to quick-sort the list. This method may modify
	 * the {@code list}, e.g., median of three will move smallest value to front of
	 * the list.
	 * 
	 * @param list  the list to find the pivot in
	 * @param start start-index of the sublist to consider
	 * @param end   end-index of the sublist to consider
	 * @return pivot for quick-sort
	 * 
	 * @requires start <= end
	 * 
	 * @modifies {@code list}
	 */
	protected abstract T pivot(List<T> list, int start, int end);

	/**
	 * Determines a pivot and partitions the {@code list} between {@code left} and
	 * {@code right} such that all elements less than pivot are on its left and all
	 * elements greater than pivot are to its right.
	 * 
	 * @param list  the list to be partitioned
	 * @param left  start-index of the sublist to partition
	 * @param right end-index of the sublist to partition
	 * @return the location of the pivot in the resulting list
	 * 
	 * @modifies {@code list}
	 */

	protected int partition(List<T> list, int left, int right) {
		assert list != null : "Violation of: list is not null";
		if(list.size() < threshold){
			Sorter<T> insertion = new InsertionSort<T>();
			insertion.sort(list);
		}
		T pivot = pivot(list, left, right);

		list.remove(pivot);// take the pivot element out to avoid comparing it
		int j = right - 1;
		int i = left;
		while (i < j) {
			// sinister is latin for left
			T sinister = list.get(i);
			// dexter is latin for right
			T dexter = list.get(j);

			while ((sinister.compareTo(pivot) <= 0) && i < j) {
				// leaves elements similar to pivot in place
				// also leaves elements less than pivot in place
				i++;
				sinister = list.get(i);
			}
			while ((pivot.compareTo(dexter) <= 0) && (i < j)) {
				// leaves elements similar to pivot in place
				// also leaves elements larger than pivot in place
				j--;
				dexter = list.get(j);
			}

			if ((sinister.compareTo(dexter) > 0) && (i < j)) {
				// puts large elements to the right and small elements to the left
				swap(list, i, j);
			}
			if (i < j) {
				// advances the markers towards each other
				i++;
				j--;
			}

		}
		// Selects the appropriate spot for the pivot and inserts the correct element
		if (list.get(j).compareTo(pivot) > 0)
			list.add(j, pivot);
		else if (list.get(j).compareTo(pivot) <= 0) {
			i++;
			list.add(i, pivot);
		}

		return j;

	}

	/**
	 * Sorts the virtual {@code list} between indexes {@code left} and {@code right}
	 * using the Quick-Sort algorithm.
	 * 
	 * @param list  the list to be sorted
	 * @param left  start-index of the sublist to be sorted
	 * @param right end-index of the sublist to be sorted
	 * 
	 * @modifies {@code list}
	 */

	protected void quickSort(List<T> list, int left, int right) {
		assert list != null : "Violation of: list is not null";
		// ensures we do not go past what we are supposed to sort
		if (left < right) {
			// stores the partition for us to recursively call
			int partition = partition(list, left, right);
			// recursively calls quicksort to split and sort the list
			quickSort(list, left, partition);
			quickSort(list, partition + 1, right);
		}
	}

	@Override
	public void sort(List<T> list) {
		assert list != null : "Violation of: list is not null";
		// added so quick sort will be common on the whole
		quickSort(list, 0, list.size() - 1);

	}

	/**
	 * Interchanges given elements on the list
	 * 
	 * @param list
	 * @param i
	 * @param j
	 * 
	 * @modifies list
	 */
	private void swap(List<T> list, int i, int j) {
		// saves the elements and then swaps the two elements within the list
		T first = list.get(i);
		T second = list.get(j);
		list.set(i, second);
		list.set(j, first);
	}
	
	/**
	 * sets internal threshold where the method will swap between insertion sort
	 * 
	 * @param threshold
	 * 
	 */
	@Override
	public void setThreshold(int threshold) {
		this.threshold = threshold;
	}

	/**
	 * @return internal threshold
	 */
	@Override
	public int threshold() {
		return this.threshold;
	}
}
