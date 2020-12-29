package assignment08;

import java.util.Iterator;

import components.set.Set;
import components.set.SetOnArrayList;
import components.set.SetOnHashTable;

/**
 * Hash Table based implementation of a Set.
 * 
 * @author Harrison Smith
 * @author Ben Malohi
 *
 * @param <E> type of the elements of this set
 */
public class HashTableSet<E> {

	/*
	 * Private members -----
	 */
	private static final int DEFAULT_NUM_BUCKETS = 101;
	private Set<E>[] hashTable;
	private int size;

	/*
	 * Helper methods
	 */
	/**
	 * Computes {@code a} mod {@code b} as % should have been defined to work.
	 *
	 * @param a the number being reduced
	 * @param b the modulus
	 * @return the result of a mod b, which satisfies {@code 0 <=  mod < b}
	 * @requires b > 0
	 */
	public static int mod(int a, int b) {
		assert b > 0 : "Violation of: b > 0";
		int modNum = a % b;
		if (modNum < 0)
			modNum += b;
		return modNum;
	}

	/*
	 * Constructors -----
	 */
	/**
	 * No-argument constructor, resulting in a hash table of default size.
	 */
	public HashTableSet() {
		this(DEFAULT_NUM_BUCKETS);
	}

	/**
	 * Constructor resulting in a hash table of size {@code hashTableSize}.
	 *
	 * @param hashTableSize size of hash table
	 */
	public HashTableSet(int hashTableSize) {
		hashTable = (Set<E>[]) new Set[hashTableSize];
		clear();
	}

	/*
	 * Set methods -----
	 */
	/**
	 * Adds {@code x} to {@code this}.
	 *
	 * @param x the element to be added
	 * 
	 * @modifies {@code this}
	 * 
	 * @requires {@code x} is not in {@code this}
	 * 
	 */
	public void add(E x) {
		assert x != null : "Violation of: x is not null";
		assert !this.contains(x) : "Violation of: x is not in this";

		int bucket = mod(x.hashCode(), this.hashTable.length);
		this.hashTable[bucket].add(x);
		this.size++;
	}

	/**
	 * Removes {@code x} from this.
	 *
	 * @param x the element to be removed
	 *
	 * @modifies {@code this}
	 * 
	 * @requires {@code x} is in {@code this}
	 * 
	 */
	public void remove(E x) {
		assert x != null : "Violation of: x is not null";
		assert this.contains(x) : "Violation of: x is in this";

		int bucket = mod(x.hashCode(), this.hashTable.length);
		this.hashTable[bucket].remove(x);
		// maybe move size before ?
		this.size--;
	}

	/**
	 * Reports whether {@code x} is in {@code this}.
	 *
	 * @param x the element to be checked
	 * @return true iff element is in {@code this}
	 * 
	 */
	public boolean contains(E x) {
		assert x != null : "Violation of: x is not null";
		int bucket = mod(x.hashCode(), this.hashTable.length);
		return this.hashTable[bucket].contains(x);
	}

	/**
	 * Reports the number of elements in {@code this}.
	 * 
	 * @return size of this set
	 */
	public int size() {
		return size;
	}

	/**
	 * Removes all the elements in {@code this}.
	 */
	public void clear() {
		for (int i = 0; i < hashTable.length; i++) {
			hashTable[i] = new SetOnArrayList<E>();
		}
		this.size = 0;
	}

	/*
	 * Methods inherited from Object
	 */
	/**
	 * String representation of the hash table. Elements in bucket 0, followed by
	 * those in bucket 1, and so on, separated by commas and enclosed in {..}.
	 */
	@Override
	public String toString() {
		String s = "{";
		// loops through each hash table bucket
		for (int i = 0; i < hashTable.length; i++) {
			// iterate through each bucket
			Iterator<E> iter = hashTable[i].iterator();
			while (iter.hasNext())
				s += iter.next() + ",";
		}
		
		// removes last comma
		if(s.length() > 1)
			s = s.substring(0, s.length() - 1);
		return s + "}";
	}

	/*
	 * Other methods specific to hash tables; for testing/performance purposes only
	 */
	/**
	 * Returns the number of elements in the specified bucket.
	 * 
	 * @param bucketIndex index of the bucket requested
	 * @requires 0 <= bucketIndex < numBuckets()
	 */
	public int bucketSize(int bucketIndex) {
		int count = 0;
		Iterator<E> iter = hashTable[bucketIndex].iterator();
		while(iter.hasNext()) {
			count ++;
			iter.next();
		}
		return count;
	}

	/**
	 * Reports the number of buckets in this hashtable.
	 * 
	 * @return number of buckets
	 */
	public int numBuckets() {
		return hashTable.length;
	}

}
