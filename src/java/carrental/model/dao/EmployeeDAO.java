package carrental.model.dao;

import carrental.model.entity.EmployeeEntity;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.util.DAOBase;
import java.util.List;

public class EmployeeDAO extends DAOBase implements DAO {

	static {
		ObjectifyService.register(EmployeeEntity.class);
	}

	/**
	 * Get all employee entities
	 * @return entities
	 */
	public List<EmployeeEntity> getAll() {
		List<EmployeeEntity> entities = ofy().query(EmployeeEntity.class).list();

		return entities;
	}

	/**
	 * Get employee entity
	 * @param id
	 * @return EmployeeEntity|null
	 */
	public EmployeeEntity get(long id) {
		EmployeeEntity entity = ofy().find(EmployeeEntity.class, id);

		return entity;
	}

	/**
	 * Save employee entity
	 * @param entity
	 * @return entity key
	 */
	public Long save(EmployeeEntity entity) {
		Key<EmployeeEntity> key = ofy().put(entity);

		return key.getId();
	}

	/**
	 * Delete employee entity by id
	 * @param id
	 */
	public void delete(long id) {
		ofy().delete(EmployeeEntity.class, id);
	}
}