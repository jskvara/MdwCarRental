package carrental.view;

import carrental.util.Messages;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import java.util.Iterator;
import javax.servlet.http.HttpServletRequest;

/**
 * Jsp helper functions
 */
public final class Functions {

	private Functions() {
	}

	/**
	 * Encodes the input object. If the object is a string, it is escaped as
	 * HTML. If the object is a key, it is encoded as Base64. Anything else is
	 * converted to a string using toString() method.
	 *
	 * @param input
	 *            the input value
	 * @return the escaped value
	 */
	public static String h(Object input) {
		if (input == null || "".equals(input)) {
			return "";
		}
		if (input.getClass() == String.class) {
			return HtmlUtil.escape(input.toString());
		}
		if (input.getClass() == Key.class) {
			return KeyFactory.keyToString((Key) input);
		}
		return input.toString();
	}

	/**
	 * Returns context-relative URL.
	 *
	 * @param input
	 *            the input value
	 * @return context-relative URL
	 * /
	public static String url(String input) {
		boolean empty = StringUtil.isEmpty(input);
		if (!input.isEmpty() && input.indexOf(':') >= 0) {
			return input;
		}
		HttpServletRequest request = request();
		String contextPath = request.getContextPath();
		StringBuilder sb = new StringBuilder(50);
		if (contextPath.length() > 1) {
			sb.append(contextPath);
		}

		String path = RequestUtil.getPath(request);
		int pos = path.lastIndexOf('/');
		path = path.substring(0, pos + 1);
		if (empty) {
			sb.append(path);
		} else if (input.startsWith("/")) {
			sb.append(input);
		} else {
			sb.append(path).append(input);
		}
		return ResponseLocator.get().encodeURL(sb.toString());
	}

	/**
	 * Returns the text tag representation.
	 *
	 * @param name
	 *            the property name
	 * @return the text tag representation
	 * @throws IllegalArgumentException
	 *             if the property name ends with "Array"
	 */
	public static String text(String name) throws IllegalArgumentException {
		HttpServletRequest request = request();
		return "name=\""
				+ name
				+ "\" value=\""
				+ h(request.getAttribute(name))
				+ "\"";
	}

	/**
	 * Returns the hidden tag representation.
	 *
	 * @param name
	 *            the property name
	 * @return the hidden tag representation
	 * @throws IllegalArgumentException
	 *             if the property name ends with "Array"
	 */
	public static String hidden(String name) throws IllegalArgumentException {
		return text(name);
	}

	/**
	 * Returns the current request.
	 *
	 * @return the current request
	 * @throws IllegalStateException
	 *             if the current request does not exists
	 */
	protected static HttpServletRequest request() throws IllegalStateException {
		HttpServletRequest request = RequestLocator.get();
		if (request == null) {
			throw new IllegalStateException(
					"JSP should be called via FrontController.");
		}
		return request;
	}

	public static Iterator getMessages() {
		return Messages.getAllMessages().iterator();
	}
}
