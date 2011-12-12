package carrental.model.converter;

import carrental.model.entity.LeaseEntity;
import java.util.Map;

/**
 * Lease converter
 */
public class LeaseConverter extends AbstractConverter implements IConverter {

	public LeaseEntity convert(Map<String, String> input) throws ConverterException {
		LeaseEntity entity = new LeaseEntity();
		if (input.get("id") != null) {
			entity.setId(stringToLong(input.get("id")));
		}
		entity.setDate(stringToDate(input.get("date")));
		entity.setVehicleId(stringToLong(input.get("vehicleId")));

		return entity;
	}
}