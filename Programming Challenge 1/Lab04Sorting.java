package lab04;

import java.util.Arrays;
import java.util.Random;

import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.stopwatch.Stopwatch;
import components.stopwatch.Stopwatch1;
//import components.util.Utilities;

/**
 * Coding Challenge - September 13, 2019
 * 
 * @author Harrison Smith
 * 
 *
 */
public class Lab04Sorting {

	/*
	 * Helper methods ----------------
	 */
	/**
	 * Reports if the given integer arrays have the same values, i.e., same integers
	 * in the same order.
	 *
	 * This implementation is deliberately kept simple by avoiding null check, alias
	 * check, etc. which are guaranteed to hold by the nature of the code above.
	 * Java's built-in {@code Arrays.deepEquals} requires the parameters to be
	 * arrays of Objects, hence this workaround.
	 *
	 * @param a1 first array
	 * @param a2 second array
	 * @return true iff {@code a1} and {@code a2} have the same values
	 */
	private static boolean deepEquals(int[] a1, int[] a2) {
		if (a1.length != a2.length) {
			return false;
		}

		for (int i = 0; i < a1.length; i++) {
			if (a1[i] != a2[i]) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Returns an array of the given number of random integers.
	 *
	 * @param count length of the array to be generated
	 * @return array of {@code count} random {@code int}s
	 */
	private static int[] arrayOfRandomInts(int count) {
		int[] arr = new int[count];
		Random r = new Random();
		for (int i = 0; i < count; i++) {
			arr[i] = r.nextInt();
		}
		return arr;
	}

	/**
	 * Prints the run time of the three sorting methods: the built-in Arrays.sort,
	 * selection sort implemented here, and insertion sort implemented here, over
	 * arrays of various sizes containing random integers.
	 *
	 * @param out Stream to print the run times on
	 */
	private static void sortingAnalysis(SimpleWriter out) {
		// Stopwatch timer = new Stopwatch1();
		final int increment = 500;
		int n = increment;

		while (n <= 20000) {

			out.print(n + ";");

			int[] refArrary = arrayOfRandomInts(n);
			int[] selectionSortArray = Arrays.copyOf(refArrary, n);
			int[] insertionSortArray = Arrays.copyOf(refArrary, n);

			assert deepEquals(refArrary, selectionSortArray) && deepEquals(refArrary, insertionSortArray);

			long start = System.nanoTime();
			Arrays.sort(refArrary); // built-in sort
			long end = System.nanoTime();
			out.print(end - start + ";");

			start = System.nanoTime();
			selectionSort(selectionSortArray);
			end = System.nanoTime();
			out.print(end - start + ";");

			start = System.nanoTime();
			insertionSort(insertionSortArray);
			end = System.nanoTime();
			out.print(end - start + "\n");

			assert deepEquals(refArrary, selectionSortArray) && deepEquals(refArrary, insertionSortArray);
			n += increment;
		}
	}

	/**
	 * Sorts the given array of integers in non-decreasing order using the Selection
	 * Sort Algorithm.
	 *
	 * @param arr array to be sorted
	 *
	 * @modifies {@code arr}
	 */
	public static void selectionSort(int[] arr) {
		for (int i = 0; i < arr.length - 1; i++) {
			int index = i;
			for (int j = i + 1; j < arr.length; j++)
				if (arr[j] < arr[index])
					index = j;

			int smallNum = arr[index];
			arr[index] = arr[i];
			arr[i] = smallNum;
		}
	}

	/**
	 * Sorts the given array of integers in non-decreasing order using the Insertion
	 * Sort Algorithm.
	 *
	 * @param arr array to be sorted
	 *
	 * @modifies {@code arr}
	 */
	public static void insertionSort(int[] arr) {
		for (int i = 1; i < arr.length; i++) {
			int check = arr[i];
			int index = i - 1;
			while ((index > -1) && (arr[index] > check)) {
				arr[index + 1] = arr[index];
				index--;
			}
			arr[index + 1] = check;
		}
	}

	/*
	 * Main method ---
	 */

	public static void main(String[] args) {

		SimpleWriter out = new SimpleWriter1L();

		// testing selectionSort
		out.println();
		int[] arr1 = { 10, 13, 4, 19, 20, 5, 1, -5, -2 };
		out.println("Pre Selection Sort");
		for (int index : arr1) {
			out.print(index + " ");
		}
		selectionSort(arr1);
		out.println("\nPost Selection Sort");
		for (int index : arr1) {
			out.print(index + " ");
		}
		out.println();

		// testing insertSort
		out.println();
		int[] arr2 = { 10, 13, 4, 19, 20, 5, 1, -5, -2 };
		out.println("Pre Insert Sort");
		for (int index : arr2) {
			out.print(index + " ");
		}
		insertionSort(arr2);
		out.println("\nPost Insert Sort");
		for (int index : arr2) {
			out.print(index + " ");
		}
		out.println();

		// TODO uncomment the following line to run the analysis
		sortingAnalysis(out);

		out.close();
	}

}
