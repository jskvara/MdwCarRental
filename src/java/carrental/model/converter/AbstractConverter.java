package carrental.model.converter;

import carrental.util.DateUtil;
import java.text.ParseException;
import java.util.Date;

/**
 * Abstract converter
 */
public class AbstractConverter {

	protected Date stringToDate(String strDate) throws ConverterException {
		try {
			return DateUtil.stringToDate(strDate);
		} catch (ParseException ex) {
			throw new ConverterException("Unexpected date format (dd.mm.yyyy).");
		}
	}

	protected Long stringToLong(String strLong) throws ConverterException {
		Long l;
		try {
			l = Long.parseLong(strLong);
		} catch (NumberFormatException e) {
			throw new ConverterException("Unexpected number format.");
		}

		return l;
	}
}
