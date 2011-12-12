package carrental.model.entity;

import java.util.Date;
import javax.persistence.Id;

/**
 * Order entity
 */
public class OrderEntity implements IEntity {

	@Id private Long id;
	private Date date;
	private Long vehicleId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Long getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(Long vehicleId) {
		this.vehicleId = vehicleId;
	}
}
