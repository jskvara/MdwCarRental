package carrental.controller.servlet.order;

import carrental.model.entity.VehicleEntity;
import java.io.IOException;
import javax.servlet.ServletException;

/**
 * Order servlet
 */
public class OrderServlet extends AbstractOrderServlet {

	@Override
	protected void processRequest() throws ServletException, IOException {
		if (request.getParameter("vehicleId") != null) {
			VehicleEntity vehicleEntity = getVehicleEntity();
			request.setAttribute("vehicle", vehicleEntity);
			request.setAttribute("items", orderService.getByVehicle(vehicleEntity.getId()));
		} else {
			request.setAttribute("items", orderService.getAll());
		}

		forward("/carrental/order.jsp");
	}
}
