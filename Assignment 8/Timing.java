package assignment08;

import java.util.Random;

import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

public class Timing<E> {

	private static <E> HashTableSet <Integer> buildHashTable(int size) {
		HashTableSet <Integer> hs = new HashTableSet<>();
		Random rand = new Random();
		for (int i = 0; i < size; i++) {
			hs.add(rand.nextInt(size));
		}
		hs.add(size);
		return hs;
	}

	private static int find(int x, int times, HashTableSet <Integer> hs) {
		int seconds = 0;
		for (int i = 0; i <= times; i++) {
			long startTime = System.nanoTime();
			hs.contains(x);
			long endTime = System.nanoTime();
			long totalNano = endTime - startTime;
			// converts nano seconds to seconds
			seconds += totalNano;
		}
		// computes average amount of time and returns them in seconds
		return seconds /= times;
	}

	private static void experiment01() {
		SimpleWriter writer = new SimpleWriter1L("exp1.txt");
		writer.println("n;containsTime");
		for(int i = 10; i <= 1_000_000; i *= 10) {
			HashTableSet <Integer> hs = buildHashTable(i);
			int seconds = find(i / 2, 3, hs);
			writer.println(i + ";" + seconds);
			System.out.println(i);
		}
		writer.close();
	}
	
	public static void main(String[] args) {
		experiment01();
	}

}
