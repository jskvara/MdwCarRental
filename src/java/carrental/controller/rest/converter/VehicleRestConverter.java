package carrental.controller.rest.converter;

import carrental.model.entity.VehicleEntity;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Vehicle REST converter
 */
//@XmlElementWrapper(name = "vehicles")
@XmlRootElement(name = "vehicle")
@XmlType(propOrder = {"id", "brand", "vin"})
public class VehicleRestConverter implements IRestConverter {

	private VehicleEntity entity;

	public VehicleRestConverter() {
		entity = new VehicleEntity();
	}

	public VehicleRestConverter(VehicleEntity entity) {
		this.entity = entity;
	}

	public VehicleEntity getEntity() {
		return entity;
	}

	@XmlElement
	public Long getId() {
		return entity.getId();
	}

	public void setId(Long id) {
		this.entity.setId(id);
	}

	@XmlElement
	public String getBrand() {
		return entity.getBrand();
	}

	public void setBrand(String brand) {
		this.entity.setBrand(brand);
	}

	@XmlElement
	public String getVin() {
		return entity.getVin();
	}

	public void setVin(String vin) {
		this.entity.setVin(vin);
	}
}
