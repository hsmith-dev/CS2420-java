package assignment07;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import components.bintree.BalancedBST1;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Timing for the Binary Search tree and how each will be inserted and comparing
 * it to the provided Balanced BST
 * 
 * @author Harrison Smith
 * @author Ben Malohi
 * @date October 31, 2019
 *
 */
public class Timing {

	/**
	 * Helper method that is used to generate a list of size {@code n}
	 * 
	 * @param n - the size that is provided to build the list to
	 * @return an ArrayList of type Integer with the generated list
	 */
	private static ArrayList<Integer> generateList(int n) {
		ArrayList<Integer> gen = new ArrayList<>();
		Random rand = new Random();
		for (int i = 0; i <= n; i++) {
			gen.add(rand.nextInt());
		}

		return gen;
	}

	public static void main(String[] args) {
		// Writes out to a file
		SimpleWriter writer = new SimpleWriter1L("timing.txt");
		writer.println("n;bstString;bstBala");
		// writer.println("n;Rand;Sorted");
		for (int i = 0; i <= 500_000; i += 10_000) {
			// Objects for timing to use
			BinarySearchTreeOfStrings bstRand = new BinarySearchTreeOfStrings();
			BinarySearchTreeOfStrings bstSort = new BinarySearchTreeOfStrings();
			BalancedBST1<String> bstB = new BalancedBST1<>();
			ArrayList<Integer> rand = generateList(i);
//			ArrayList<Integer> sorted = generateList(i);
//			Collections.sort(sorted);

			long start, end, totalRand, totalSort, totalBal;

			// time random
			start = System.nanoTime();
			for (int j = 0; j < rand.size(); j++) {
				bstRand.insert(rand.get(j).toString());
			}
			end = System.nanoTime();
			totalRand = end - start;

			// time balanced
			start = System.nanoTime();
			for (int j = 0; j < rand.size(); j++) {
				bstB.insert(rand.get(j).toString());
			}
			end = System.nanoTime();
			totalBal = end - start;
			writer.println(i + ";" + totalRand + ";" + totalBal);

			// Stack Overflow at 53_000, commented out so we can time other approaches
			// time sorted
//			start = System.nanoTime();
//			for(int j = 0; j < sorted.size(); j ++) {
//				bstSort.insert(sorted.get(j).toString());
//			}
//			end = System.nanoTime();
//			totalSort = end - start;
//			writer.println(i + ";" + totalRand + ";" + totalSort );
		}
		writer.close();
	}

}
