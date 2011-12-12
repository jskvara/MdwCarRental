package carrental.model.validator;

import carrental.model.entity.IEntity;

public interface IValidator {
	public boolean validateAdd(IEntity entity) throws ValidatorException;
	public boolean validateEdit(IEntity entity) throws ValidatorException;
}
