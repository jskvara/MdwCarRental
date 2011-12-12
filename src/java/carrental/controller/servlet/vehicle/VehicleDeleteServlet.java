package carrental.controller.servlet.vehicle;

import carrental.model.service.ServiceException;
import carrental.util.Message;
import java.io.IOException;
import javax.servlet.ServletException;

/**
 * Delete vehicle
 */
public class VehicleDeleteServlet extends AbstractVehicleServlet {

	@Override
	protected void processRequest() throws ServletException, IOException {
		if(!checkAdmin()) {
			return;
		}

		Long id = getId();

		try {
			vehicleService.delete(id);
		} catch (ServiceException ex) {
			redirect("/vehicle", ex.getMessage(), Message.ERROR);
			return;
		}

		redirect("/vehicle", "Vehicle was deleted.");
	}
}
