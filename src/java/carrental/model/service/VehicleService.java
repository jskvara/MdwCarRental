package carrental.model.service;

import carrental.model.converter.VehicleConverter;
import carrental.model.dao.OrderDAO;
import carrental.model.dao.VehicleDAO;
import carrental.model.entity.VehicleEntity;
import com.google.inject.Inject;
import java.util.Map;
import java.util.List;

public class VehicleService implements Service {
	@Inject protected VehicleDAO vehicleDAO;// VehicleCloudSqlDAO
	@Inject protected OrderDAO orderDAO;
	@Inject protected VehicleConverter vehicleConverter;
//	@Inject protected VehicleValidator vehicleValidator;

	public List<VehicleEntity> getAll() {
		return vehicleDAO.getAll();
	}

	public VehicleEntity get(long id) {
		return vehicleDAO.get(id);
	}

	public Long save(Map<String, String> input) throws ServiceException {
		VehicleEntity entity = vehicleConverter.convert(input);

		return save(entity);
	}

	public Long save(VehicleEntity entity) throws ServiceException {
		// edit
		if (entity.getId() != null && !exists(entity.getId())) {
			throw new ServiceException("Vehicle "+ entity.getId() +" was not found.");
		}

		return vehicleDAO.save(entity);
	}

	protected boolean exists(Long id) {
		return (get(id) != null);
	}

	public void delete(long id) throws ServiceException {
		if (!exists(id)) {
			throw new ServiceException("Vehicle "+ id +" was not found.");
		}

		vehicleDAO.delete(id);
		orderDAO.deleteAllByVehicle(id);
	}
}