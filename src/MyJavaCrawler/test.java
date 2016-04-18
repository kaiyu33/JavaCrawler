package MyJavaCrawler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class test {

	public static void main(String[] args) throws IOException {
//		 for(int i=1;i<=20;i++){
//		 System.out.println("elem2.select(\"td:eq(\"+"+i+"+\")\").text(),");
//		 }
		// for(int i=1;i<=24;i++){
		// System.out.print("D"+i+",");
		// }
		// Date myDate = new Date();
		// System.out.println(myDate);

		String dataoutput1 = "String dataoutput1 = ";
		int colmax = 20;
		for (int i = 0; i <= colmax; i++) {
			//td.get(23 * i + 2).text(), 
			dataoutput1 += "td.get(23 *i+" +i+").text().replace(\",\", \"\").replace(\" \", \"\")";
			if (i < colmax) {
				dataoutput1 += ",\n";
			} else if (i == colmax) {
				dataoutput1 += ";";
			}

		}
		System.out.println(dataoutput1);
	}

}