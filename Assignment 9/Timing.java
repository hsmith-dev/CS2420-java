package assignment09;

import java.util.Random;

import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Timing class used to determine the growth of our methods for our
 * implimentation of Heaps
 * 
 * @author Harrison Smith and Ben Maholi
 * @date November 21, 2019
 *
 */
public class Timing {

	private static long timeInsertElements(Heap<Integer> hp, int size) {
		Random rand = new Random();
		long totalTime = 0;
		for (int i = 0; i < size; i++) {
			long start = System.nanoTime();
			hp.add(rand.nextInt());
			long end = System.nanoTime();
			totalTime += end - start;
		}
		return totalTime;
	}

	private static long timeRemoveElements(Heap<Integer> hp, int size) {
		long totalTime = 0;
		for (int i = size; i > 0; i--) {
			long start = System.nanoTime();
			hp.removeFirst();
			long end = System.nanoTime();
			totalTime += end - start;
		}
		return totalTime;
	}

	private static long timeSortElements(Heap<Integer> hp) {
		long totalTime = 0;
		long start = System.nanoTime();
		hp.sort();
		long end = System.nanoTime();
		totalTime += end - start;
		return totalTime;
	}

	public static void main(String[] args) {
		Heap <Integer> hp = new Heap<Integer>();
		SimpleWriter writer = new SimpleWriter1L("timing.txt");
		writer.println("type;n;time");
		for(int i = 200_000; i <= 1_000_000; i += 100_000) {
			writer.println("insert;" + i + ";" + timeInsertElements(hp, i));
			writer.println("remove;" + i + ";" + timeRemoveElements(hp, i));
			timeInsertElements(hp, i);
			writer.println("sort;" + i + ";" + timeSortElements(hp));
			System.out.println(i);
		}
		writer.close();
	}

}
