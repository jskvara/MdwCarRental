package carrental.model.dao;

import carrental.model.entity.OrderEntity;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.util.DAOBase;
import java.util.List;

public class OrderDAO extends DAOBase implements DAO {

	static {
		ObjectifyService.register(OrderEntity.class);
	}

	/**
	 * Get all order entities
	 * @return orders
	 */
	public List<OrderEntity> getAll() {
		List<OrderEntity> entities = ofy().query(OrderEntity.class)
				.list();

		return entities;
	}

	/**
	 * Get all orders by vehicle id
	 * @param vehicleId
	 * @return vehicle orders
	 */
	public List<OrderEntity> getByVehicle(long vehicleId) {
		List<OrderEntity> entities = ofy().query(OrderEntity.class)
				.filter("vehicleId", vehicleId).list();

		return entities;
	}

	/**
	 * Get Order entity
	 * @param id
	 * @return OrderEntity|null
	 */
	public OrderEntity get(long id) {
		OrderEntity entity = ofy().find(OrderEntity.class, id);

		return entity;
	}

	/**
	 * Save Order entity
	 * @param entity
	 * @return entity key
	 */
	public Long save(OrderEntity entity) {
		Key<OrderEntity> key = ofy().put(entity);

		return key.getId();
	}

	/**
	 * Delete Order entity by id
	 * @param id
	 */
	public void delete(long id) {
		ofy().delete(OrderEntity.class, id);
	}

	public void deleteAllByVehicle(Long vehicleId) {
		Iterable<Key<OrderEntity>> keys =
				ofy().query(OrderEntity.class)
				.filter("vehicleId", vehicleId)
				.fetchKeys();

		ofy().delete(keys);
	}
}