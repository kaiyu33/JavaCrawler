package JAVA_Repository;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class day {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
//		TWday();
//		TWday(2016, 4, 10);
		TWdaybefore(50);// 距今五十天
		ADdaybefore(50);
		// ADday();
	}

	public day() {
		Date myDate = new Date();
		// 改import
		// import java.util.Date;
		// Sun Apr 10 16:51:25 GMT+08:00 2016

		// 改import
		// import java.sql.Date;
		// Date(0)=1970-01-01
		System.out.println(myDate);
	}

	public static void TWday() {
		Date myDate = new Date();
		int thisYear = myDate.getYear() + 1900;// thisYear = 2003
		int thisMonth = myDate.getMonth() + 1;// thisMonth = 5
		String Month = String.format("%02d", thisMonth);
		int thisDate = myDate.getDate();// thisDate = 30
		String Date = String.format("%02d", thisDate);
		System.out.println(thisYear - 1911 + "/" + Month + "/" + Date);
	}

	public static void TWday(int year, int month, int day) {
		String Month = String.format("%02d", month);
		String Date = String.format("%02d", day);
		System.out.println(year - 1911 + "/" + Month + "/" + Date);
	}

	public static String TWdaybefore(int daynum) {
		Calendar now = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
		// "E yyyy.MM.dd"=星期日 2016.04.10
		// System.out.println(formatter.format(now.getTime()));
		now.add(Calendar.DAY_OF_YEAR, -daynum);
		// System.out.println(formatter.format(now.getTime()));
		
		String getday = formatter.format(now.getTime());
		// 包含起始值，不包含終始值
		String sub1 = getday.substring(0, 4);
		String sub2 = getday.substring(4, getday.length());
		int yaer = (Integer.parseInt(sub1) - 1911);
//		 System.out.println(getday.length());
//		System.out.println(yaer + sub2);
		String getTWday = yaer + sub2;
		return getTWday;
	}
	public static String ADdaybefore(int daynum) {
		Calendar now = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		// "E yyyy.MM.dd"=星期日 2016.04.10
		// System.out.println(formatter.format(now.getTime()));
		now.add(Calendar.DAY_OF_YEAR, -daynum);
		// System.out.println(formatter.format(now.getTime()));
		String getday = formatter.format(now.getTime());
		// 包含起始值，不包含終始值
		String sub1 = getday.substring(0, 4);
		String sub2 = getday.substring(4, getday.length());
		int yaer = Integer.parseInt(sub1);
//		 System.out.println(getday.length());
//		System.out.println(yaer + sub2);
		String getTWday = yaer + sub2;
		return getTWday;
	}
	public static void ADday() {
		Date myDate = new Date();
		int thisYear = myDate.getYear() + 1900;// thisYear = 2003
		int thisMonth = myDate.getMonth() + 1;// thisMonth = 5
		int thisDate = myDate.getDate();// thisDate = 30
		System.out.println(thisYear + "/" + thisMonth + "/" + thisDate);
	}

}
