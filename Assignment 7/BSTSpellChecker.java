package assignment07;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import components.list.List;
import components.list.ListOnJavaArrayList;

/**
 * BSTSpellChecker, used to check if there is a misspelling within a file, based
 * on a BST created from valid words. This implementation will say there are
 * misspellings for anything that is not exactly how it is stated within the BST
 * of valid words; if there is a capital, period or any other symbol it will not
 * factor those out. Thus resulting in a "misspelled" word
 * 
 * @author Harrison Smith
 * @author Ben Malohi
 * @date October 31, 2019
 *
 */
public class BSTSpellChecker implements SpellChecker {

	/**
	 * Instance variables
	 */
	private List<String> fileWords;
	private BinarySearchTreeOfStrings validWords;

	/**
	 * No argument constructor
	 */
	public BSTSpellChecker() {
		fileWords = new ListOnJavaArrayList<>();
		validWords = new BinarySearchTreeOfStrings();
	}

	/**
	 * Constructs the BST of valid words
	 * 
	 * @param filename is what file we are going to import from
	 */
	@Override
	public void loadValidWords(String filename) {
		loadFile(filename, validWords);
	}

	/**
	 * Constructs a list to compare to the valid words
	 * 
	 * @param filename is what file we are going to import from
	 * @return the list of all the misspelled words
	 */
	@Override
	public List<String> misspelledWords(String filename) {
		loadFile(filename, fileWords);

		// loop through the list of words, removing any valid words
		for (int i = fileWords.size() - 1; i > 0; i--) {
			if (validWords.contains(fileWords.get(i))) {
				fileWords.remove(i);
			}
		}
		return fileWords;
	}

	/**
	 * Empties the BST of Valid Words and resets the instance
	 * 
	 * @modifies the BST of valid words and the misspelled list to be empty
	 */
	@Override
	public void clear() {
		validWords.clear();
		fileWords.clear();
	}

	/**
	 * Helper Method that fills either a List or BST based on what Object is passed
	 * in
	 * 
	 * @param filename - location of the file we will be importing from
	 * @param o        - the object that will be used to add either to the BST or to
	 *                 a List depending on the type
	 * @requires - the correct Object type of either List or
	 *           BinarySearchTreeOfStrings
	 */
	private void loadFile(String filename, Object o) {
		// Creates a scanner to use to read from the file
		Scanner sc = null;
		try {
			sc = new Scanner(new File(filename));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		// imports the file line by line
		while (sc.hasNextLine()) {
			String line = sc.nextLine();
			// String array used to split the line into individual strings
			String[] arrStr = line.split(" ");
			// loops through the line and adds them to the individual strings
			for (int i = 0; i < arrStr.length; i++) {
				String str = arrStr[i];
				// if {@code o} is {@code validWords} it will insert it into the BST
				if (o.equals(validWords)) {
					validWords.insert(str);
				}
				// if {@code o} is {@code fileWords} it will add to a List
				else if (o.equals(fileWords)) {
					fileWords.add(str);
				}
			}
		}
	}
}
