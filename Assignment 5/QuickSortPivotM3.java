package assignment05;

import java.util.ArrayList;
import java.util.List;

/**
 * Implements in-place QuickSort using the {@code Sorter} interface by selecting
 * the "median of three" as the pivot.
 * 
 * This class must implement the {@code setThreshold, threshold} methods.
 * 
 * @author Samuel Hancock
 * @author Harrison Smith
 *
 * @param <T> type of the element of the list this sorter can sort
 */
public class QuickSortPivotM3<T extends Comparable<? super T>> extends AbstractQuickSort<T> {


	// Class constructor
	public QuickSortPivotM3() {
		this.name = "Quick Sort Median Of Three";
		this.complexity = ComplexityClass.NLOGN;

	}

	@Override
	protected T pivot(List<T> list, int start, int end) {
		assert list != null : "Violation of: list is not null";
		assert start <= end : "Start must be less than or equal to end.";
		return getMedian(list, start, end);
	}

	/**
	 * Returns a median value of three objects in the subset
	 * 
	 * @param list  - list with the part to be examined
	 * @param start - first index of the part to be examined
	 * @param end   - last element of part to be examined
	 * @return Median element of the subset
	 */
	private T getMedian(List<T> list, int start, int end) {
		T first = list.get(start);
		T last = list.get(end);
		T mid = list.get((end - start) / 2);
		List<T> ofThree = new ArrayList<>();
		ofThree.add(first);
		ofThree.add(mid);
		ofThree.add(last);
		Sorter<T> insertion = new InsertionSort<>();
		insertion.sort(ofThree);
		return ofThree.get(1);
	}

	

}
