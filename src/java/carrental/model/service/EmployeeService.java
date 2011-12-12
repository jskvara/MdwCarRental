package carrental.model.service;

import carrental.model.converter.EmployeeConverter;
import carrental.model.dao.EmployeeDAO;
import carrental.model.entity.EmployeeEntity;
import com.google.inject.Inject;
import java.util.Map;
import java.util.List;

public class EmployeeService implements Service {
	@Inject protected EmployeeDAO employeeDAO;
	@Inject protected EmployeeConverter employeeConverter;

	public List<EmployeeEntity> getAll() {
		return employeeDAO.getAll();
	}

	public EmployeeEntity get(long id) {
		return employeeDAO.get(id);
	}

	public Long save(Map<String, String> input) throws ServiceException {
		EmployeeEntity entity = employeeConverter.convert(input);

		return save(entity);
	}

	public Long save(EmployeeEntity entity) throws ServiceException {
		// edit
		if (entity.getId() != null && !exists(entity.getId())) {
			throw new ServiceException("Employee "+ entity.getId() +" was not found.");
		}

		return employeeDAO.save(entity);
	}

	protected boolean exists(Long id) {
		return (get(id) != null);
	}

	public void delete(long id) throws ServiceException {
		if (!exists(id)) {
			throw new ServiceException("Employee "+ id +" was not found.");
		}

		employeeDAO.delete(id);
	}
}