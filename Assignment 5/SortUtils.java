package assignment05;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Various utility methods for Sorting analysis.
 * 
 * @author Sam Hancock
 * @author Harrison Smith
 *
 */
public final class SortUtils {

	/**
	 * Sole constructor -- making it private ensures this class cannot be
	 * instantiated.
	 * 
	 */
	private SortUtils() {
	}

	/**
	 * Swaps the elements at the given positions in the list.
	 * 
	 * @param <T>  type of the elements of the list
	 * @param list list in which elements are to be swapped
	 * @param i    index of the first element
	 * @param j    index of the second element
	 * 
	 * @requires {@code i} and {@code j} are valid indices
	 * 
	 * @modifies {@code list}
	 * 
	 */
	final public static <T> void swapElementsAt(List<T> list, int i, int j) {
		assert list != null : "Violation of: list is not null";
		assert 0 <= i && i < list.size() : "Violation of: i is a valid index";
		assert 0 <= j && j < list.size() : "Violation of: i is a valid index";

		T first = list.get(i);
		T second = list.get(j);
		list.set(i, second);
		list.set(j, first);

	}

	/**
	 * Reports if the given list is sorted in the non-decreasing order according to
	 * its "natural order", i.e., the one described by its {@code compareTo} method.
	 * 
	 * @param <T>  type of the elements of the list
	 * @param list the given list
	 * @return true iff {@code list} is sorted
	 */
	public static <T extends Comparable<? super T>> boolean isSorted(List<T> list) {
		assert list != null : "Violation of: list is not null";

		for (int i = 1; i < list.size(); i++) {
			T second = list.get(i);
			T first = list.get(i - 1);
			if (first.compareTo(second) > 0) {
				return false;
			}
		}

		return true;
	}

	/**
	 * Creates and returns a list of the given number of random integers.
	 * 
	 * @param count number of integers to generate
	 * @return list of {@code count} number of integers
	 * 
	 * @requires count >= 0
	 */
	public static List<Integer> listOfRandomInts(int count) {
		assert count >= 0 : "Violation of count >= 0";

		List<Integer> randomList = new ArrayList<>();
		Random r = new Random();
		for (int i = 0; i < count; i++) {
			randomList.add(r.nextInt());
		}

		return randomList;
	}

	/**
	 * Creates and returns a list of the given number of integers in a
	 * non-decreasing order.
	 * 
	 * @param count number of integers to generate
	 * @return list of {@code count} number of integers
	 * 
	 * @requires count >= 0
	 */
	public static List<Integer> listOfSortedInts(int count) {
		assert count >= 0 : "Violation of count >= 0";

		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < count; i++) {
			list.add(i);
		}

		return list;
	}

	/**
	 * Creates and returns a list of the given number of integers in a
	 * non-increasing order.
	 * 
	 * @param count number of integers to generate
	 * @return list of {@code count} number of integers
	 * 
	 * @requires count >= 0
	 */
	public static List<Integer> listOfReversedSortedInts(int count) {
		assert count >= 0 : "Violation of count >= 0";

		List<Integer> list = new ArrayList<>();
		for (int i = count; i > 0; i--) {
			list.add(i);
		}

		return list;
	}

	/**
	 * Creates and returns a list of the given number of integers where each element
	 * is a copy of {@code element}.
	 * 
	 * @param count   number of integers to generate
	 * @param element number to be duplicated
	 * @return list of {@code count} number of integers
	 * 
	 * @requires count >= 0
	 * 
	 */
	public static List<Integer> listOfDuplicateInts(int count, int element) {
		assert count >= 0 : "Violation of count >= 0";

		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < count; i++) {
			list.add(i, element);
		}

		return list;
	}

	/**
	 * Measures the running time of the sort method of the given routine on the
	 * given list. After running the sorter, if the list is sorted, it returns the
	 * time taken by the {@code sortRoutine} in nanoseconds, otherwise returns
	 * {@code Long.MIN_VALUE}.
	 * 
	 * @param sortRoutine the sorting algorithm to test
	 * @param list        the list to be sorted
	 * @return running time of the given sorter in nanoseconds if the sorting was
	 *         correct, {@code Long.MIN_VALUE} otherwise.
	 * 
	 * @modifies {@code list}
	 */
	public static long testAndTime(Sorter<Integer> sortRoutine, List<Integer> list) {
		assert sortRoutine != null : "Violation of: sortRoutine not null";
		assert list != null : "Violation of: list is not null";
		long lastStart = 0;
		long lastStop = 0;
		for (int j = 0; j < 9; j++) {

			long start = System.nanoTime();
			sortRoutine.sort(list);
			long stop = System.nanoTime();

			lastStart += start;
			lastStop += stop;

		}
		System.gc();// Where is the best place for garbage collection
		return (lastStop - lastStart) / 10;
	}

	/**
	 * Generates the timing report in a specified format for the given sorter. It
	 * runs the sorter on various lists (random, sorted, reverse-sorted, duplicates)
	 * of various sizes (from {@code startSize} to {@code maxSize}, incremented by
	 * {@code sizeIncrement}), ensures the sorter is sorting the list correctly, and
	 * prints out the name of the sorter and the time it takes in nanoseconds in the
	 * following format:
	 * 
	 * <sorter_name>;<list_type>;<list_size>;<time_taken>
	 * 
	 * @param sortRoutine   sorter to be tested
	 * @param startSize     start size of the lists
	 * @param sizeIncrement how many elements to go up in each iteration
	 * @param maxSize       maximum size of the lists to be tested
	 * @param timeoutSec    if the last iteration of the test took more time than
	 *                      this number, don't do any more tests in this category
	 *                      (in seconds)
	 * 
	 * @requires startSize >= 0 and startSize <= maxSize and timeoutSec > 0
	 */
	public static void generateTimingReport(Sorter<Integer> sortRoutine, int startSize, int sizeIncrement, int maxSize,
			int timeoutSec) {
		assert startSize >= 0 : "Violation of: startSize >= 0";
		assert startSize <= maxSize : "Violation of: startSize <= maxSize";
		assert timeoutSec > 0 : "Violation of: timeoutSec > 0";
		assert sortRoutine != null : "Violation of: sortRoutine not null";

		SimpleWriter out = new SimpleWriter1L();
		out.println(sortRoutine.name() + " (Expected runtime: " + sortRoutine.getExpectedComplexityClass() + ")");
		out.println("=============================================");

		for (int i = startSize; i <= maxSize; i += sizeIncrement) {
			long randomTime = testAndTime(sortRoutine, listOfRandomInts(i));
			int randomSec = (int) (randomTime/100000000);
			if(randomSec > timeoutSec) {
				out.println("Random took longer than timeoutSec:" + timeoutSec + " for size of " + i);
				break;
			}
			out.println("Random;" + randomTime);
			
			long sortedTime = testAndTime(sortRoutine, listOfSortedInts(i));
			int sortedSec = (int) (sortedTime/100000000);
			if(sortedSec > timeoutSec){
				out.println("Sorted took longer than timeoutSec:" + timeoutSec + " for size of " + i);
				break;
			}
			out.println("Sorted;" + sortedTime);
			
			long reverseTime = testAndTime(sortRoutine, listOfReversedSortedInts(i));
			int reverseSec = (int) (reverseTime/100000000);
			if(reverseSec > timeoutSec){
				out.println("Reverse took longer than timeoutSec:" + timeoutSec + " for size of " + i);
				break;
			}
			out.println("Reverse;" + reverseTime);

			long duplicatesTime = testAndTime(sortRoutine, listOfDuplicateInts(i, i));
			int duplicatesSec = (int) (duplicatesTime/100000000);
			if(duplicatesSec > timeoutSec){
				out.println("SortedDuplicates took longer than timeoutSec:" + timeoutSec + " for size of " + i);
				break;
			}
			out.println("SortedDuplicates;" + duplicatesTime);
			System.out.println(i);
		}

		out.println("=============================================");
		out.close();
	}
	
	

	/**
	 * The main will instantiate the Sorter objects and call the
	 * generateTimingReport and print them out
	 * 
	 * @param args enables main to take in arguments from the terminal
	 */
	public static void main(String[] args) {
		InsertionSort<Integer> iSorter = new InsertionSort<>();
		generateTimingReport(iSorter, 500, 500, 10_500, 10);
		MergeSort<Integer> mSorter = new MergeSort<>();
		generateTimingReport(mSorter, 500, 500, 10_500, 10);
		QuickSortPivotFirst<Integer> qsPivFir = new QuickSortPivotFirst<>();
		generateTimingReport(qsPivFir, 500, 500, 10_500, 10);
		QuickSortPivotM3<Integer> qsPivM3 = new QuickSortPivotM3<>();
		generateTimingReport(qsPivM3, 500, 500, 10_500, 10);
		QuickSortPivotRandom<Integer> qsPivRan = new QuickSortPivotRandom<>();
		generateTimingReport(qsPivRan, 500, 500, 10_500, 10);
		JavaBuitInSort<Integer> jBuiltSort = new JavaBuitInSort<>();
		generateTimingReport(jBuiltSort, 500, 500, 10_500, 10);
		QuickSortNaive<Integer> qsNaive = new QuickSortNaive<>();
		generateTimingReport(qsNaive, 500, 500, 10_500, 10);
		
		for(int i = 50; i <= 150; i += 50) {
			mSorter.setThreshold(i);
			qsPivM3.setThreshold(i);
			qsPivFir.setThreshold(i);
			qsPivRan.setThreshold(i);
			System.out.println("Threshold is: " + i);
			generateTimingReport(mSorter, 0, 1_000, 20_001, 10);
			generateTimingReport(qsPivFir, 0, 1_000, 20_000, 10);
			generateTimingReport(qsPivM3, 0, 1_000, 20_000, 10);
			generateTimingReport(qsPivRan, 0, 1_000, 20_000, 10);

		}

	}

}
