package carrental.controller.servlet.address;

import carrental.model.entity.AddressEntity;
import carrental.model.service.ServiceException;
import carrental.util.Message;
import carrental.util.RequestMap;
import java.io.IOException;
import javax.servlet.ServletException;

/**
 * Save address
 */
public class AddressSaveServlet extends AbstractAddressServlet {

	@Override
	protected void processRequest() throws ServletException, IOException {
		if(!checkAdmin()) {
			return;
		}

		String submit = request.getParameter("submit");
		if (submit != null) {
			try {
				addressService.save(new RequestMap(request));
				redirect("/address", "Address was saved.");
				return;
			} catch (ServiceException ex) {
				forward("/carrental/addressSave.jsp", ex.getMessage(), Message.ERROR);
			}
		}

		if (request.getParameter("id") != null) {
			AddressEntity addressEntity = getAddressEntity();
			request.setAttribute("id", addressEntity.getId());
			request.setAttribute("city", addressEntity.getCity());
			request.setAttribute("country", addressEntity.getCountry());
			request.setAttribute("postalNumber", addressEntity.getPostalNumber());
			request.setAttribute("street", addressEntity.getStreet());
		}

		forward("/carrental/addressSave.jsp");
	}
}
