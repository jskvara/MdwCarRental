package carrental.model.service;

import carrental.model.converter.OrderConverter;
import carrental.model.dao.OrderDAO;
import carrental.model.dao.VehicleDAO;
import carrental.model.dto.OrderDTO;
import carrental.model.entity.OrderEntity;
import com.google.inject.Inject;
import java.util.LinkedList;
import java.util.Map;
import java.util.List;

public class OrderService implements Service {
	@Inject
	protected OrderDAO orderDAO;
	@Inject
	protected VehicleDAO vehicleDAO;
	@Inject
	protected OrderConverter orderConverter;

	public List<OrderDTO> getAll() {
		return convertToDTO(orderDAO.getAll());
	}

	protected List<OrderDTO> convertToDTO(List<OrderEntity> entities) {
		List<OrderDTO> dtos = new LinkedList<OrderDTO>();
		for (OrderEntity e : entities) {
			dtos.add(new OrderDTO(e));
		}

		return dtos;
	}

	public OrderDTO get(long id) {
		OrderEntity orderEntity = orderDAO.get(id);
		if (orderEntity == null) {
			return null;
		}

		return new OrderDTO(orderEntity);
	}

	public List<OrderDTO> getByVehicle(Long vehicleId) {
		return convertToDTO(orderDAO.getByVehicle(vehicleId));
	}

	public Long save(Map<String, String> input) throws ServiceException {
		OrderEntity entity = orderConverter.convert(input);

		return save(entity);
	}

	public Long save(OrderEntity entity) throws ServiceException {
		// edit
		if (entity.getId() != null && !exists(entity.getId())) {
			throw new ServiceException("Order "+ entity.getId() +" was not found.");
		}

		if (entity.getVehicleId() == null ||
				vehicleDAO.get(entity.getVehicleId()) == null) {
			throw new ServiceException("Vehicle "+ entity.getVehicleId() +" was not found.");
		}

		List<OrderEntity> savedOrders = orderDAO.getByVehicle(entity.getVehicleId());
		for (OrderEntity e : savedOrders) {
			if (e.getDate().equals(entity.getDate())) {
				throw new ServiceException("This date is taken.");
			}
		}

		return orderDAO.save(entity);
	}

	protected boolean exists(Long id) {
		return (get(id) != null);
	}

	public void delete(long id) throws ServiceException {
		if (!exists(id)) {
			throw new ServiceException("Order "+ id +" was not found.");
		}

		orderDAO.delete(id);
	}
}