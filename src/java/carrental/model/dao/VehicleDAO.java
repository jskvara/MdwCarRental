package carrental.model.dao;

import carrental.model.entity.VehicleEntity;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.util.DAOBase;
import java.util.List;

public class VehicleDAO extends DAOBase implements DAO {

	static {
		ObjectifyService.register(VehicleEntity.class);
	}

	/**
	 * Get all vehicle entities
	 * @return entities
	 */
	public List<VehicleEntity> getAll() {
		List<VehicleEntity> entities = ofy().query(VehicleEntity.class)
//				.filter("field", "value")
				.list();

		return entities;
	}

	/**
	 * Get vehicle entity
	 * @param id
	 * @return VehicleEntity|null
	 */
	public VehicleEntity get(long id) {
		VehicleEntity entity = ofy().find(VehicleEntity.class, id);

		return entity;
	}

//	public VehicleEntity getByBrand(String brand) {
//		VehicleEntity entity = ofy().query(VehicleEntity.class)
//				.filter("brand", brand).get();
//
//		return entity;
//	}

	/**
	 * Save vehicle entity
	 * @param entity
	 * @return entity key
	 */
	public Long save(VehicleEntity entity) {
		Key<VehicleEntity> key = ofy().put(entity);

		return key.getId();
	}

	/**
	 * Delete vehicle entity by id
	 * @param id
	 */
	public void delete(long id) {
		ofy().delete(VehicleEntity.class, id);
	}
}