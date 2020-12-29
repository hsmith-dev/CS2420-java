package assignment05;

import assignment05.QuickSortPivotM3;
import assignment05.Sorter;

/**
 * This test class calls the SorterTest class and initializes the tests as a
 * QuickSortPivotM3 sorter
 * 
 * 
 * @author Sam Hancock
 * @author Harrison Smith
 *
 */
public class PivotM3Tests extends SorterTest {

	@Override
	protected Sorter<Integer> uutInt() {
		return new QuickSortPivotM3<Integer>();
	}

	@Override
	protected Sorter<String> uutString() {
		return new QuickSortPivotM3<String>();
	}

}
