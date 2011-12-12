package carrental.controller.servlet.order;

import carrental.controller.servlet.BaseServlet;
import carrental.model.dto.OrderDTO;
import carrental.model.entity.VehicleEntity;
import carrental.model.service.OrderService;
import carrental.model.service.ServiceException;
import carrental.model.service.VehicleService;
import carrental.util.GuiceUtil;
import carrental.util.Message;
import java.io.IOException;

/**
 * Abstract class for common operation with order
 */
public abstract class AbstractOrderServlet extends BaseServlet {

	OrderService orderService = GuiceUtil.getService(OrderService.class);
	VehicleService vehicleService = GuiceUtil.getService(VehicleService.class);

	protected VehicleEntity getVehicleEntity() throws IOException {
		VehicleEntity vehicleEntity = null;

		String stringVehicleId = request.getParameter("vehicleId");
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

	protected OrderDTO getOrderEntity() throws IOException {
		OrderDTO orderEntity = null;

		String stringId = request.getParameter("id");
		if (stringId == null) {
			redirect("/order", "Wrong parameter.", Message.ERROR);
			return null;
		}

		try {
			Long id = Long.parseLong(stringId);
			orderEntity = orderService.get(id);
			if (orderEntity == null) {
				throw new ServiceException("Order doesn't exist.");
			}
		} catch (ServiceException ex) {
			redirect("/order", ex.getMessage(), Message.ERROR);
			return null;
		}

		return orderEntity;
	}

	protected Long getId() throws IOException {
		return getId("id", "/order");
	}

	protected Long getVehicleId() throws IOException {
		return getId("vehicleId", "/vehicle");
	}
}
