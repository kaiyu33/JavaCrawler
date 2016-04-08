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

public class getOtcDataCreditTransactions {

	public static void main(String[] args) throws UnsupportedEncodingException {
		// for (int j = 94; j <= 104; j++) {// 從94開始有資料
		try {
			CreditTransactions(1, "CreditTransactions");
		} catch (Exception e) {
			e.printStackTrace();
		}
		// }
		System.out.println("ALL FINISHED!!!");
	}

	public static void CreditTransactions(int year, String fileName) throws Exception {
		// input
		String url ="http://www.tpex.org.tw/web/stock/margin_trading/margin_balance/margin_bal_result.php?l=zh-tw&d=105/04/01&_=1459958110989";
		Document xmlDoc = Jsoup.parse(new URL(url).openStream(), "UTF-8", url);
		// Document doc = Jsoup.parse(new URL(url1).openStream(), "UTF-8",
		// url1);
		// Document xmlDoc = Jsoup.parse(url, 3000);

		// 使用Jsoup jar 去解析網頁
		// (要解析的文件,timeout)
		Elements body = xmlDoc.select("body");
		// Elements tr = xmlDoc.select("tr");
		// Elements th = xmlDoc.select("html>body>table>tbody>tr>th");
		// 要解析的tag元素為td
		// Elements td = xmlDoc.select("html>body>table>tbody>tr>td");
		// output
		File file = new File("C:/Temp/" + year + fileName + ".txt");
		file.createNewFile();
		FileWriter fw = new FileWriter(file, false);
		// TRUE:繼續增加文字
		BufferedWriter bw = new BufferedWriter(fw);
		String x = body.text();
		// for(int
		// i=1,j=0;j<=22;j++){System.out.println(",td.get(23*i+"+j+").text()");}
		// try {
		// for (int i = 0; i <= 2000; i++) {
		// String k = String.valueOf(i + 1);
		// String x = String.join(";", k, td.get(23 * i + 0).text(), td.get(23 *
		// i + 1).text(),
		// td.get(23 * i + 2).text(), td.get(23 * i + 3).text(), td.get(23 * i +
		// 4).text(),
		// td.get(23 * i + 5).text(), td.get(23 * i + 6).text(), td.get(23 * i +
		// 7).text(),
		// td.get(23 * i + 8).text(), td.get(23 * i + 9).text(), td.get(23 * i +
		// 10).text(),
		// td.get(23 * i + 11).text(), td.get(23 * i + 12).text(), td.get(23 * i
		// + 13).text(),
		// td.get(23 * i + 14).text(), td.get(23 * i + 15).text(), td.get(23 * i
		// + 16).text(),
		// td.get(23 * i + 17).text(), td.get(23 * i + 18).text(), td.get(23 * i
		// + 19).text(),
		// td.get(23 * i + 20).text(), td.get(23 * i + 21).text(), td.get(23 * i
		// + 22).text());

		byte[] utf8 = null;
		// unicode 轉成 UTF-8 編碼
		// utf8 = unicode.getBytes("UTF-8");

		// Big5 編碼 轉回 unicode 再轉成 UTF-8 編碼
		utf8 = new String(x, "Big5").getBytes("UTF-8");
		System.out.println("UTF-8: " + utf8);
		// unicode 轉成 Big5 編碼
		// byte[] big5 = x.getBytes("Big5");
		// Big5 編碼 轉回 unicode
		// x = new String(big5, "Big5");
//		System.out.println("Big5: " + x);
		// byte y[] = big5;
		// for (int i = 0; i < y.length; i++) {
		// System.out.printf("%x ", y[i]);
		// }

		// // System.out.println(x);
		bw.write(x);
		// bw.newLine();
		// }
		// } catch (IndexOutOfBoundsException e) {
		// // break;
		// } finally {
		// try {
		bw.close();
		fw.close();
		System.out.println("FINISHED:" + year + "YearCoupon");
		// } catch (IOException e) {
		// }
		// } // 完整寫入檔案

		// FileOutputStream write =
		// FileOutputStream("C:/temp/A.txt",true);
		//
		// byte[] data = x.getBytes();
		// write.write(data);
		// write.close();

	}

}
