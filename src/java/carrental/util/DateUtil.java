package carrental.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Date utility
 */
public class DateUtil {

	public static final DateFormat DATE_FORMATTER =
			new SimpleDateFormat("dd.MM.yyyy");

	public static String dateToString(Date date) {
		return DATE_FORMATTER.format(date);
	}

	public static Date stringToDate(String date) throws ParseException {
		return (Date) DATE_FORMATTER.parse(date);
	}
}
