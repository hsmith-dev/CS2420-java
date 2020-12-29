package assignment01;

import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * This class is used to test my Matrix implementation, some previously provided
 * by the professor with more added by myself
 * 
 * @author Harrison Smith
 * @date August 25, 2019
 *
 */
public class MatrixTester {
	public static void main(String[] args) {
		SimpleWriter out = new SimpleWriter1L();

		/*
		 * Test case 1: constructing a matrix from an empty array should throw an
		 * IllegalArgumentException.
		 */
		int[][] d = new int[0][0];
		boolean caughtRightException = false;
		Matrix m;
		try {
			m = new Matrix(d);
		} catch (IllegalArgumentException e) {
			caughtRightException = true;
		}
		if (!caughtRightException) {
			out.print("Fail: constructing a matrix from an empty array");
			caughtRightException = false; // reset for next such test case
		}

		/*
		 * Test case 2: a valid matrix multiplication
		 */
		Matrix m1 = new Matrix(new int[][] { { 1, 2, 3 }, { 2, 5, 6 } });
		/*
		 * Testing toString
		 */
		String m1StrExp = "1 2 3 \n2 5 6 \n";
		String m1StrActual = m1.toString();
		if (!m1StrExp.equals(m1StrActual)) {
			out.println("toString Error.\nGot:\n" + m1StrActual + "\nExpected:\n" + m1StrExp);
		}

		/*
		 * Testing equals
		 */
		Matrix m1Expected = new Matrix(new int[][] { { 1, 2, 3 }, { 2, 5, 6 } });
		if (!m1Expected.equals(m1)) {
			out.println("Equals error: m1 and m1Expected not equal\nGot:" + m1 + "\nexpected:" + m1Expected + ")");
		}

		/*
		 * Testing times (!! Pun unintended !!)
		 */
		Matrix m2 = new Matrix(new int[][] { { 4, 5 }, { 3, 2 }, { 1, 1 } });
		Matrix actualProduct = m1.times(m2);
		Matrix expectedProduct = new Matrix(new int[][] { { 13, 12 }, { 29, 26 } }); // we know this from math
		if (!expectedProduct.equals(actualProduct)) {
			out.println("equals error (m1 * m2 not equal to expected result)");
		}

		/*
		 * Testing plus and continues to test equals
		 * 
		 * Test 1: PLUS
		 */
		actualProduct = m1.plus(m1Expected);
		expectedProduct = new Matrix(new int[][] { { 2, 4, 6 }, { 4, 10, 12 } });
		if (!expectedProduct.equals(actualProduct))
			out.println("--- PLUS ERROR ---\nExpected:" + expectedProduct.toString() + "\nResult:"
					+ actualProduct.toString());
		/*
		 * Test 2: PLUS
		 */
		m1 = new Matrix(new int[][] { { 1, 2, 3, 52 }, { 1_000, 100, 500, 10_000 } });
		m2 = new Matrix(new int[][] { { 4, 5, 10, 20 }, { 3, 2, 1, 1 } });
		actualProduct = m1.plus(m2);
		expectedProduct = new Matrix(new int[][] { { 5, 7, 13, 72 }, { 1003, 102, 501, 10001 } });
		if (!expectedProduct.equals(actualProduct))
			out.println("--- PLUS ERROR ---\nExpected\n" + expectedProduct.toString() + "\nResult:\n"
					+ actualProduct.toString());

		/*
		 * Testing Equals -- should result in a failed equals as Matrix 1 does not equal
		 * Matrix 2
		 */
		if (m1.equals(m2))
			out.println("--- EQUALS ERROR ---\nM1 does NOT equal M2\nM1:" + m1.toString() + "\nM2:\n" + m2.toString());

		/*
		 * Explicit test for Equals
		 */
		if (!m1.equals(m1))
			out.println("--- EQUALS ERROR ---\nComparing the same Matrix, should not fail");

		/*
		 * Testing Times
		 */
		m2 = new Matrix(new int[][] { { 4, 5 }, { 3, 2 } });
		actualProduct = m2.times(m1);
		expectedProduct = new Matrix(new int[][] { { 5004, 508, 2512, 50_208 }, { 2003, 206, 1009, 20_156 } });
		if (!expectedProduct.equals(actualProduct))
			out.println("--- TIMES ERROR ---\nExpected:\n" + expectedProduct.toString() + "\nActual:\n"
					+ actualProduct.toString());

		/*
		 * Test 2 for toString
		 */
		m1 = new Matrix(new int[][] { { 1000, 30 }, { 0, 0 } });
		m1StrExp = "1000 30 \n0 0 \n";
		m1StrActual = m1.toString();
		if (!m1StrExp.equals(m1StrActual)) {
			out.println("toString Error.\nGot:\n" + m1StrActual + "\nExpected:\n" + m1StrExp);
		}

		/**
		 * 1 - Testing IllegalArgumentException in "times" method
		 */
		try {
			actualProduct.times(m2);
		}catch (IllegalArgumentException e) {
			out.println("Expected : Yes : times 1 - thrown");
			caughtRightException = true;
		}
		if(! caughtRightException) {
			out.println("Test Failed - Different Exception");
			caughtRightException = false;
		}
		
		/**
		 * 2 - Testing IllegalArgumentException in "times" method
		 */
		try {
			actualProduct.times(m1);
		}catch (IllegalArgumentException e) {
			out.println("Expected : Yes : times 2 - thrown");
			caughtRightException = true;
		}
		if(! caughtRightException) {
			out.println("Test Failed - Different Exception");
			caughtRightException = false;
		}
		
		/**
		 * 1 - Testing IllegalArgumentException in "plus" method
		 */
		try {
			actualProduct.plus(m2);
		}catch (IllegalArgumentException e) {
			out.println("Expected : Yes : plus 1 - thrown");
			caughtRightException = true;
		}
		if(! caughtRightException) {
			out.println("Test Failed - Different Exception");
			caughtRightException = false;
		}
		
		/**
		 * 2 - Testing IllegalArgumentException in "plus" method
		 */
		try {
			actualProduct.plus(m1);
		}catch (IllegalArgumentException e) {
			out.println("Expected : Yes : plus 2 - thrown");
			caughtRightException = true;
		}
		if(! caughtRightException) {
			out.println("Test Failed - Different Exception");
			caughtRightException = false;
		}
		
		/**
		 * Testing ArrayOutOfBoundsException in "setData" method
		 */
		try {
			m1.setData(3, 3, m2.getData(1, 1));
		}catch (ArrayIndexOutOfBoundsException e) {
			out.println("Expected : Yes : setData - thrown");
			caughtRightException = true;
		}
		if(! caughtRightException) {
			out.println("Test Failed - Different Exception");
			caughtRightException = false;
		}
		
		/**
		 * Testing ArrayOutOfBoundsException in "getData" method
		 * 
		 * Error will throw on 
		 */
		try {
			m1.setData(2, 2, m2.getData(5, 5));
		}catch (ArrayIndexOutOfBoundsException e) {
			out.println("Expected : Yes : getData - thrown");
			caughtRightException = true;
		}
		if(! caughtRightException) {
			out.println("Test Failed - Different Exception");
			caughtRightException = false;
		}
		
		/*
		 * Finally close the output stream
		 */
		out.close();
	}
}
