package carrental.controller.servlet.vehicle;

import carrental.controller.servlet.BaseServlet;
import carrental.model.entity.VehicleEntity;
import carrental.model.service.ServiceException;
import carrental.model.service.VehicleService;
import carrental.util.GuiceUtil;
import carrental.util.Message;
import java.io.IOException;

/**
 * Abstract class for common operations with Vehicle
 */
public abstract class AbstractVehicleServlet extends BaseServlet {
	VehicleService vehicleService = GuiceUtil.getService(VehicleService.class);

	protected Long getId() throws IOException {
		return getId("id", "/order");
	}

	protected VehicleEntity getVehicleEntity() throws IOException {
		VehicleEntity vehicleEntity = null;

		String stringVehicleId = request.getParameter("id");
		if (stringVehicleId == null) {
			redirect("/vehicle", "Wrong parameter.", Message.ERROR);
			return null;
		}

		try {
			Long vehicleId = Long.valueOf(stringVehicleId);
			vehicleEntity = vehicleService.get(vehicleId);
			if (vehicleEntity == null) {
				throw new ServiceException("Vehicle doesn't exist.");
			}
		} catch (ServiceException ex) {
			redirect("/vehicle", ex.getMessage(), Message.ERROR);
			return null;
		} catch (NumberFormatException ex) {
			redirect("/vehicle", "Wrong parameter.", Message.ERROR);
			return null;
		}

		return vehicleEntity;
	}
}
