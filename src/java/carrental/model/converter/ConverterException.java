package carrental.model.converter;

import carrental.model.service.ServiceException;

public class ConverterException extends ServiceException {

	public ConverterException() {
	}

	public ConverterException(String msg) {
		super(msg);
	}
}