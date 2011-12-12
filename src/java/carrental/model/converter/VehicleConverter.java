package carrental.model.converter;

import carrental.model.entity.VehicleEntity;
import java.util.Map;

/**
 * Vehicle converter
 */
public class VehicleConverter extends AbstractConverter implements IConverter {

	public VehicleEntity convert(Map<String, String> input) throws ConverterException {
		VehicleEntity entity = new VehicleEntity();
		if (input.get("id") != null && !input.get("id").equals("")) {
			entity.setId(stringToLong(input.get("id")));
		}
		entity.setBrand(input.get("brand"));
		entity.setVin(input.get("vin"));

		return entity;
	}
}