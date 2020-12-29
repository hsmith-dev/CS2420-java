package assignment05;

import java.util.List;
import java.util.Random;

/**
 * Implements in-place QuickSort using the {@code Sorter} interface by selecting
 * a random element in the list as the pivot.
 * 
 * This class must implement the {@code setThreshold, threshold} methods.
 * 
 * @author Sam Hancock
 * @author Harrison Smith
 *
 * @param <T> type of the element of the list this sorter can sort
 */
public class QuickSortPivotRandom<T extends Comparable<? super T>> extends AbstractQuickSort<T> {


	

	public QuickSortPivotRandom() {
		this.name = "Quick Sort Random Pivot";
		this.complexity = ComplexityClass.NLOGN;
	}

	@Override
	protected T pivot(List<T> list, int start, int end) {
		assert list != null : "Violation of: list is not null";

		T pivot = getRandomT(list, start, end);
		return pivot;
	}

	/**
	 * finds and returns randomly indexed element from subset as defined by
	 * start and end
	 * 
	 * @param list
	 * @param start
	 * @param end
	 * @return random element from given subset
	 */
	private T getRandomT(List<T> list, int start, int end) {
		Random R = new Random();

		int diff = end - start;
		int r = R.nextInt(diff) + start;
		return list.get(r);
	}

	


}