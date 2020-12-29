package assignment05;

/**
 * @author Sam Hancock - u0966409
 * @author Harrison Smith - u0901395
 *
 */
public enum ComplexityClass {
	LOGN("O(log n)"), N("O(n)"), NLOGN("O(n log n)"), NSQUARED("O(n^2)");
	private String value;

	private ComplexityClass(String value) {
		this.value = value;
	}

	public String value() {
		return value;
	}

	@Override
	public String toString() {
		return value;
	}
}
