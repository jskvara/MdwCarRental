package carrental.controller.servlet.vehicle;

import carrental.model.entity.VehicleEntity;
import carrental.model.service.ServiceException;
import carrental.util.Message;
import carrental.util.RequestMap;
import java.io.IOException;
import javax.servlet.ServletException;

/**
 * Save vehicle
 */
public class VehicleSaveServlet extends AbstractVehicleServlet {

	@Override
	protected void processRequest() throws ServletException, IOException {
		if(!checkAdmin()) {
			return;
		}

		String submit = request.getParameter("submit");
		if (submit != null) {
			try {
				vehicleService.save(new RequestMap(request));
				redirect("/vehicle", "Vehicle was saved.");
				return;
			} catch (ServiceException ex) {
				forward("/carrental/vehicleSave.jsp", ex.getMessage(), Message.ERROR);
			}
		}

		if (request.getParameter("id") != null) {
			VehicleEntity vehicleEntity = getVehicleEntity();
			request.setAttribute("id", vehicleEntity.getId());
			request.setAttribute("brand", vehicleEntity.getBrand());
			request.setAttribute("vin", vehicleEntity.getVin());
		}

		forward("/carrental/vehicleSave.jsp");
	}
}
