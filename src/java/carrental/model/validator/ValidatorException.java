package carrental.model.validator;

import carrental.model.service.ServiceException;

public class ValidatorException extends ServiceException {

	public ValidatorException() {
	}

	public ValidatorException(String msg) {
		super(msg);
	}
}