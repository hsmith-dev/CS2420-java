package assignment08;

public class A08String {
	private String str;

	public A08String() {
		this.str = "";
	}

	public A08String(String str) {
		this.str = str;
	}

	@Override
	public String toString() {
		return str;
	}

	@Override
	public boolean equals(Object other) {
		if (other == null)
			return false;
		if (this == other)
			return true;
		if (!(other instanceof A08String))
			return false;
		A08String that = (A08String) other;
		return this.str.equals(that.str);
	}

	@Override
	public int hashCode() {
		// converts string to char array
		char[] strChar = str.toCharArray();
		int value = 0;
		for (int i = 0; i < str.length() / 2; i++) {
			// adds the first half of the character's values together
			value += strChar[i];
		}
		// returns the first four values together multiplied by a prime number
		return value * 3;
	}
}
