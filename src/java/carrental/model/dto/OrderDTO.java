package carrental.model.dto;

import carrental.model.entity.OrderEntity;
import carrental.util.DateUtil;

/**
 * Order DTO
 */
public class OrderDTO implements IDTO {

	protected OrderEntity entity;

	public OrderDTO(OrderEntity entity) {
		this.entity = entity;
	}

	public Long getId() {
		return entity.getId();
	}

	public String getDate() {
		return DateUtil.dateToString(entity.getDate());
	}

	public Long getVehicleId() {
		return entity.getVehicleId();
	}
}
