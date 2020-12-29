package assignment06;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.swing.Timer;

import components.list.DoublyLinkedList;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.util.Utilities;

/**
 * Code to time the performance of our A6DoublyLinkedList
 * 
 * @author Harrison Smith
 * @author Samuel Hancock
 *
 */
public class TIming {

	@SuppressWarnings("resource")
	private static void printTest(A6DoublyLinkedList<Integer> aList, int size, int increment) {
		SimpleWriter s = new SimpleWriter1L("linkedList_" + size + ".txt");
		s.println("Size;first;mid;end");
		// time insertFront
		long start;
		long end;

		for (int i = 0; i <= size; i += increment) {
			// rest the variables each time the for loops starts over
			long sumFirst = 0;
			long sumMid = 0;
			long sumEnd = 0;
			aList.clear();

			// time insertFirst
			start = System.nanoTime();
			fillToSize(aList, i, "first");
			end = System.nanoTime();
			sumFirst += end - start;
			aList.clear();

			// time insertMiddle
			start = System.nanoTime();
			fillToSize(aList, i, "mid");
			end = System.nanoTime();
			sumMid += end - start;
			aList.clear();

			// time insertEnd
			start = System.nanoTime();
			fillToSize(aList, i, "end");
			end = System.nanoTime();
			sumEnd += end - start;
			aList.clear();

			System.out.println(i);
			s.println(i + ";" + sumFirst + ";" + sumMid + ";" + sumEnd);
		}
	}

	private static void fillToSize(A6DoublyLinkedList<Integer> aList, int size, String position) {
		int pos = 0;
		Random rand = new Random();
		for (int i = aList.size() - 1; i < size; i++) {
			int data = rand.nextInt(1000);
			int currSize = aList.size();
			if (currSize > 2) {
				if (position.equals("end"))
					pos = currSize - 1;
				else if (position.equals("mid"))
					pos = rand.nextInt(currSize - 2) + 1;
			}
			aList.add(pos, data);
		}
	}

	private static void timeGiven() {
		SimpleWriter sA = new SimpleWriter1L("compareAdd.txt");
		SimpleWriter sR = new SimpleWriter1L("compareRemove.txt");
		sA.println("N Add Given;first;mid;end");
		sR.println("N Rem Given;first;mid;end");
		DoublyLinkedList<Integer> compareList = new DoublyLinkedList<>();
		Random rand = new Random();
		long sumAddFirst = 0;
		long sumAddMid = 0;
		long sumAddEnd = 0;
		long sumRemFirst = 0;
		long sumRemMid = 0;
		long sumRemEnd = 0;
		long start, end;

		// -----------10-----------
		// first add
		start = System.nanoTime();
		for (int i = 0; i <= 10; i++) {
			compareList.add(0, 10);
		}
		end = System.nanoTime();
		sumAddFirst = end - start;

		// first remove
		start = System.nanoTime();
		while (compareList.size() > 0)
			compareList.remove(0);
		end = System.nanoTime();
		sumRemFirst = end - start;

		// mid
		start = System.nanoTime();
		compareList.add(0, 10);
		for (int i = 1; i <= 10; i++) {
			compareList.add(rand.nextInt(compareList.size()) - 1, 10);
		}
		end = System.nanoTime();
		sumAddMid = end - start;

		// mid remove
		start = System.nanoTime();
		while (compareList.size() > 2)
			compareList.remove(rand.nextInt(compareList.size()) - 1);
		while (compareList.size() > 0)
			compareList.remove(0);
		end = System.nanoTime();
		sumRemFirst = end - start;

		// end
		start = System.nanoTime();
		for (int i = 0; i <= 10; i++) {
			compareList.add(compareList.size() - 1, 10);
		}
		end = System.nanoTime();
		sumAddEnd = end - start;

		// end remove
		start = System.nanoTime();
		compareList.remove(compareList.size() - 1);
		end = System.nanoTime();
		sumRemFirst = end - start;
		sA.println("10;" + sumAddFirst + ";" + sumAddMid + ";" + sumAddEnd);
		sR.println("10;" + sumRemFirst + ";" + sumRemMid + ";" + sumRemEnd);

		// -----------100-----------
		// first add
		start = System.nanoTime();
		for (int i = 0; i <= 100; i++) {
			compareList.add(0, 100);
		}
		end = System.nanoTime();
		sumAddFirst = end - start;

		// first remove
		start = System.nanoTime();
		while (compareList.size() > 0)
			compareList.remove(0);
		end = System.nanoTime();
		sumRemFirst = end - start;

		// mid
		start = System.nanoTime();
		compareList.add(0, 10);
		for (int i = 1; i <= 100; i++) {
			compareList.add(rand.nextInt(compareList.size()) - 1, 100);
		}
		end = System.nanoTime();
		sumAddMid = end - start;

		// mid remove
		start = System.nanoTime();
		while (compareList.size() > 2)
			compareList.remove(rand.nextInt(compareList.size()) - 1);
		while (compareList.size() > 0)
			compareList.remove(0);
		end = System.nanoTime();
		sumRemFirst = end - start;

		// end
		start = System.nanoTime();
		for (int i = 0; i <= 100; i++) {
			compareList.add(compareList.size() - 1, 100);
		}
		end = System.nanoTime();
		sumAddEnd = end - start;

		// end remove
		start = System.nanoTime();
		compareList.remove(compareList.size() - 1);
		end = System.nanoTime();
		sumRemFirst = end - start;
		sA.println("10000;" + sumAddFirst + ";" + sumAddMid + ";" + sumAddEnd);
		sR.println("10000;" + sumRemFirst + ";" + sumRemMid + ";" + sumRemEnd);

		// -----------1_000-----------
		// first add
		start = System.nanoTime();
		for (int i = 0; i <= 1_000; i++) {
			compareList.add(0, 100);
		}
		end = System.nanoTime();
		sumAddFirst = end - start;

		// first remove
		start = System.nanoTime();
		while (compareList.size() > 0)
			compareList.remove(0);
		end = System.nanoTime();
		sumRemFirst = end - start;

		// mid
		start = System.nanoTime();
		compareList.add(0, 10);
		for (int i = 1; i <= 1_000; i++) {
			compareList.add(rand.nextInt(compareList.size()) - 1, 100);
		}
		end = System.nanoTime();
		sumAddMid = end - start;

		// mid remove
		start = System.nanoTime();
		while (compareList.size() > 2)
			compareList.remove(rand.nextInt(compareList.size()) - 1);
		while (compareList.size() > 0)
			compareList.remove(0);
		end = System.nanoTime();
		sumRemFirst = end - start;

		// end
		start = System.nanoTime();
		for (int i = 0; i <= 1_000; i++) {
			compareList.add(compareList.size() - 1, 100);
		}
		end = System.nanoTime();
		sumAddEnd = end - start;

		// end remove
		start = System.nanoTime();
		compareList.remove(compareList.size() - 1);
		end = System.nanoTime();
		sumRemFirst = end - start;
		sA.println("10000;" + sumAddFirst + ";" + sumAddMid + ";" + sumAddEnd);
		sR.println("10000;" + sumRemFirst + ";" + sumRemMid + ";" + sumRemEnd);

		// -----------10_000-----------
		// first add
		start = System.nanoTime();
		for (int i = 0; i <= 10_000; i++) {
			compareList.add(0, 100);
		}
		end = System.nanoTime();
		sumAddFirst = end - start;

		// first remove
		start = System.nanoTime();
		while (compareList.size() > 0)
			compareList.remove(0);
		end = System.nanoTime();
		sumRemFirst = end - start;

		// mid
		start = System.nanoTime();
		compareList.add(0, 10);
		for (int i = 1; i <= 10_000; i++) {
			compareList.add(rand.nextInt(compareList.size()) - 1, 100);
		}
		end = System.nanoTime();
		sumAddMid = end - start;

		// mid remove
		start = System.nanoTime();
		while (compareList.size() > 2)
			compareList.remove(rand.nextInt(compareList.size()) - 1);
		while (compareList.size() > 0)
			compareList.remove(0);
		end = System.nanoTime();
		sumRemFirst = end - start;

		// end
		start = System.nanoTime();
		for (int i = 0; i <= 10_000; i++) {
			compareList.add(compareList.size() - 1, 100);
		}
		end = System.nanoTime();
		sumAddEnd = end - start;

		// end remove
		start = System.nanoTime();
		compareList.remove(compareList.size() - 1);
		end = System.nanoTime();
		sumRemFirst = end - start;
		sA.println("10000;" + sumAddFirst + ";" + sumAddMid + ";" + sumAddEnd);
		sR.println("10000;" + sumRemFirst + ";" + sumRemMid + ";" + sumRemEnd);

		// -----------100_000-----------
		// first add
		start = System.nanoTime();
		for (int i = 0; i <= 100_000; i++) {
			compareList.add(0, 100);
		}
		end = System.nanoTime();
		sumAddFirst = end - start;

		// first remove
		start = System.nanoTime();
		while (compareList.size() > 0)
			compareList.remove(0);
		end = System.nanoTime();
		sumRemFirst = end - start;

		// mid
		start = System.nanoTime();
		compareList.add(0, 10);
		for (int i = 1; i <= 100_000; i++) {
			compareList.add(rand.nextInt(compareList.size()) - 1, 100);
		}
		end = System.nanoTime();
		sumAddMid = end - start;

		// mid remove
		start = System.nanoTime();
		while (compareList.size() > 2)
			compareList.remove(rand.nextInt(compareList.size()) - 1);
		while (compareList.size() > 0)
			compareList.remove(0);
		end = System.nanoTime();
		sumRemFirst = end - start;

		// end
		start = System.nanoTime();
		for (int i = 0; i <= 100_000; i++) {
			compareList.add(compareList.size() - 1, 100);
		}
		end = System.nanoTime();
		sumAddEnd = end - start;

		// end remove
		start = System.nanoTime();
		compareList.remove(compareList.size() - 1);
		end = System.nanoTime();
		sumRemFirst = end - start;
		sA.println("100000;" + sumAddFirst + ";" + sumAddMid + ";" + sumAddEnd);
		sR.println("100000;" + sumRemFirst + ";" + sumRemMid + ";" + sumRemEnd);

		// -----------250_000-----------
		// first add
		start = System.nanoTime();
		for (int i = 0; i <= 250_000; i++) {
			compareList.add(0, 100);
		}
		end = System.nanoTime();
		sumAddFirst = end - start;

		// first remove
		start = System.nanoTime();
		while (compareList.size() > 0)
			compareList.remove(0);
		end = System.nanoTime();
		sumRemFirst = end - start;

		// mid
		start = System.nanoTime();
		compareList.add(0, 10);
		for (int i = 1; i <= 250_000; i++) {
			compareList.add(rand.nextInt(compareList.size()) - 1, 100);
		}
		end = System.nanoTime();
		sumAddMid = end - start;

		// mid remove
		start = System.nanoTime();
		while (compareList.size() > 2)
			compareList.remove(rand.nextInt(compareList.size()) - 1);
		while (compareList.size() > 0)
			compareList.remove(0);
		end = System.nanoTime();
		sumRemFirst = end - start;

		// end
		start = System.nanoTime();
		for (int i = 0; i <= 250_000; i++) {
			compareList.add(compareList.size() - 1, 100);
		}
		end = System.nanoTime();
		sumAddEnd = end - start;

		// end remove
		start = System.nanoTime();
		compareList.remove(compareList.size() - 1);
		end = System.nanoTime();
		sumRemFirst = end - start;
		sA.println("250000;" + sumAddFirst + ";" + sumAddMid + ";" + sumAddEnd);
		sR.println("250000;" + sumRemFirst + ";" + sumRemMid + ";" + sumRemEnd);

	}

	private static long buildListFromFirst(int length) {
		A6DoublyLinkedList<Integer> list = new A6DoublyLinkedList<Integer>();
		long start, end;
		long sum = 0;
		for (int i = 0; i < length; i++) {
			start = System.nanoTime();
			list.add(0, Utilities.randomIntBetween(0, 100));
			end = System.nanoTime();
			sum += end - start;
		}
		return sum;
	}

	private static long buildFromLast(int length) {
		A6DoublyLinkedList<Integer> list = new A6DoublyLinkedList<Integer>();
		long start, end;
		long sum = 0;
		for (int i = 0; i < length; i++) {
			start = System.nanoTime();
			list.add(list.size(), Utilities.randomIntBetween(0, 100));
			end = System.nanoTime();
			sum += end - start;
		}
		return sum;
	}

	private static long buildFromRandomPosition(int length) {
		A6DoublyLinkedList<Integer> list = new A6DoublyLinkedList<Integer>();
		long start, end;
		long sum = 0;
		list.add(0, Utilities.randomIntBetween(0, 100));
		list.add(1, Utilities.randomIntBetween(0, 100));
		for (int i = 2; i < length; i++) {
			start = System.nanoTime();
			list.add(Utilities.randomIntBetween(0, list.size()), Utilities.randomIntBetween(0, 100));
			end = System.nanoTime();
			sum += end - start;
		}
		return sum;
	}

	private static long removeAllFromFirst(A6DoublyLinkedList<Integer> list) {
		long start = System.nanoTime();
		while (list.size() > 0) {
			list.remove(0);
		}
		long end = System.nanoTime();
		return end - start;
	}

	private static long removeAllFromEnd(A6DoublyLinkedList<Integer> list) {
		long start = System.nanoTime();
		while (list.size() > 0) {
			list.remove(list.size() - 1);
		}
		long end = System.nanoTime();
		return end - start;
	}

	private static long removeAllFromMid(A6DoublyLinkedList<Integer> list) {
		Random rand = new Random();
		long start = System.nanoTime();
		while (list.size() > 2)
			list.remove(rand.nextInt(list.size()));
		while (list.size() > 0)
			list.remove(0);
		long end = System.nanoTime();
		return end - start;
	}

	private static void removeTiming() {
		SimpleWriter s = new SimpleWriter1L("removeTiming.txt");
		A6DoublyLinkedList<Integer> first = new A6DoublyLinkedList<Integer>();
		A6DoublyLinkedList<Integer> mid = new A6DoublyLinkedList<Integer>();
		A6DoublyLinkedList<Integer> end = new A6DoublyLinkedList<Integer>();

		for (int i = 0; i <= 10; i++) {
			first.add(0, Utilities.randomIntBetween(0, 100));
			mid.add(0, Utilities.randomIntBetween(0, 100));
			end.add(0, Utilities.randomIntBetween(0, 100));
		}
		s.println("RemoveN;First;End;Mid");
		s.println("10;" + removeAllFromFirst(first) + ";" + removeAllFromEnd(end) + ";" + removeAllFromMid(mid));

		for (int i = 0; i <= 100; i++) {
			first.add(0, Utilities.randomIntBetween(0, 100));
			mid.add(0, Utilities.randomIntBetween(0, 100));
			end.add(0, Utilities.randomIntBetween(0, 100));
		}
		s.println("RemoveN;First;End;Mid");
		s.println("100;" + removeAllFromFirst(first) + ";" + removeAllFromEnd(end) + ";" + removeAllFromMid(mid));

		for (int i = 0; i < 1_000; i++) {
			first.add(0, Utilities.randomIntBetween(0, 100));
			mid.add(0, Utilities.randomIntBetween(0, 100));
			end.add(0, Utilities.randomIntBetween(0, 100));
		}
		s.println("RemoveN;First;End;Mid");
		s.println("1000;" + removeAllFromFirst(first) + ";" + removeAllFromEnd(end) + ";" + removeAllFromMid(mid));

		for (int i = 0; i <= 10_000; i++) {
			first.add(0, Utilities.randomIntBetween(0, 100));
			mid.add(0, Utilities.randomIntBetween(0, 100));
			end.add(0, Utilities.randomIntBetween(0, 100));
		}

		s.println("RemoveN;First;End;Mid");
		s.println("10000;" + removeAllFromFirst(first) + ";" + removeAllFromEnd(end) + ";" + removeAllFromMid(mid));

		for (int i = 0; i < 100_000; i++) {
			first.add(0, Utilities.randomIntBetween(0, 100));
			mid.add(0, Utilities.randomIntBetween(0, 100));
			end.add(0, Utilities.randomIntBetween(0, 100));
		}
		s.println("RemoveN;First;End;Mid");
		s.println("100000;" + removeAllFromFirst(first) + ";" + removeAllFromEnd(end) + ";" + removeAllFromMid(mid));

		for (int i = 0; i < 1_000_000; i++) {
			first.add(0, Utilities.randomIntBetween(0, 100));
			mid.add(0, Utilities.randomIntBetween(0, 100));
			end.add(0, Utilities.randomIntBetween(0, 100));
		}
		s.println("RemoveN;First;End;Mid");
		s.println("1000000;" + removeAllFromFirst(first) + ";" + removeAllFromEnd(end) + ";" + removeAllFromMid(mid));

	}

	private static void reverseTiming() {
		SimpleWriter s = new SimpleWriter1L("reverseList.txt");
		s.println("reverseN;ListOnArray;A6List");

		for (int i = 10; i <= 100_000; i *= 10) {
			System.out.println(i);
			A6DoublyLinkedList<Integer> a6List = new A6DoublyLinkedList<Integer>();
			DoublyLinkedList<Integer> listOnArray = new DoublyLinkedList<Integer>();
			for (int j = 0; j < i; j++) {
				a6List.add(5);
				listOnArray.add(10);
			}
			long a6Start = System.nanoTime();
			a6List.reverse();
			long a6End = System.nanoTime();
			long arrayStart = System.nanoTime();
			listOnArray.reverse();
			long arrayEnd = System.nanoTime();
			s.println(i + ";" + (arrayEnd - arrayStart) + ";" + (a6End - a6Start));
		}
		A6DoublyLinkedList<Integer> a6List = new A6DoublyLinkedList<Integer>();
		DoublyLinkedList<Integer> listOnArray = new DoublyLinkedList<Integer>();
		for (int j = 0; j < 250_000; j++) {
			a6List.add(5);
			listOnArray.add(10);
		}
		long a6Start = System.nanoTime();
		a6List.reverse();
		long a6End = System.nanoTime();
		long arrayStart = System.nanoTime();
		listOnArray.reverse();
		long arrayEnd = System.nanoTime();
		s.println(250_000 + ";" + (arrayEnd - arrayStart) + ";" + (a6End - a6Start));
	}

	public static void main(String[] args) throws InterruptedException {
		// warms up the Java VM
		TimeUnit.SECONDS.sleep(10);

		timeGiven();

		// A6DoublyLinkedList<Integer> aList = new A6DoublyLinkedList<>();
		// printTest(aList, 1_000_000, 250_000);

		// SimpleWriter s = new SimpleWriter1L("linkedListRemove10_000.txt");
		//	s.println("N;first;end;mid");
		//	for(int i = 0; i <= 100_000; i += 10_000) {
		//	s.println(i + ";" + buildListFromFirst(i) + ";" + buildFromLast(i) + ";" + buildFromRandomPosition(i));
		//	}

		//	removeTiming();

		reverseTiming();

	}
}