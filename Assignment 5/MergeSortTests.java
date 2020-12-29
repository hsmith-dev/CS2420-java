package assignment05;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import assignment05.MergeSort;
import assignment05.Sorter;

/**
 * Calls the SorterTest and instantiates it in the type of Merge Sort
 * 
 * @author Sam Hancock - u0966409
 * @author Harrison Smith - u0901395
 *
 */
public class MergeSortTests extends SorterTest {

	@Override
	protected Sorter<Integer> uutInt() {
		return new MergeSort<Integer>();
	}

	@Override
	protected Sorter<String> uutString() {
		return new MergeSort<String>();
	}

}
