package MyJavaCrawler;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class getTwseData2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Parsing();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void Parsing() throws Exception {
		//繼承自getTwseData.java
		for (int i = 104; i >= 94; i--) {// 從94開始有資料
			File file = new File("C:/Temp/Testing" + i + ".txt");
			FileWriter fw;
			try {
				file.createNewFile();
				fw = new FileWriter(file);
				String url1 = "http://mops.twse.com.tw/mops/web/ajax_t108sb27?encodeURIComponent=1&step=1&firstin=1&off=1&keyword4=&code1=&TYPEK2=&checkbtn=&queryName=&TYPEK=sii&co_id_1=&co_id_2=&year="
						+ i + "&month=&b_date=&e_date=&type=";
				// URL url = new URL(
				// "http://mops.twse.com.tw/mops/web/ajax_t108sb27?encodeURIComponent=1&step=1&firstin=1&off=1&keyword4=&code1=&TYPEK2=&checkbtn=&queryName=&TYPEK=sii&co_id_1=&co_id_2=&year="+i+"&month=&b_date=&e_date=&type=");
				Document doc = Jsoup.parse(new URL(url1).openStream(), "UTF-8", url1);
				Elements tab1 = doc.getElementsByTag("table");

				Elements tr = doc.select("tr");
				Elements td = doc.select("html>body>table>tbody>tr>td");
				try {
					for (int j = 0; j <= 2000; j++) {
						System.out.println(j + " , " + td.get(j).text()); // 得到td
					}
				} catch (IndexOutOfBoundsException e) {
					// TODO Auto-generated catch block
					break;
				}

				System.out.println();
				fw.write(tab1.text());
				fw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
