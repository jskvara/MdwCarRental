package carrental.model.converter;

import carrental.model.entity.AddressEntity;
import java.util.Map;

/**
 * Address converter
 */
public class AddressConverter extends AbstractConverter implements IConverter {

	public AddressEntity convert(Map<String, String> input) throws ConverterException {
		AddressEntity entity = new AddressEntity();
		if (input.get("id") != null && !input.get("id").equals("")) {
			entity.setId(stringToLong(input.get("id")));
		}
		entity.setCity(input.get("city"));
		entity.setCountry(input.get("country"));
		entity.setPostalNumber(input.get("postalNumber"));
		entity.setStreet(input.get("street"));

		return entity;
	}
}