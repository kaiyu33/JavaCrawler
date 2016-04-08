package MyJavaCrawler;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class getTwseDataX_ChangeEncodeFin {

	public static void main(String[] args) {
		// for (int j = 94; j <= 104; j++) {// 從94開始有資料
		int j = 94;
		try {
			YearCoupon(j, "YearCoupon");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// String changeFileName = j + "YearCoupon";
			// System.out.println(changeFileName);
			// try {
			// changeCode(changeFileName, "UTF-8", "BIG5");
			// } catch (FileNotFoundException e) {
			// e.printStackTrace();
			// } catch (IOException e) {
			// e.printStackTrace();
			// }
		}
		// }

	}

	public static void YearCoupon(int year, String fileName) throws Exception {
		// input
		String url = "http://mops.twse.com.tw/mops/web/ajax_t108sb27?encodeURIComponent=1&step=1&firstin=1&off=1&keyword4=&code1=&TYPEK2=&checkbtn=&queryName=&TYPEK=sii&co_id_1=&co_id_2=&year="
				+ year + "&month=&b_date=&e_date=&type=";
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
				String x = String.join(";", k, td.get(23 * i + 0).text(), td.get(23 * i + 1).text(),
						td.get(23 * i + 2).text(), td.get(23 * i + 3).text(), td.get(23 * i + 4).text(),
						td.get(23 * i + 5).text(), td.get(23 * i + 6).text(), td.get(23 * i + 7).text(),
						td.get(23 * i + 8).text(), td.get(23 * i + 9).text(), td.get(23 * i + 10).text(),
						td.get(23 * i + 11).text(), td.get(23 * i + 12).text(), td.get(23 * i + 13).text(),
						td.get(23 * i + 14).text(), td.get(23 * i + 15).text(), td.get(23 * i + 16).text(),
						td.get(23 * i + 17).text(), td.get(23 * i + 18).text(), td.get(23 * i + 19).text(),
						td.get(23 * i + 20).text(), td.get(23 * i + 21).text(), td.get(23 * i + 22).text());
				
				// unicode 轉成 Big5 編碼
			    byte[] big5 = x.getBytes("Big5");
			    // Big5 編碼 轉回 unicode
			    x = new String(big5, "Big5");
			    System.out.println("Big5: " + x);
//			    byte y[] = big5;
//			    for (int i = 0; i < y.length; i++) {
//			      System.out.printf("%x ", y[i]);
//			    }
				
//				System.out.println(x);
				bw.write(x);
				bw.newLine();
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

	public static void changeCodeF1(String fileName, String inCode, String outCode) throws IOException {
		FileInputStream fis = new FileInputStream("C:/Temp/" + fileName + ".txt");
		// InputStreamReader inReader = new InputStreamReader(fis, inCode);
		// System.out.println(inReader.getEncoding());
		FileOutputStream fos = new FileOutputStream("C:/Temp/" + fileName + ".csv");
		OutputStreamWriter inWriter = new OutputStreamWriter(fos, outCode);
		System.out.println(inWriter.getEncoding());
		// TRUE:繼續增加文字
		int data;
		while ((data = fis.read()) != -1) {
			// System.out.print((char) data + " ");
			fos.write(data);
		}
		fis.close();
		fos.close();
	}

	public static void changeCodeF2() {
		String unicode = "中文轉碼測試";

		System.out.println("UTF-16: " + unicode);
		char y[] = unicode.toCharArray();
		for (int i = 0; i < y.length; i++) {
			System.out.printf("%x ", (int) y[i]);
		}
		System.out.println();

		// unicode 轉成 Big5 編碼
		byte[] big5 = unicode.getBytes("Big5");
		// Big5 編碼 轉回 unicode
		unicode = new String(big5, "Big5");
		System.out.println("Big5: " + unicode);
		byte x[] = big5;
		for (int i = 0; i < x.length; i++) {
			System.out.printf("%x ", x[i]);
		}
		System.out.println();
		byte[] utf8 = null;
		// unicode 轉成 UTF-8 編碼
		// utf8 = unicode.getBytes("UTF-8");

		// Big5 編碼 轉回 unicode 再轉成 UTF-8 編碼
		utf8 = new String(big5, "Big5").getBytes("UTF-8");

		System.out.println("UTF-8: " + unicode);
		byte z[] = utf8;
		for (int i = 0; i < z.length; i++) {
			System.out.printf("%x ", z[i]);
		}

	}
}
