package carrental.model.dao;

import carrental.model.entity.LeaseEntity;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.util.DAOBase;
import java.util.List;

public class LeaseDAO extends DAOBase implements DAO {

	static {
		ObjectifyService.register(LeaseEntity.class);
	}

	/**
	 * Get all lease entities
	 * @return leases
	 */
	public List<LeaseEntity> getAll() {
		List<LeaseEntity> entities = ofy().query(LeaseEntity.class)
				.list();

		return entities;
	}

	/**
	 * Get all leases by vehicle id
	 * @param vehicleId
	 * @return vehicle leases
	 */
	public List<LeaseEntity> getAllByVehicle(long vehicleId) {
		List<LeaseEntity> entities = ofy().query(LeaseEntity.class)
				.filter("vehicleId", vehicleId).list();

		return entities;
	}

	/**
	 * Get Lease entity
	 * @param id
	 * @return LeaseEntity|null
	 */
	public LeaseEntity get(long id) {
		LeaseEntity entity = ofy().find(LeaseEntity.class, id);

		return entity;
	}

	/**
	 * Save Lease entity
	 * @param entity
	 * @return entity key
	 */
	public Long save(LeaseEntity entity) {
		Key<LeaseEntity> key = ofy().put(entity);

		return key.getId();
	}

	/**
	 * Delete Lease entity by id
	 * @param id
	 */
	public void delete(long id) {
		ofy().delete(LeaseEntity.class, id);
	}
}