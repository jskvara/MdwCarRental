package carrental.controller.servlet.address;

import carrental.model.service.ServiceException;
import carrental.util.Message;
import java.io.IOException;
import javax.servlet.ServletException;

/**
 * Delete address
 */
public class AddressDeleteServlet extends AbstractAddressServlet {

	@Override
	protected void processRequest() throws ServletException, IOException {
		if(!checkAdmin()) {
			return;
		}

		Long id = getId();

		try {
			addressService.delete(id);
		} catch (ServiceException ex) {
			redirect("/address", ex.getMessage(), Message.ERROR);
			return;
		}

		redirect("/address", "Address was deleted.");
	}
}
