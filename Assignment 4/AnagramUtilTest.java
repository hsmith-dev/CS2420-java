package assignment04;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.junit.Test;

import assignment04.AnagramUtil;

/**
 * 
 * @author Samuel Hancock
 * @author Harrison Smith
 * 
 * @date September 16, 2019
 * 
 *         Assignment04-Anagrams
 *
 */
public class AnagramUtilTest {

	@Test
	public void testAreAnagramsInsertionSortAndSort() {
		String ana1 = "abe";
		String ana2 = "bae";
		
		boolean ana = AnagramUtil.areAnagrams(ana1, ana2);
		assertEquals(true, ana);
		String caseAna2 = "Bae";
		boolean capAna = AnagramUtil.areAnagrams(ana1, caseAna2);
		assertEquals(true, capAna);
		String numAna = "321";
		String numAna2 = "132";
		
		boolean numAre = AnagramUtil.areAnagrams(numAna, numAna2);
		assertEquals(true, numAre);
	}
	
	@Test
	public void testSortAndInsertionSort() {
		String expected = "ams";
		String ana1 = AnagramUtil.sort("Sam");
		boolean answ = expected.equals(ana1);
		assertEquals(true, answ);
	}
	@Test
	public void testGetLargestAnagram() {
		List<String> expected = new ArrayList<>();
		expected.add("parses");
		expected.add("passer");
		expected.add("spares");
		expected.add("sparse");
		expected.add("spears");
		List<String> actual = AnagramUtil.getLargestAnagramGroup("data/twoAnagramLists.txt");
		assertEquals(expected, actual);
	}
	
	@Test
	public void testLargestAnagramWithExpectedWordsTxtFile() {
		List <String> expected = new ArrayList<>();
		expected.add("carets");
		expected.add("Caters");
		expected.add("caster");
		expected.add("crates");
		expected.add("Reacts");
		expected.add("recast");
		expected.add("traces");
		List <String> actual = AnagramUtil.getLargestAnagramGroup("data/words.txt");
		assertEquals(expected, actual);
	}
	
	
	@Test
	public void testLargestAnagramWithExpectedModerateWordListTxtFile() {
		List <String> expected = new ArrayList<>();
		expected.add("act");
		expected.add("cat");
		List <String> actual = AnagramUtil.getLargestAnagramGroup("data/moderate_word_list.txt");
		assertEquals(expected, actual);
	}
	
	@Test
	public void testInsertionSort() {
		Comparator<Character> comp = new Comparator<>() {

			@Override
			public int compare(Character o1, Character o2) {
				return ((Character) Character.toLowerCase(o1)).compareTo(Character.toLowerCase(o2));
			}
		};
		
		List<Character> toSort = new ArrayList<>();
		toSort.add('c');
		toSort.add('a');
		toSort.add('b');
		
		AnagramUtil.insertionSort(toSort, comp);
		
		List<Character> expected = new ArrayList<>();
		expected.add('a');
		expected.add('b');
		expected.add('c');
		
		assertEquals(expected, toSort);
		

	}

}
