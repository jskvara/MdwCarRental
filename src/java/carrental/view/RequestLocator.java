package carrental.view;

import javax.servlet.http.HttpServletRequest;

/**
 * Request Locator
 */
public final class RequestLocator {

    private static ThreadLocal<HttpServletRequest> requests =
        new ThreadLocal<HttpServletRequest>();

    /**
     * Returns the request attached to the current thread.
     *
     * @return the request attached to the current thread
     */
    public static HttpServletRequest get() {
        return requests.get();
    }

    /**
     * Sets the request to the current thread.
     *
     * @param request
     *            the request
     */
    public static void set(HttpServletRequest request) {
        requests.set(request);
    }

    private RequestLocator() {
    }
}