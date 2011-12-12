package carrental.model.converter;

import carrental.model.entity.IEntity;
import java.util.Map;

public interface IConverter {
	public IEntity convert(final Map<String, String> input) throws ConverterException;
}