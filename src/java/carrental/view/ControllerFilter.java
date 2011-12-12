package carrental.view;

import carrental.util.Message;
import carrental.util.Messages;
import java.io.IOException;
import java.util.List;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author skvaros
 */
public class ControllerFilter implements Filter {

	private FilterConfig filterConfig = null;

	public ControllerFilter() {
	}

	/**
	 *
	 * @param request The servlet request we are processing
	 * @param response The servlet response we are creating
	 * @param chain The filter chain we are processing
	 *
	 * @exception IOException if an input/output error occurs
	 * @exception ServletException if a servlet error occurs
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain)
			throws IOException, ServletException {
		RequestLocator.set((HttpServletRequest) request);

		// store all flash messages from session to request
		if (request instanceof HttpServletRequest) {
			HttpServletRequest httpRequest = (HttpServletRequest) request;
			HttpSession session = httpRequest.getSession(false);
			if (session != null) {
				List<Message> flashParams = (List<Message>)
						session.getAttribute(Messages.FLASH_SESSION_KEY);
					request.setAttribute(Messages.FLASH_REQUEST_KEY, flashParams);
					session.removeAttribute(Messages.FLASH_SESSION_KEY);
				}
		}

		chain.doFilter(request, response);
	}

	/**
	 * Return the filter configuration object for this filter.
	 */
	public FilterConfig getFilterConfig() {
		return (this.filterConfig);
	}

	/**
	 * Set the filter configuration object for this filter.
	 *
	 * @param filterConfig The filter configuration object
	 */
	public void setFilterConfig(FilterConfig filterConfig) {
		this.filterConfig = filterConfig;
	}

	/**
	 * Destroy method for this filter
	 */
	public void destroy() {
	}

	/**
	 * Init method for this filter
	 */
	public void init(FilterConfig filterConfig) {
		this.filterConfig = filterConfig;
	}
}
