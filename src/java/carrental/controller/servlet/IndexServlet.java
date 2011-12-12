package carrental.controller.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Index Servlet
 */
public class IndexServlet extends BaseServlet {

	protected void processRequest() throws ServletException, IOException {
		request.getRequestDispatcher("/carrental/index.jsp")
			.forward(request, response);
	}
}
