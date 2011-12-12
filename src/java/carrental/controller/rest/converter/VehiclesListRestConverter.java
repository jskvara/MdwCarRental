package carrental.controller.rest.converter;

import carrental.model.entity.VehicleEntity;
import java.util.LinkedList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Vehicle REST converter
 */
@XmlRootElement(name = "vehicles")
public class VehiclesListRestConverter implements IRestConverter {

	private List<VehicleRestConverter> entities = new LinkedList<VehicleRestConverter>();

	public VehiclesListRestConverter() {
	}

	public VehiclesListRestConverter(List<VehicleEntity> vehicleEntities) {
		for (VehicleEntity ve : vehicleEntities) {
			entities.add(new VehicleRestConverter(ve));
		}
	}

	@XmlElement(name = "vehicle")
	public List<VehicleRestConverter> getEntities() {
		return entities;
	}
}
