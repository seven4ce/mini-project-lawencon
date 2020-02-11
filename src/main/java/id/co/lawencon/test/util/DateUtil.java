package id.co.lawencon.test.util;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class DateUtil {
	
	public static final String FORMAT_DEFAULT_DB = "yyyy-MM-dd HH:mm:ss.SSS";
	
	public static Date startDate(String strDate) {
		Date startDate = null;
		String strTglAwal = null;
		SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_DEFAULT_DB);
		if(strDate!=null & strDate!="" ) {
			
			strTglAwal = strDate + " 00:00:00.000";
			try {
				startDate = sdf.parse(strTglAwal);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return startDate;
		
	}
	
	public static Date endDate(String strDate) {
		Date endDate = null;
		String strTglAkhir = null;
		SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_DEFAULT_DB);
		if(strDate!=null & strDate!="" ) {
			
			strTglAkhir = strDate + " 23:59:59.999";
			try {
				endDate = sdf.parse(strTglAkhir);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return endDate;
		
	}

}
