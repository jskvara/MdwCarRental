package carrental.model.converter;

import carrental.model.entity.EmployeeEntity;
import java.util.Map;

/**
 * Employee converter
 */
public class EmployeeConverter extends AbstractConverter implements IConverter {

	public EmployeeEntity convert(Map<String, String> input) throws ConverterException {
		EmployeeEntity entity = new EmployeeEntity();
		if (input.get("id") != null && !input.get("id").equals("")) {
			entity.setId(stringToLong(input.get("id")));
		}
		entity.setName(input.get("name"));
		entity.setSurname(input.get("surname"));

		return entity;
	}
}