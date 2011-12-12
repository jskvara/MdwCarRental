package carrental.controller.servlet.order;

import carrental.model.service.ServiceException;
import carrental.util.Message;
import java.io.IOException;
import javax.servlet.ServletException;

/**
 * Delete order
 */
public class OrderDeleteServlet extends AbstractOrderServlet {

	@Override
	protected void processRequest() throws ServletException, IOException {
		if(!checkAdmin()) {
			return;
		}

		Long id = getId();
		try {
			orderService.delete(id);
			redirect("/order", "Order was deleted.");
			return;
		} catch (ServiceException ex) {
			redirect("/order", ex.getMessage(), Message.ERROR);
			return;
		}
	}
}
