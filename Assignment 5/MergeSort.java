package assignment05;

import java.util.ArrayList;
import java.util.List;

/**
 * Implements the merge sort using the {@code Sorter} interface.
 *
 * This class must implement the {@code setThreshold, threshold} methods.
 *
 * @author Sam Hancock
 * @author Harrison Smith
 *
 * @param <T> type of the element of the list this sorter can sort
 */
public class MergeSort<T extends Comparable<? super T>> extends AbstractSorter<T> {

	private int threshold;

	public MergeSort() {
		this.name = "Merge Sort";
		this.complexity = ComplexityClass.NLOGN;
	}

	@Override
	public void sort(List<T> list) {
		assert list != null : "Violation of: list is not null";
		// calls the method to sort the list
		if(list.size() < threshold){
			Sorter<T> insertion = new InsertionSort<T>();
			insertion.sort(list);
		}
		mergeSort(list, 0, list.size() - 1);

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

	/**
	 * Breaks apart the given list into the smallest problem Merges after that
	 * smallest problem is found
	 * 
	 * @param list   - list to be sorted of type T
	 * @param bottom - first index of the list to be looked at
	 * @param top    - last index of the list to be looked at
	 */
	private void mergeSort(List<T> list, int bottom, int top) {
		
		// checks to ensure that we are not going out of bounds
		if (bottom < top) {
			int half = (top + bottom) / 2;
			// recursively calls itself splitting the list down into single partitions, left
			// to right
			mergeSort(list, bottom, half);
			mergeSort(list, half + 1, top);
			// builds the list back together in a sorted manner
			merge(list, bottom, half + 1, top);
		}
	}

	/**
	 * Compares near objects in list, writes them to a new list in order and copies
	 * that list to the original
	 * 
	 * @param list         - list to get items from for comparison
	 * @param startOfLeft  - Left most index of left most sublist to be examined
	 * @param startOfRight - left most index of rightmost sublist to be examined
	 * @param endOfRight   - end of rightmost sublist
	 */
	private void merge(List<T> list, int startOfLeft, int startOfRight, int endOfRight) {
		List<T> resultList = new ArrayList<>();
		// save markers to advance through the respective sublists
		int currR = startOfRight;
		int currL = startOfLeft;
		int endL = startOfRight - 1;
		// to advance to variables concurrently, start with a while loop that
		// goes until one marker reaches the end of its respective sublist
		while (currR <= endOfRight && currL <= endL) {
			T leftItem = list.get(currL);
			T rightItem = list.get(currR);
			if (leftItem.compareTo(rightItem) <= 0) {
				resultList.add(leftItem);
				currL++;
			} else {
				resultList.add(rightItem);
				currR++;
			}
		}
		// Once one of the markers completes its sublist, and the other sublist is
		// sorted within itself
		// add the rest of whatever sublist has elements left
		while (currR <= endOfRight) {
			resultList.add(list.get(currR));
			currR++;
		}

		while (currL <= endL) {
			resultList.add(list.get(currL));
			currL++;
		}
		// overwrite from built list to original, leaves that section sorted
		int currResult = 0;
		for (int i = startOfLeft; i <= endOfRight; i++) {
			list.set(i, resultList.get(currResult));
			currResult++;
		}

	}

}