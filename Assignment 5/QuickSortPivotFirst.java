package assignment05;

import java.util.List;

/**
 * Implements in-place QuickSort using the {@code Sorter} interface by selecting
 * the first element in the list as the pivot.
 * 
 * This class must implement the {@code setThreshold, threshold} methods.
 * 
 * @author Sam Hancock
 * @author Harrison Smith
 *
 * @param <T> type of the element of the list this sorter can sort
 */
public class QuickSortPivotFirst<T extends Comparable<? super T>> extends AbstractQuickSort<T> {


	public QuickSortPivotFirst() {
		this.name = "Quick Sort Pivot First";
		this.complexity = ComplexityClass.NLOGN;
	}

	/**
	 * Returns the pivot around which to quick-sort the list. This method may modify
	 * the {@code list}. Uses first element
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
	@Override
	protected T pivot(List<T> list, int start, int end) {
		assert list != null : "Violation of: list is not null";
		
		return list.get(start); // pivot selected as first element of unsorted section
	}

	

}
