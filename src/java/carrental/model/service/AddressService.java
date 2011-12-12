package carrental.model.service;

import carrental.model.converter.AddressConverter;
import carrental.model.dao.AddressDAO;
import carrental.model.entity.AddressEntity;
import com.google.inject.Inject;
import java.util.Map;
import java.util.List;

public class AddressService implements Service {
	@Inject protected AddressDAO addressDAO;
	@Inject protected AddressConverter addressConverter;

	public List<AddressEntity> getAll() {
		return addressDAO.getAll();
	}

	public AddressEntity get(long id) {
		return addressDAO.get(id);
	}

	public Long save(Map<String, String> input) throws ServiceException {
		AddressEntity entity = addressConverter.convert(input);

		return save(entity);
	}

	public Long save(AddressEntity entity) throws ServiceException {
		// edit
		if (entity.getId() != null && !exists(entity.getId())) {
			throw new ServiceException("Address "+ entity.getId() +" was not found.");
		}

		return addressDAO.save(entity);
	}

	protected boolean exists(Long id) {
		return (get(id) != null);
	}

	public void delete(long id) throws ServiceException {
		if (!exists(id)) {
			throw new ServiceException("Address "+ id +" was not found.");
		}

		addressDAO.delete(id);
	}
}