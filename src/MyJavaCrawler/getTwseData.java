package MyJavaCrawler;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class getTwseData {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		for(int i=105;i>=94;i--){//從94開始有資料
			File file = new File("C:/Temp/Testing"+i+".txt");
			FileWriter fw;
			try {
				file.createNewFile();
				fw = new FileWriter(file);
				String url1 = "http://mops.twse.com.tw/mops/web/ajax_t108sb27?encodeURIComponent=1&step=1&firstin=1&off=1&keyword4=&code1=&TYPEK2=&checkbtn=&queryName=&TYPEK=sii&co_id_1=&co_id_2=&year="+i+"&month=&b_date=&e_date=&type=";
				Document doc = Jsoup.parse(new URL(url1).openStream(), "UTF-8", url1);
				Elements tab1 = doc.getElementsByTag("table");
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
