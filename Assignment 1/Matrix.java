package assignment01;

/**
 * Represents a 2-d integer matrix as a 2-d int array.
 * 
 * @author Harrison Smith
 * @date August 25, 2019
 *
 */
public class Matrix {
	/**
	 * Holds the number of rows of this matrix
	 */
	private int numRows;
	/**
	 * Holds the number of columns of this matrix
	 */
	private int numColumns;
	/**
	 * Holds the actual data of this matrix
	 */
	private int data[][];

	/**
	 * Constructor from a 2D array -- automatically determines the dimensions.
	 * 
	 * @param d 2D array to construct the matrix
	 * @throws IllegalArgumentException if {@code d} is empty or null
	 */
	public Matrix(int d[][]) throws IllegalArgumentException {
		if (d == null || d.length == 0) {
			throw new IllegalArgumentException();
		}
		this.numRows = d.length; // d.length is the number of 1D arrays in the 2D array
		this.numColumns = d[0].length; // d[0] is the first 1D array

		// create a new matrix to hold the data
		this.data = new int[this.getRows()][this.getCols()];
		// copy the data over
		for (int i = 0; i < this.getRows(); i++) {
			for (int j = 0; j < this.getCols(); j++) {
				this.setData(i, j, d[i][j]);
			}
		}
	}

	/**
	 * This method is used to get the row size of the Matrix
	 * 
	 * @return the length of the rows of the Matrix
	 */
	public int getRows() {
		return this.numRows;
	}

	/**
	 * This method is used to get the column size of the Matrix
	 * 
	 * @return the length of the columns of the Matrix
	 */
	public int getCols() {
		return this.numColumns;
	}

	/**
	 * This method retrieves the data stored within the Matrix on a specific column,
	 * and row
	 * 
	 * @param row is the row
	 * @param col is the column
	 * @return the data contained in the row and column of the Matrix that called
	 *         this method
	 */
	public int getData(int row, int col) throws ArrayIndexOutOfBoundsException {
		return data[row][col];
	}

	/**
	 * This method sets the data within the Matrix on a specific row and column
	 * within the Matrix
	 * 
	 * @param row  tells the method which row to access in the Matrix
	 * @param col  tells the method which column to access in the Matrix
	 * @param data passes in the data to be set in the Matrix
	 */
	public void setData(int row, int col, int data) throws ArrayIndexOutOfBoundsException {
		this.data[row][col] = data;
	}
	
	/**
	 * This method will check two Matrices to see if they are equal in dimensions
	 * and equal in contents in every location of the matrix
	 * 
	 * @param o is an Object that will be checked to confirm that the Object is an
	 *          instance of Matrix, if the Object is not an instance of Matrix then
	 *          we will return false
	 */
	@Override
	public boolean equals(Object o) {
		if (o == null) {
			return false;
			// we return false because did not properly cast the Matrix into the
		}
		if (!(o instanceof Matrix)) { // make sure the Object we're comparing to is a Matrix
			return false;
		}
		Matrix m = (Matrix) o; // if the above was not true, we know it's safe to treat 'o' as a Matrix

		// we check to see if the left object is an instance of Matrix
		if (!(this instanceof Matrix)) {
			return false;
		}

		// checks to see if there are the same columns and rows, returns false if they
		// do not match in length
		if (this.getRows() != m.getRows() || this.getCols() != m.getCols()) {
			return false;
		} else {
			for (int i = 0; i < this.getRows(); i++) {
				for (int j = 0; j < this.getCols(); j++) {
					if (this.getData(i, j) != m.getData(i, j)) {
						return false;
					}
				}
			}
			return true;
		}
	}

	@Override
	/**
	 * Takes the Matrix and converts it a string that can be printed out
	 * 
	 * After each value has been added in the row it will increment the "row" in the
	 * String by utilizing "\n"
	 */
	public String toString() {
		String mString = "";
		if (this instanceof Matrix) {
			for (int i = 0; i < this.getRows(); i++) {
				for (int j = 0; j < this.getCols(); j++) {
					mString += this.getData(i, j) + " ";
				}
				mString += "\n";
			}
		}
		return mString;
	}

	/**
	 * Multiplies {@code this} by {@code m} and returns the result as a new
	 * {@code Matrix}. If {@code m} does not have appropriate dimensions to multiply
	 * to {@code this}, it throws an {@code IllegalArgumentException}.
	 * 
	 * @param m matrix to multiply {@code this} with
	 * @return result of the product
	 * 
	 * @throws IllegalArgumentException if dimensions of the matrices are not
	 *                                  compatible
	 */
	public Matrix times(Matrix m) throws IllegalArgumentException {
		Matrix timesM;
		if (this.getCols() != m.getRows()) {
			throw new IllegalArgumentException();
		} else {
			// defines the appropriate dimensions of the times'd matrix
			int[][] dimensions = new int[this.getRows()][m.getCols()];
			// initializes the new matrix
			timesM = new Matrix(dimensions);

			// the following for loops will loop through the two matrices and multiply the
			// first matrix's columns by the second matrix's columns to construct the
			// product matrix
			// this is to go through the rows in matrix 1
			for (int matrix1row = 0; matrix1row < this.getRows(); matrix1row++) {
				// this is to track the columns in matrix 2 and go through it
				for (int matrix2col = 0; matrix2col < m.getCols(); matrix2col++) {
					// int i is used to track the columns of matrix 1, and the rows of matrix 2
					for (int i = 0; i < m.getRows(); i++) {
						timesM.setData(matrix1row, matrix2col, (timesM.getData(matrix1row, matrix2col)
								+ (this.getData(matrix1row, i) * m.getData(i, matrix2col))));
					}

				}
			} // end of loops and nested loops

		} // end of else loop
			// returns the times Matrix after it has been times and populated
		return timesM;
	}

	/**
	 * Adds {@code m} to {@code this} and returns the result as a new
	 * {@code Matrix}. If {@code m} does not have the same dimensions as
	 * {@code this}, it throws an {@code IllegalArgumentException}.
	 * 
	 * @param m matrix to add to {@code this} with
	 * @return result of the addition
	 *
	 * @throws IllegalArgumentException if dimensions of the matrices are not
	 *                                  compatible
	 */
	public Matrix plus(Matrix m) throws IllegalArgumentException {
		Matrix plusM;
		if (this.getRows() != m.getRows() || this.getCols() != m.getCols()) {
			throw new IllegalArgumentException();
		} else {
			int[][] dimensions = new int[this.getRows()][this.getCols()];
			plusM = new Matrix(dimensions);
			for (int i = 0; i < plusM.getRows(); i++) {
				for (int j = 0; j < plusM.getCols(); j++) {
					plusM.setData(i, j, (this.getData(i, j) + m.getData(i, j)));
				}
			}
		}

		return plusM;
	}
}
