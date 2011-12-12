package carrental.controller.servlet;

import carrental.util.Message;
import carrental.util.Messages;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Administrator login required
 */
public abstract class BaseServlet extends HttpServlet {

	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected UserService userService = UserServiceFactory.getUserService();

	protected void setAttributes(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;

		String thisURL = request.getRequestURI();
		if (!userService.isUserLoggedIn()) {
			request.setAttribute("loginUrl", userService.createLoginURL(thisURL));
		} else {
			request.setAttribute("logged", true);
			request.setAttribute("username", userService.getCurrentUser().getEmail());
			request.setAttribute("logoutUrl", userService.createLogoutURL(thisURL));
		}
	}

	protected boolean checkAdmin()
			throws IOException {
		String thisURL = request.getRequestURI();
		if (!userService.isUserLoggedIn()) {
			String loginUrl = userService.createLoginURL(thisURL);
			response.sendRedirect(loginUrl);
			return false;
		}

		if (!userService.isUserAdmin()) {
			Messages.setSessionMessage("You don't have a permission for this action", Message.ERROR);
			response.sendRedirect("/vehicle");
			return false;
		}

		return true;
	}

	protected void forward(String url) throws ServletException, IOException {
		request.getRequestDispatcher(url).forward(request, response);
	}

	protected void forward(String url, String message)
			throws ServletException, IOException {
		Messages.setRequestMessage(message);
		request.getRequestDispatcher(url).forward(request, response);
	}

	protected void forward(String url, String message, String type)
			throws ServletException, IOException {
		Messages.setRequestMessage(message, type);
		request.getRequestDispatcher(url).forward(request, response);
	}

	protected void redirect(String url) throws IOException {
		response.sendRedirect(url);
	}

	protected void redirect(String url, String message) throws IOException {
		Messages.setSessionMessage(message);
		redirect(url);
	}

	protected void redirect(String url, String message, String type) throws IOException {
		Messages.setSessionMessage(message, type);
		redirect(url);
	}

	protected Long getId(String parameterName, String redirectTo) throws IOException {
		Long id = null;
		String stringId = request.getParameter(parameterName);
		if (stringId == null || "".equals(stringId.trim())) {
			redirect(redirectTo, "Wrong parameter.", Message.ERROR);
			return null;
		}

		try {
			id = Long.valueOf(stringId);
		} catch (NumberFormatException ex) {
			redirect(redirectTo, "Parameter must be number.", Message.ERROR);
			return null;
		}

		return id;
	}

	abstract protected void processRequest() throws ServletException, IOException;

	protected void doRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		setAttributes(request, response);

		processRequest();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doRequest(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doRequest(request, response);
	}
}
