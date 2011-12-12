package carrental.model.entity;

import javax.persistence.Id;

public class VehicleEntity implements IEntity {

	@Id
	private Long id;
	private String brand;
	private String vin;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getVin() {
		return vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}

	@Override
	public String toString() {
		StringBuilder ret = new StringBuilder("VehicleEntity{");
		ret.append("Id:").append(id).append(",");
		ret.append("Brand:").append(brand).append(",");
		ret.append("vin:").append(vin);
		ret.append("}");

		return ret.toString();
	}
}