package assignment05;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import assignment05.QuickSortPivotM3;

/**
 * Calls Sorter Test and instantiates it in Quick Sort Pivot Median 3
 * 
 * @author Sam Hancock
 * @author Harrison Smith
 */
public class QuickSortPivotM3Test {

	private List <Integer> list;
	private final int SIZE = 10;
	private final int MAX = 25;
	
	@Test
	public void test() {
		QuickSortPivotM3<Integer> sorter = new QuickSortPivotM3<>();
		
		list = new ArrayList<>();
		
		list.add(2);
		list.add(4);
		list.add(3);
		list.add(5);
		list.add(-12); 
		list.add(1);
		
		sorter.sort(list);
		
		System.out.println(list);
		
		boolean flag = true;
		for(int i = 0; i < list.size() - 2; i++) {
			if(list.get(i) > list.get(i + 1)) {
				flag = false;
			}
		}
		assertEquals(true, flag);
	}

}
