package carrental.controller.servlet.vehicle;

import java.io.IOException;
import javax.servlet.ServletException;

/**
 * Vehicle servlet
 */
public class VehicleServlet extends AbstractVehicleServlet {

	@Override
	protected void processRequest() throws ServletException, IOException {
		request.setAttribute("items", vehicleService.getAll());
		forward("/carrental/vehicle.jsp");
	}
}
