package carrental.model.service;

import carrental.model.converter.LeaseConverter;
import carrental.model.dao.LeaseDAO;
import carrental.model.entity.LeaseEntity;
import com.google.inject.Inject;
import java.util.Map;
import java.util.List;

public class LeaseService implements Service {
	@Inject
	protected LeaseDAO leaseDAO;
	@Inject
	protected LeaseConverter leaseConverter;

	public List<LeaseEntity> getAll() {
		return leaseDAO.getAll();
	}

	public LeaseEntity get(long id) {
		return leaseDAO.get(id);
	}

	public Long save(Map<String, String> input) throws ServiceException {
		LeaseEntity entity = leaseConverter.convert(input);

		return leaseDAO.save(entity);
	}

	public Long save(LeaseEntity entity) throws ServiceException {
		// edit
		if (entity.getId() != null && !exists(entity.getId())) {
			throw new ServiceException("Lease "+ entity.getId() +" was not found.");
		}

		return leaseDAO.save(entity);
	}

	protected boolean exists(Long id) {
		return (get(id) != null);
	}

	public void delete(long id) throws ServiceException {
		if (!exists(id)) {
			throw new ServiceException("Lease "+ id +" was not found.");
		}

		leaseDAO.delete(id);
	}
}