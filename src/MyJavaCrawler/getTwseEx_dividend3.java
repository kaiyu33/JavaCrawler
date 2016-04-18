package MyJavaCrawler;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class getTwseEx_dividend3 {

	public static void main(String[] args) throws UnsupportedEncodingException {
//		for (int j = 94; j <= 104; j++) {// 從94開始有資料
			try {
				int j=104;
				YearCoupon(j, "YearCoupon");
			} catch (Exception e) {
				e.printStackTrace();
			}
//		}
		System.out.println("ALL FINISHED!!!");
	}

	public static void YearCoupon(int year, String fileName) throws Exception {
		// input
		//http://mops.twse.com.tw/mops/web/t108sb27
		String url = "http://mops.twse.com.tw/mops/web/ajax_t108sb27?encodeURIComponent=1&step=1&firstin=1&off=1&keyword4=&code1=&TYPEK2=&checkbtn=&queryName=&TYPEK=sii&co_id_1=&co_id_2=&year="
				+ year + "&month=&b_date=&e_date=&type=";
		System.out.println(url);
		Document xmlDoc = Jsoup.parse(new URL(url).openStream(), "UTF-8", url);
		// Document doc = Jsoup.parse(new URL(url1).openStream(), "UTF-8",
		// url1);
		// Document xmlDoc = Jsoup.parse(url, 3000);

		// 使用Jsoup jar 去解析網頁
		// (要解析的文件,timeout)
		// Elements tr = xmlDoc.select("tr");
		// Elements th = xmlDoc.select("html>body>table>tbody>tr>th");
		// 要解析的tag元素為td
		Elements td = xmlDoc.select("html>body>table>tbody>tr>td");
		// output
		File file = new File("C:/Temp/" + year + fileName + ".txt");
		file.createNewFile();
		FileWriter fw = new FileWriter(file, false);
		// TRUE:繼續增加文字
		BufferedWriter bw = new BufferedWriter(fw);
		// for(int
		// i=1,j=0;j<=22;j++){System.out.println(",td.get(23*i+"+j+").text()");}
		try {
			for (int i = 0; i <= 2000; i++) {
				String k = String.valueOf(i + 1);
				//body > table:nth-child(4) > tbody > tr:nth-child(4)
				String x = String.join(";", k, td.get(23 * i + 0).text(), td.get(23 * i + 1).text(),
						td.get(23 * i + 2).text(), 
						td.get(23 * i + 3).text(), td.get(23 * i + 4).text(),
						td.get(23 * i + 5).text(), td.get(23 * i + 6).text(), td.get(23 * i + 7).text(),
						td.get(23 * i + 8).text(), td.get(23 * i + 9).text(), td.get(23 * i + 10).text(),
						td.get(23 * i + 11).text(), td.get(23 * i + 12).text(), td.get(23 * i + 13).text(),
						td.get(23 * i + 14).text(), td.get(23 * i + 15).text(), td.get(23 * i + 16).text(),
						td.get(23 * i + 17).text(), td.get(23 * i + 18).text(), td.get(23 * i + 19).text(),
						td.get(23 * i + 20).text(), td.get(23 * i + 21).text(), td.get(23 * i + 22).text());
				 System.out.println(x);
//				bw.write(x);
//				bw.newLine();
			}
		} catch (IndexOutOfBoundsException e) {
			// break;
		} finally {
			try {
				bw.close();
				fw.close();
				System.out.println("FINISHED:" + year + "YearCoupon");
			} catch (IOException e) {
			}
		} // 完整寫入檔案

		// FileOutputStream write =
		// FileOutputStream("C:/temp/A.txt",true);
		//
		// byte[] data = x.getBytes();
		// write.write(data);
		// write.close();

	}

}
