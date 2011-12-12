package carrental.model.entity;

import java.util.Date;
import javax.persistence.Id;

/**
 * Lease entity
 */
public class LeaseEntity implements IEntity {

	@Id
	Long id;
	Date date;
	Long vehicleId;

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
