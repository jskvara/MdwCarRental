package carrental.model.converter;

import carrental.model.entity.OrderEntity;
import java.util.Map;

/**
 * Order converter
 */
public class OrderConverter extends AbstractConverter implements IConverter {

	public OrderEntity convert(Map<String, String> input) throws ConverterException {
		OrderEntity entity = new OrderEntity();
		if (input.get("id") != null) {
			entity.setId(stringToLong(input.get("id")));
		}
		entity.setDate(stringToDate(input.get("date")));
		entity.setVehicleId(stringToLong(input.get("vehicleId")));

		return entity;
	}
}