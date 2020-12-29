package assignment05;

/**
 * Calls Sorter Test and instantiates it in Quick Sort Pivot First
 * 
 * @author Sam Hancock
 * @author Harrison Smith
 */
public class QuickSorFirstTest extends SorterTest {

	@Override
	protected Sorter<Integer> uutInt() {
		return new QuickSortPivotFirst<Integer>();
	}

	@Override
	protected Sorter<String> uutString() {
		return new QuickSortPivotFirst<String>();
	}

}
