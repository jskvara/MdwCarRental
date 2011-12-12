package carrental.controller.servlet.order;

import carrental.model.dto.OrderDTO;
import carrental.model.entity.VehicleEntity;
import carrental.model.service.ServiceException;
import carrental.util.Message;
import carrental.util.RequestMap;
import java.io.IOException;
import javax.servlet.ServletException;

/**
 * Save order
 */
public class OrderSaveServlet extends AbstractOrderServlet {

	@Override
	protected void processRequest()
			throws ServletException, IOException {
		if(!checkAdmin()) {
			return;
		}

		String submit = request.getParameter("submit");
		if (submit != null) {
			try {
				orderService.save(new RequestMap(request));
				redirect("/order", "Order was saved.");
				return;
			} catch (ServiceException ex) {
				if (request.getParameter("id") == null) {
					request.setAttribute("vehicleId", getVehicleId());
				}
				forward("/carrental/orderSave.jsp", ex.getMessage(), Message.ERROR);
				return;
			}
		}

		if (request.getParameter("id") != null) {
			OrderDTO order = getOrderEntity();
			request.setAttribute("id", order.getId());
			request.setAttribute("date", order.getDate());
			request.setAttribute("vehicleId", order.getVehicleId());
		} else {
			VehicleEntity vehicleEntity = getVehicleEntity();
			request.setAttribute("vehicleId", vehicleEntity.getId());
		}


		forward("/carrental/orderSave.jsp");
	}
}
