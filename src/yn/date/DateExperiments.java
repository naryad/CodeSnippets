package yn.date;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.time.DateUtils;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

public class DateExperiments {
	public static void main(String[] args) throws ParseException {
		String dateStr = "02/13/2013";
		String[] DATE_FORMATS = new String[]{"yyyy-MM-dd'T'HH:mm:ss.S", "yyyy-MM-dd HH:mm:ss", "MM/dd/yyyy", "yyyy-MM-dd"};
		System.out.println(DateUtils.parseDateStrictly(dateStr, DATE_FORMATS));
		
		System.out.println(new Date().toString());
		dateStr = "2011-09-19T155711.000-0700";
		String pattern = "yyyy-MM-dd'T'HHmmss.SSSZ";
		Date date = new SimpleDateFormat(pattern).parse(dateStr);
		System.out.println(date.toString());
		
		String pattern1 = "EEE, dd MMM yyyy HH:m:s zzz";
		String dateStr1 = "Fri, 28 Oct 2011 07:43:18 GMT";
		Date date1 = new SimpleDateFormat(pattern1).parse(dateStr1);
		System.out.println("date1 "  + date1.toString());
		
		System.out.println(new Timestamp(date.getTime()));

		System.out.println(new SimpleDateFormat("yyyy-MM-dd'T'HHmmss.SSSZ")
				.format(new Date(Long.MAX_VALUE)));

		pattern = "EEE MMM dd yyyy HH:mm:ss Z";
		dateStr = "Fri Oct 21 2011 08:45:00 +0530";
		date = new SimpleDateFormat(pattern).parse(dateStr);
		System.out.println(date);

		// create Date object
		date = new Date();

		// formatting TimeZone in z (General time zone) format like EST.
		String strDateFormat = "zzz";
		SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);

		System.out.println("TimeZone in z format : " + sdf.format(date));

		// formatting TimeZone in zzzz format Eastern Standard Time.
		strDateFormat = "zzzz";
		sdf = new SimpleDateFormat(strDateFormat);
		System.out.println("TimeZone in zzzz format : " + sdf.format(date));

		// formatting TimeZone in Z (RFC 822) format like -8000.
		strDateFormat = "Z";
		sdf = new SimpleDateFormat(strDateFormat);
		System.out.println("TimeZone in Z format : " + sdf.format(date));
		
	
		jodaExperiments();
	}

	private static void jodaExperiments() {
		System.out.println("---------------Joda experiments start -------------");
		DateTimeZone gmt = DateTimeZone.forID("Etc/GMT");
		DateTimeZone gmtPlusOne = DateTimeZone.forID("Etc/GMT+1");
		DateTimeZone gmtMinusOne = DateTimeZone.forID("Etc/GMT-1");

		System.out.println(new DateTime(gmt).toString());
		System.out.println(new DateTime(gmtPlusOne).toString());
		System.out.println(new DateTime(gmtMinusOne).toString());
	}
}
