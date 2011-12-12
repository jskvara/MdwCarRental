package carrental.view;

/**
 * String util
 */
public final class StringUtil {

	private StringUtil() {
	}

	/**
	 * Determines if the text is empty.
	 *
	 * @param text
	 *            the text
	 * @return whether text is empty
	 */
	public static boolean isEmpty(String text) {
		return text == null || text.length() == 0;
	}

	/**
	 * Converts the object into string.
	 *
	 * @param o
	 *            the object
	 * @return string
	 */
	public static String toString(Object o) {
		return o == null ? null : o.toString();
	}
}