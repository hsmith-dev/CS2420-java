package assignment05;

import assignment05.Sorter;

/**
 * Calls Sorter Test and instantiates it in Quick Sort Pivot Random
 * 
 * @author Sam Hancock
 * @author Harrison Smith
 */
public class PivotRandomTests extends SorterTest {

	@Override
	protected Sorter<Integer> uutInt() {
		return new QuickSortPivotRandom<Integer>();
	}

	@Override
	protected Sorter<String> uutString() {
		return new QuickSortPivotRandom<String>();
	}

}
