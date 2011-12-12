package carrental.controller.servlet.address;

import java.io.IOException;
import javax.servlet.ServletException;

/**
 * Address servlet
 */
public class AddressServlet extends AbstractAddressServlet {

	@Override
	protected void processRequest() throws ServletException, IOException {
		request.setAttribute("items", addressService.getAll());
		forward("/carrental/address.jsp");
	}
}
