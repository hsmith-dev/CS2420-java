package assignment02;

import static org.junit.Assert.*;

import org.junit.Test;

import components.set.Set;
import components.set.Set1L;

public class SetOnArrayListTest {
	/**
	 * Creates and returns a Set under test, i.e. a set with dynamic type
	 * SetOnArrayList, populated with the given words.
	 * 
	 * @author Harrison Smith
	 * 
	 * @param words Strings to populate the set with
	 * @return generated set with the proper dynamic type
	 */
	private Set<String> createFromArgsUUT(String... words) {
		Set<String> s = new SetOnArrayList<>();
		for (String w : words) {
			if (!s.contains(w)) {
				s.add(w);
			}
		}
		return s;
	}

	/**
	 * Creates and returns a Set for reference, i.e. a set with dynamic type Set1L,
	 * populated with the given words.
	 * 
	 * @param words Strings to populate the set with
	 * @return generated set with the proper dynamic type
	 */
	private Set<String> createFromArgsRef(String... words) {
		Set<String> s = new Set1L<>();
		for (String w : words) {
			if (!s.contains(w)) {
				s.add(w);
			}
		}
		return s;
	}

	@Test
	public final void testAddToEmpty() {
		// Creates and empty set s (SetOnArrayList)
		Set<String> s = createFromArgsUUT();
		// Adds the string "red" to the empty set s
		s.add("red");
		// Creates a Set1L with a string "red" in it
		Set<String> sExpected = createFromArgsRef("red");
		// Checks if the two sets are equal
		assertEquals(s, sExpected);
	}
	
	@Test
	public final void testAddToEmptyString() {
		Set<String> s = createFromArgsUUT();
		s.add("");
		Set<String> sExpected = createFromArgsRef("");
		assertEquals(s, sExpected);
	}
	
	@Test
	public final void testAddEmptyStringRemoveEmptyString() {
		Set<String> s = createFromArgsUUT();
		s.add("");
		s.remove("");
		Set<String> sExpected = createFromArgsRef();
		assertEquals(s, sExpected);
	}
	
	@Test
	public final void testContainsTrue() {
		Set <String> s = createFromArgsUUT("red", "blue", "green");
		assertEquals(s.contains("green"), true);		
	}
	
	@Test
	public final void testContainsFalse() {
		Set <String> s = createFromArgsUUT("red", "blue", "green");
		assertEquals(s.contains("purple"), false);	
	}
	
	@Test
	public final void testContainsEmpty() {
		Set <String> s = createFromArgsUUT();
		assertEquals(s.contains("purple"), false);	
	}
	
	@Test
	public final void testRemoveFromOne() {
		Set <String> s = createFromArgsUUT("kite");
		s.remove("kite");
		Set <String> sExpected = createFromArgsUUT();
		assertEquals(s, sExpected);
	}
	
	@Test
	public final void testRemoveFromMultiple() {
		Set <String> s = createFromArgsUUT("kite", "dog", "street");
		s.remove("street");
		Set <String> sExpected = createFromArgsUUT("kite", "dog");
		assertEquals(s, sExpected);
	}
	
	@Test
	public final void testRemoveFromBeginningAndAddSameString() {
		Set <String> s = createFromArgsUUT("kite", "dog", "street");
		s.remove("kite");
		s.add("kite");
		Set <String> sExpected = createFromArgsUUT("dog", "street", "kite");
		assertEquals(s, sExpected);
	}
	
	@Test
	public final void testSize() {
		Set <String> s = createFromArgsUUT();
		s.add("hello");
		s.add("my");
		s.add("name");
		s.add("is");
		s.add("harrison");
		assertEquals(s.size(), 5);
	}
	
	@Test
	public final void testSizeZero() {
		Set <String> s = createFromArgsUUT();
		assertEquals(s.size(), 0);
	}
	
	@Test
	public final void testClearArray() {
		Set <String> s = createFromArgsUUT("hello", "hey", "food", "green", "eating");
		Set <String> sExpected = createFromArgsUUT();
		s.clear();
		assertEquals(s, sExpected);
	}
	
	@Test
	public final void testClearEmptyArray() {
		Set <String> s = createFromArgsUUT();
		Set <String> sExpected = createFromArgsUUT();
		s.clear();
		assertEquals(s, sExpected);
	}
	
}
