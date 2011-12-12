package carrental.util;

import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;

/**
 * Request parameters map
 */
public class RequestMap extends HashMap<String, String> {

	private HttpServletRequest request;

	public RequestMap(HttpServletRequest request) {
		this.request = request;
	}

	@Override
	public String get(Object key) {
		return request.getParameter(obj2Str(key));
	}

	protected String obj2Str(Object o) {
		return o == null ? null : o.toString();
	}
}
