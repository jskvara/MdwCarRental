package carrental.view;

/**
 * Html util
 */
public final class HtmlUtil {

	private static final int HIGHEST_SPECIAL = '>';
	private static char[][] specialCharactersRepresentation =
			new char[HIGHEST_SPECIAL + 1][];

	static {
		specialCharactersRepresentation['&'] = "&amp;".toCharArray();
		specialCharactersRepresentation['<'] = "&lt;".toCharArray();
		specialCharactersRepresentation['>'] = "&gt;".toCharArray();
		specialCharactersRepresentation['"'] = "&#034;".toCharArray();
		specialCharactersRepresentation['\''] = "&#039;".toCharArray();
	}

	private HtmlUtil() {
	}

	/**
	 * Escapes string that could be interpreted as HTML.
	 * @param input
	 * @return the escaped value
	 */
	public static String escape(String input) {
		int start = 0;
		char[] arrayBuffer = input.toCharArray();
		int length = arrayBuffer.length;
		StringBuilder escapedBuffer = null;
		for (int i = 0; i < length; i++) {
			char c = arrayBuffer[i];
			if (c <= HIGHEST_SPECIAL) {
				char[] escaped = specialCharactersRepresentation[c];
				if (escaped != null) {
					if (start == 0) {
						escapedBuffer = new StringBuilder(length + 5);
					}
					if (start < i) {
						escapedBuffer.append(arrayBuffer, start, i - start);
					}
					start = i + 1;
					escapedBuffer.append(escaped);
				}
			}
		}
		if (start == 0) {
			return input;
		}
		if (start < length) {
			escapedBuffer.append(arrayBuffer, start, length - start);
		}
		return escapedBuffer.toString();
	}
}