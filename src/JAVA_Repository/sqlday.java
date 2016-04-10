package JAVA_Repository;
import java.sql.Date;

public class sqlday {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		sqlday();
	}
	
	@SuppressWarnings("deprecation")
	public static void sqlday() {
		Date myDate = new Date(50000);
		Date myDate2 = new Date(115,3,1);
		//改import
		// import java.util.Date;
		// Sun Apr 10 16:51:25 GMT+08:00 2016
		
		//改import
		// import java.sql.Date;
		// Date(0)=1970-01-01
		 System.out.println(myDate);
		 System.out.println(myDate2);
		 System.out.println(myDate.getTime()-myDate2.getTime());

	}
	//1987/7/5
	public static void TWday(int daynum) {
		Date myDate = new Date(daynum);
		String Month=String.format("%02d", month);
		String Date=String.format("%02d", day);
		System.out.println(year-1911 + "/" + Month + "/" + Date);
		 System.out.println(myDate.getTime()-myDate2.getTime());

	}
}
