package assignment05;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class SortUtilsTestStudent {

	@Test
	public void swapElementsAtTest() {
		List<Integer> list = new ArrayList<>();
		list.add(5);
		list.add(10);
		list.add(7);
		SortUtils.swapElementsAt(list, 1, 2);
		List<Integer> expected = new ArrayList<>();
		expected.add(5);
		expected.add(7);
		expected.add(10);
		assertEquals(expected, list);
	}

	@Test
	public void isSortedTrueTest() {
		List<Integer> expected = new ArrayList<>();
		expected.add(5);
		expected.add(7);
		expected.add(10);
		boolean result = SortUtils.isSorted(expected);
		assertEquals(true, result);
	}

	@Test
	public void isSortedFalseTest() {
		List<Integer> expected = new ArrayList<>();
		expected.add(5);
		expected.add(11);
		expected.add(10);
		boolean result = SortUtils.isSorted(expected);
		assertEquals(false, result);
	}

	@Test
	public void listOfRandomIntsTest() {
		List<Integer> randomList = SortUtils.listOfRandomInts(10);
		// if the list did not populate the size of 10, then it will throw an out of
		// bounds error and fail the test
		for (int i = 0; i < 10; i++) {
			randomList.get(i);
		}
		assert (true);
	}
	
	@Test
	public void listOfSortedIntsTest() {
		List<Integer> sortedList = SortUtils.listOfSortedInts(10);
		boolean result = SortUtils.isSorted(sortedList);
		assertEquals(true, result);
	}
	
	@Test
	public void listOfReverseSortedIntsTest() {
		List <Integer> reverseList = SortUtils.listOfReversedSortedInts(10);
		for(int i = 9; i < 0; i--) {
			if(reverseList.get(i + 1) > reverseList.get(i))
				fail("Reverse Sorted does not put largest first");
		}
		assert(true);
	}
	
	public void listOfDuplicateIntsTest() {
		List<Integer> duplicateList = SortUtils.listOfDuplicateInts(10, 5);
		for(int i = 0; i < 10; i++) {
			if(duplicateList.get(i) != 5)
				fail("Not all the same element");
		}
		assert(true);
	}

}
