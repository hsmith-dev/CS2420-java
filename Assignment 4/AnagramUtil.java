package assignment04;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;

/**
 * 
 * @author Samuel Hancock
 * @author Harrison Smith
 * 
 * @date September 16, 2019
 * 
 *       Assignment04-Anagrams
 *
 */

public class AnagramUtil {

	/**
	 * Helper method that converts a file to a list
	 * 
	 * @param r - a Simple Reader Object that has taken in a file
	 * @return a List of type String containing all the words within the file
	 *         provided within the {@code r} object
	 */
	private static ArrayList<String> fileToList(SimpleReader r) {
		ArrayList<String> dictionary = new ArrayList<>();
		// Populates the Array List with the contents of the file
		while (!r.atEOS()) {
			dictionary.add(r.nextLine());
		}
		return dictionary;
	}

	/**
	 * Reports whether the two input strings are anagrams of each other . The two
	 * string objects must be sorted before calling this method.
	 *
	 * @param s1 the first candidate string
	 * @param s2 the second candidate string
	 * @return true iff { @code s1} and { @code s2} are anagrams of each other
	 */
	public static boolean areAnagrams(String s1, String s2) {
		// return s1.equals(s2);

		// returns if the two sorted strings are equal to one another
		return sort(s1).equals(sort(s2));
	}

	/**
	 * Sorts the input list using an insertion sort and the input { @code Comparator
	 * } object .
	 *
	 * @param <T    > type of the element of the list
	 * @param list  input list
	 * @param order comparator used to sort elements
	 *
	 * @modifies { @code list }
	 */
	public static <T> void insertionSort(List<T> list, Comparator<? super T> order) {
		for (int index = 1; index < list.size(); index++) {
			T check = list.get(index);
			int secondIndex = index;
			while (secondIndex > 0 && (order.compare(check, list.get(secondIndex - 1))) < 0) {
				list.set(secondIndex, list.get(secondIndex - 1));
				secondIndex--;
			}
			list.set(secondIndex, check);
		}
	}

	/**
	 * Returns a case - insensitive sorted version of the input String . For example
	 * , if { @code str = " Utah "} , it returns { @code " ahtu "}. This sorting
	 * must be done using insertion sort .
	 *
	 * @param str string to be sorted
	 * @return sorted string
	 */
	public static String sort(String str) {
		str = str.toLowerCase();
		char[] chars = str.toCharArray();
		int length = chars.length;

		// create and populate Character List to sort each letter in strings
		List<Character> charList = new ArrayList<Character>();
		for (int index = 0; index < length; index++) {
			charList.add(chars[index]);
		}

		// creates a new comparator
		Comparator<Character> comp = new Comparator<>() {

			@Override
			public int compare(Character o1, Character o2) {
				return ((Character) Character.toLowerCase(o1)).compareTo(Character.toLowerCase(o2));
			}
		};

		// sorts the list
		insertionSort(charList, comp);

		String isSorted = "";
		// char to Character
		for (int i = 0; i < charList.size(); i++) {
			isSorted += charList.get(i);
		}

		// returns the value of the characters as a string
		return isSorted;
	}

	/**
	 * Returns the largest group of anagrams from the list of words in the given
	 * file , in no particular order .
	 *
	 * It is assumed that the file contains one word per line . If the file is empty
	 * , the method returns an empty list because there are no anagrams .
	 *
	 * @param filename file to read strings from
	 * @return largest group of anagrams in the input file
	 */
	public static List<String> getLargestAnagramGroup(String filename) {
		SimpleReader r = new SimpleReader1L(filename);
		// converts the file to a List of type Strings
		List<String> anagram = fileToList(r);
		r.close();
		// passes in the list generated
		return getLargestAnagramGroup(anagram);

	}

	/**
	 * Returns the largest group of anagrams in the input list of words , in no
	 * particular order .
	 *
	 * @param input list of strings
	 * @return largest group of anagrams in { @code input }
	 */
	public static List<String> getLargestAnagramGroup(List<String> input) {
		Comparator<String> comp = new Comparator<>() {

			@Override
			public int compare(String s1, String s2) {
				return (sort(s1).compareTo(sort(s2)));
			}
		};
		// used to sort the data within the {@code input}
		insertionSort(input, comp);

		// variables to store locations and counters
		int savedCount = 0;
		int runningTally = 0;
		int startIndexOfLargest = 0;
		int lastIndexOfLargest = 0;
		String currentAnagram = input.get(0);
		// loops through {@code input}, tracks progress and variables
		for (int i = 1; i < input.size(); i++) {
			// increments the counter if still on current anagram

			if (areAnagrams(currentAnagram, input.get(i))) {
				runningTally++;
			}
			// we get a new largest anagram, resetting values/counters
			else if (runningTally > savedCount) {
				// this allows for us to get the starting index of the largest anagram
				startIndexOfLargest = i - runningTally;
				// checks to make sure it isn't the first index
				if (startIndexOfLargest != 0)
					startIndexOfLargest--;
				// stores the last index of the largest anagram
				lastIndexOfLargest = i;
				// resets the values of the counters after finding a new largest anagram
				savedCount = runningTally;
				runningTally = 0;
				// re populates current anagram to check against at index i
				currentAnagram = input.get(i);
			}
			// we move to a new anagram and reset the counter for the running tallys
			else {
				runningTally = 0;
				// re populates current anagram to check against at index i
				currentAnagram = input.get(i);
			}

		}
		// List that stores all the Strings that fit within the largest anagram
		List<String> largestGroup = new ArrayList<>();
		// loop to populate the largestGroup with the largest anagram group
		while (startIndexOfLargest < lastIndexOfLargest) {
			largestGroup.add(input.get(startIndexOfLargest));
			startIndexOfLargest++;
		}
		return largestGroup;
	}
}
