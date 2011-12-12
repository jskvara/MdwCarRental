package carrental.model.dao;

import carrental.model.entity.AddressEntity;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.util.DAOBase;
import java.util.List;

public class AddressDAO extends DAOBase implements DAO {

	static {
		ObjectifyService.register(AddressEntity.class);
	}

	/**
	 * Get all address entities
	 * @return entities
	 */
	public List<AddressEntity> getAll() {
		List<AddressEntity> entities = ofy().query(AddressEntity.class).list();

		return entities;
	}

	/**
	 * Get address entity
	 * @param id
	 * @return AddressEntity|null
	 */
	public AddressEntity get(long id) {
		AddressEntity entity = ofy().find(AddressEntity.class, id);

		return entity;
	}

	/**
	 * Save address entity
	 * @param entity
	 * @return entity key
	 */
	public Long save(AddressEntity entity) {
		Key<AddressEntity> key = ofy().put(entity);

		return key.getId();
	}

	/**
	 * Delete address entity by id
	 * @param id
	 */
	public void delete(long id) {
		ofy().delete(AddressEntity.class, id);
	}
}